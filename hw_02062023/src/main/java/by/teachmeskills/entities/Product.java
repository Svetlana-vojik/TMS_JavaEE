package by.teachmeskills.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
public class Product extends BaseEntity{
    private int id;
    private String name;
    private String description;
    private int price;
    private int categoryId;
    private String imagePath;
}