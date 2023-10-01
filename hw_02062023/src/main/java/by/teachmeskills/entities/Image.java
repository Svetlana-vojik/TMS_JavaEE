package by.teachmeskills.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Image extends BaseEntity {
    private String imagePath;
    private int categoryId;
    private int productId;
    private boolean primary;
}