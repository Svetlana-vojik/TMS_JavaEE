package by.teachmeskills.hw_12052023.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class BankAccount {
    private String id;
    private String merchantId;
    private AccountStatus status;
    private String accountNumber;
    private LocalDateTime createdAt;

    public BankAccount(String id, String merchantId, AccountStatus status, String accountNumber, LocalDateTime createdAt) {
        this.id = UUID.randomUUID().toString();
        this.merchantId = merchantId;
        this.status = status;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
    }

    public BankAccount(String merchantId, String accountNumber) {
        this.id = UUID.randomUUID().toString();
        this.merchantId = merchantId;
        this.status = AccountStatus.ACTIVE;
        this.accountNumber = accountNumber;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                " merchantId='" + merchantId + '\'' +
                ", status=" + status +
                ", accountNumber='" + accountNumber + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}