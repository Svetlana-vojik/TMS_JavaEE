package by.teachmeskills.hw_12052023.utils;

import by.teachmeskills.hw_12052023.model.AccountStatus;

public class ConverterUtils {
    private ConverterUtils() {
    }

    public static AccountStatus toAccountStatus(String status) {
        return status.equals("ACTIVE") ? AccountStatus.ACTIVE : AccountStatus.DELETED;
    }
}