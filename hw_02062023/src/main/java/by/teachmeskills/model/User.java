package by.teachmeskills.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Builder
@Data
@AllArgsConstructor
public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String birthday;
}