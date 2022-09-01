package hello.itemservice.repository;

import hello.itemservice.domain.Order;
import hello.itemservice.domain.OrderItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    EntityManager em;

    public Order saveOrder(Order order) {
        em.persist(order);
        return order;
    }

    public List<Order> findAll() {
        return em.createQuery("select o from order o", Order.class)
                .getResultList();
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public void update(Long id, List<OrderItem> orderItems) {
        Order orderA = em.find(Order.class, id);
        orderA.setOrderItems(orderItems);
    }
}
