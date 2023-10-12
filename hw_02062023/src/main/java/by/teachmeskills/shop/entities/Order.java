package by.teachmeskills.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@Data
@SuperBuilder
public class Order extends BaseEntity {
    private LocalDate date;
    private int price;
    private int userId;
    List<Product> products;

    public Order(int id, LocalDate date, List<Product> products) {
        this.id = id;
        this.date = date;
        this.products = products;
    }
}