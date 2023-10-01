package by.teachmeskills.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String birthday;
    private int balance;
    private String address;

    public User(String email, String password, String name, String surname, String birthday, String address) {
    }
}