package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemRepository.findById(itemId));
        return "basic/deleteForm";
    }

    //PRG 패턴 적용
    @PostMapping("/{itemId}/delete")
    public String itemDelete(@PathVariable Long itemId) {
        itemRepository.deleteById(itemId);
        return "redirect:/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {

        itemRepository.save(item);
//      model.addAttribute("item", item); //자동 추가, 생략 가능

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/items/" + item.getId();
    }

    //PRG 패턴 적용 RedirectAttributes를 사용해서 일정정보를 같이 넘길 수 있다.
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
//    @PostConstruct
//    public void init() {
//        itemRepository.save(new Item("itemA", 10000, 10));
//        itemRepository.save(new Item("itemB", 20000, 20));
//        itemRepository.save(new Item("itemC", 10000, 30));
//        itemRepository.save(new Item("itemD", 20000, 40));
//        itemRepository.save(new Item("itemE", 10000, 40));
//        itemRepository.save(new Item("itemF", 20000, 250));
//        itemRepository.save(new Item("itemG", 10000, 10));
//        itemRepository.save(new Item("itemH", 20000, 20));
//        itemRepository.save(new Item("itemI", 10000, 10));
//        itemRepository.save(new Item("itemJ", 20000, 220));
//        itemRepository.save(new Item("itemK", 10000, 10));
//        itemRepository.save(new Item("itemL", 20000, 220));
//        itemRepository.save(new Item("itemM", 300, 105));
//        itemRepository.save(new Item("itemN", 20000, 270));
//        itemRepository.save(new Item("itemO", 10000, 109));
//        itemRepository.save(new Item("itemP", 2000, 203));
//        itemRepository.save(new Item("itemQ", 1000, 180));
//        itemRepository.save(new Item("itemR", 2000, 202));
//    }

}

