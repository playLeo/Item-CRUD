package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    public void deleteById(Long id) {
        Item item = em.find(Item.class, id);
        em.remove(item);
    }

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public void update(Long itemId, Item updateParam) {
        Item savedItem = em.createQuery("select i from Item i where i.id = :id", Item.class)
                .setParameter("id", itemId)
                .getSingleResult();

        savedItem.setItemName(updateParam.getItemName());
        savedItem.setPrice(updateParam.getPrice());
        savedItem.setQuantity(updateParam.getQuantity());
    }

}
