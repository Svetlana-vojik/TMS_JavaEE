package by.teachmeskills.entities;

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
public class Order extends BaseEntity{
    int id;
    LocalDate orderDate;
    List<Product> products;
}