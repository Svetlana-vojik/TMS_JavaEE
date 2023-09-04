package by.teachmeskills.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
@AllArgsConstructor

public class Category {
    private String id;
    private String name;
    private String imageName;
    private List<Product> productList;
}