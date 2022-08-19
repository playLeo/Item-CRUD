package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item findItem(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public void updateItem(long id, Item item) {
        itemRepository.update(id, item);
    }
}
