package by.teachmeskills.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;
@Builder
@Data
@AllArgsConstructor
public class User {
    private String login;
    private String password;
}