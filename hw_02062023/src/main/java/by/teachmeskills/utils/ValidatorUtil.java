package by.teachmeskills.utils;

import by.teachmeskills.exceptions.RequestParamNullException;

import java.util.Arrays;
import java.util.Objects;

public class ValidatorUtil {

    private ValidatorUtil() {
    }

    private static final String REQUEST_PARAMETER_IS_NULL_ERROR = "Request parameter is not initialized!";

    public static boolean validateRegistration(String email, String name, String surname, String birthday) {
        return validateName(name) && validateSurname(surname)
                && validateBirthday(birthday)
                && validateEmail(email);
    }

    private static boolean validateEmail(String email) {
        return email.matches("^(.+)@(\\S+)$");
    }

    private static boolean validateName(String name) {
        return name.matches("[A-Za-z А-Яа-я]+");
    }

    private static boolean validateSurname(String surname) {
        return surname.matches("[A-Za-z А-Яа-я]+");
    }

    private static boolean validateBirthday(String birthday) {
        return birthday.matches("\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])*");
    }

    public static void validateParamNotNull(String... parameters) throws RequestParamNullException {
        if (Arrays.stream(parameters).anyMatch(Objects::isNull)) {
            throw new RequestParamNullException(REQUEST_PARAMETER_IS_NULL_ERROR);
        }
    }
}