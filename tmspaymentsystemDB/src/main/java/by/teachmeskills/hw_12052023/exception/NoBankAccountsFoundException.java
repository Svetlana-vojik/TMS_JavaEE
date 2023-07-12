package by.teachmeskills.hw_12052023.exception;

public class NoBankAccountsFoundException extends Exception {
    public NoBankAccountsFoundException(String message) {
        super(message);
    }
}