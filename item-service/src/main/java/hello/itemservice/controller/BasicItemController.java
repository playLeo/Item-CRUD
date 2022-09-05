package hello.itemservice.controller;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
@Api(tags = {"1.item"})
public class BasicItemController {

    private final ItemService itemService;

    @GetMapping
    @ApiOperation(value = "아이템 목록 조회", notes = "모든 회원 목록을 조회한다.")
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}/delete")
    @ApiOperation(value = "아이템 삭제폼", notes = "아이템 삭제폼으로 이동한다.")
    public String delete(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemService.findItem(itemId));
        return "basic/deleteForm";
    }

    //PRG 패턴 적용
    @PostMapping("/{itemId}/delete")
    @ApiOperation(value = "아이템 삭제", notes = "아이템 id로 특정 아이템을 삭제한다.")
    public String itemDelete(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return "redirect:/items";
    }

    @GetMapping("/{itemId}")
    @ApiOperation(value = "아이템 조회", notes = "아이템 id로 특정 아이템을 조회한다.")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    @ApiOperation(value = "아이템 등록폼", notes = "아이템 등록폼으로 이동한다.")
    public String addForm() {
        return "basic/addForm";
    }


    //PRG 패턴 적용 RedirectAttributes를 사용해서 일정정보를 같이 넘길 수 있다.
    @PostMapping("/add")
    @ApiOperation(value = "아이템 등록", notes = "아이템을 등록한다.")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemService.saveItem(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    @ApiOperation(value = "아이템 수정폼", notes = "아이템 수정폼으로 이동한다.")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    @ApiOperation(value = "아이템 수정", notes = "아이템을 수정한다.")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemService.updateItem(itemId, item);
        return "redirect:/items/{itemId}";
    }
}

