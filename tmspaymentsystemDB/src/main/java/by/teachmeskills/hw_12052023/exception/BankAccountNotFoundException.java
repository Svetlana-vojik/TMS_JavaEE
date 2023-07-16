package by.teachmeskills.hw_12052023.exception;

public class BankAccountNotFoundException extends Throwable {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}