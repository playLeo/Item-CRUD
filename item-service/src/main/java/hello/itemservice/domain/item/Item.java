package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name="item")
public class Item {

    @Id @GeneratedValue
    private Long id;
    private String itemName;
    private int price;
    private int quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
