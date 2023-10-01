package by.teachmeskills.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
public class Category extends BaseEntity {
    private int id;
    private String name;
    private String imagePath;
    private int rating;
    private List<Product> productList;
}