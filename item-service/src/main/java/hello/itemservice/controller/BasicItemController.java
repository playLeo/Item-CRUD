package hello.itemservice.controller;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemService itemService;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemService.findItem(itemId));
        return "basic/deleteForm";
    }

    //PRG 패턴 적용
    @PostMapping("/{itemId}/delete")
    public String itemDelete(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return "redirect:/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }


    //PRG 패턴 적용 RedirectAttributes를 사용해서 일정정보를 같이 넘길 수 있다.
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemService.saveItem(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemService.updateItem(itemId, item);
        return "redirect:/items/{itemId}";
    }
}

