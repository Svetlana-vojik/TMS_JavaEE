package by.teachmeskills.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String birthday;
}