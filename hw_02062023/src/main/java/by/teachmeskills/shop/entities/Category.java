package by.teachmeskills.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
public class Category extends BaseEntity {
    private String name;
    private String imagePath;
    private int rating;
    private List<Product> productList;
}