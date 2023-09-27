package by.teachmeskills.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@ToString
@AllArgsConstructor
@Data
public class Order {
    int id;
    LocalDate orderDate;
    List<Product> products;
}