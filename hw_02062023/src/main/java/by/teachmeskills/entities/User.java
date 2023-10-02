package by.teachmeskills.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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
    private LocalDate birthday;
    private int balance;
    private String address;
}