package by.teachmeskills.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private String imageName;
}