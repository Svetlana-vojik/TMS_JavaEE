package by.teachmeskills.hw_12052023.service;

import by.teachmeskills.hw_12052023.exception.BankAccountNotFoundException;
import by.teachmeskills.hw_12052023.exception.MerchantNotFoundException;
import by.teachmeskills.hw_12052023.exception.NoBankAccountsFoundException;
import by.teachmeskills.hw_12052023.model.AccountStatus;
import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;
import by.teachmeskills.hw_12052023.utils.CRUDUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MerchantService {
    public void addBankAccount(Merchant merchant, String accountNumber) {
        BankAccount bankAccount = new BankAccount(merchant.getId(), accountNumber);
        validateBankAccountNumber(accountNumber);
        List<BankAccount> bankAccounts = CRUDUtils.getMerchantBankAccounts(merchant);
        Optional<BankAccount> accountOfBank = bankAccounts.stream().filter(s -> s.getAccountNumber().equals(bankAccount.getAccountNumber())).findAny();
        accountOfBank.ifPresentOrElse(a -> Optional.of(a).filter(s -> s.getStatus().equals(AccountStatus.DELETED)).ifPresent(s -> {
                    s.setStatus(AccountStatus.ACTIVE);
                    CRUDUtils.updateStatusMerchantBankAccount(s);
                }),
                () -> CRUDUtils.createBankAccount(bankAccount));
    }

    public static List<BankAccount> getMerchantBankAccounts(Merchant merchant) throws NoBankAccountsFoundException {
        List<BankAccount> accounts = CRUDUtils.getMerchantBankAccounts(merchant);
        if (accounts.isEmpty()) {
            throw new NoBankAccountsFoundException("У этого пользователя нет аккаунта");
        }
        return accounts;
    }

    public void updateBankAccount(String accountNumber, String newAccountNumber, String merchantID) throws BankAccountNotFoundException {
        Merchant merchant = CRUDUtils.getMerchantById(merchantID);
        List<BankAccount> accounts = CRUDUtils.getMerchantBankAccounts(merchant);
        BankAccount account = accounts.stream().filter(s -> s.getAccountNumber().equals(accountNumber)).findAny().orElseThrow(() -> new BankAccountNotFoundException("No bank account found!"));
        account.setAccountNumber(newAccountNumber);
        CRUDUtils.updateMerchantBankAccount(accountNumber, newAccountNumber, merchantID);
    }

    public boolean deleteBankAccount(String accountNumber, Merchant merchant) throws BankAccountNotFoundException {
        List<BankAccount> listBank = CRUDUtils.getMerchantBankAccounts(merchant);
        BankAccount account = listBank.stream().filter(s -> s.getAccountNumber().equals(accountNumber)).findAny().orElse(null);
        if (account == null) {
            throw new BankAccountNotFoundException("No bank account found!");
        }
        CRUDUtils.deleteBankAccountById(accountNumber);
        return true;
    }

    public Merchant createMerchant(String id, String nameMerchantSc, LocalDateTime createdAt) {
        Merchant merchant = new Merchant(id, nameMerchantSc, createdAt);
        CRUDUtils.createMerchant(id, nameMerchantSc, createdAt);
        return merchant;
    }

    public List<Merchant> getMerchants() {
        List<Merchant> merchants = CRUDUtils.getAllMerchants();
        return merchants;
    }

    public Merchant getMerchantsById(String merchantID) throws MerchantNotFoundException {
        Merchant merchant = CRUDUtils.getMerchantById(merchantID);
        if (merchant == null) {
            throw new MerchantNotFoundException("Мерчант с ID " + merchantID + " отсутствует в базе.\n");
        }
        return merchant;
    }

    public boolean deleteMerchant(String merchantId) throws MerchantNotFoundException {
        Merchant merchant = CRUDUtils.getMerchantById(merchantId);
        if (merchant == null) {
            throw new MerchantNotFoundException("Такого пользователя нет");
        }
        return CRUDUtils.deleteMerchantById(merchantId);
    }

    private static boolean validateBankAccountNumber(String accountNumber) {
        if (accountNumber.length() != 8 && accountNumber.matches("^\\d+")) {
            throw new IllegalArgumentException("Номер банковского аккаунта неверный");
        } else {
            return true;
        }
    }
}