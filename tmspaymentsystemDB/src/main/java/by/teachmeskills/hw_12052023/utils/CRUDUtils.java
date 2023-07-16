package by.teachmeskills.hw_12052023.utils;

import by.teachmeskills.hw_12052023.exception.MerchantNotFoundException;
import by.teachmeskills.hw_12052023.model.AccountStatus;
import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;
import by.teachmeskills.hw_12052023.service.MerchantService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    private final static String GET_MERCHANT_BANK_ACCOUNTS = "SELECT * FROM bank_accounts WHERE merchant_id = ? order by status ASC, created_at ASC";
    private final static String UPDATE_MERCHANT_BANK_ACCOUNT = "UPDATE bank_accounts SET account_number = ? WHERE merchant_id = ? and account_number = ?";
    private final static String ADD_BANK_ACCOUNT_QUERY = "INSERT INTO bank_accounts (id, merchant_id, status, account_number, created_at) values (?, ?, ?, ?, ?)";
    private final static String GET_BANK_ACCOUNT = "SELECT * FROM bank_accounts WHERE id = ?";
    private final static String DELETE_BANK_ACCOUNT = "UPDATE bank_accounts SET status = ? WHERE account_number = ?";
    private final static String ADD_MERCHANT = "INSERT INTO merchant (id, name, created_at) Values (?, ?, ?)";
    private final static String GET_ALL_MERCHANTS = "SELECT * FROM merchant";
    private final static String GET_MERCHANT_BY_ID = "SELECT * FROM merchant WHERE id = ?";
    private final static String DELETE_MERCHANT_ACCOUNT = "DELETE FROM merchant WHERE id = ?";
    private static final String UPDATE_STATUS_BANK_ACCOUNT = "UPDATE bank_accounts SET status = ? WHERE accountNumber = ?";


    private CRUDUtils() {
    }

    public static List<BankAccount> getMerchantBankAccounts(String merchantId) throws MerchantNotFoundException {
        Merchant merchant = MerchantService.getMerchantsById(merchantId);
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement psGet = connection.prepareStatement(GET_MERCHANT_BANK_ACCOUNTS)) {
            psGet.setString(1, merchant.getId());
            ResultSet set = psGet.executeQuery();
            while (set.next()) {
                bankAccounts.add(new BankAccount(set.getString(1),
                        set.getString(2),
                        ConverterUtils.toAccountStatus(set.getString(3)),
                        set.getString(4),
                        set.getTimestamp(5).toLocalDateTime()));
            }
            set.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bankAccounts;
    }

    public static void updateMerchantBankAccount(String accountNumber, String newAccountNumber, String merchantId) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_MERCHANT_BANK_ACCOUNT);
            statement.setString(1, newAccountNumber);
            statement.setString(2, merchantId);
            statement.setString(3, accountNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createBankAccount(BankAccount bankAccount) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_BANK_ACCOUNT_QUERY);
            statement.setString(1, bankAccount.getId());
            statement.setString(2, bankAccount.getMerchantId());
            statement.setString(3, bankAccount.getStatus().name());
            statement.setString(4, bankAccount.getAccountNumber());
            statement.setTimestamp(5, Timestamp.valueOf(bankAccount.getCreatedAt()));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static BankAccount getBankAccountById(String id) {
        BankAccount bankAccount = null;
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BANK_ACCOUNT);
            statement.setString(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                bankAccount = new BankAccount(set.getString(1), set.getString(2), ConverterUtils.toAccountStatus(set.getString(3)),
                        EncryptionUtils.decrypt(set.getString(4)), set.getTimestamp(5).toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bankAccount;
    }


    public static void deleteBankAccountById(String accountNumber) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_BANK_ACCOUNT);
            statement.setString(1, AccountStatus.DELETED.toString());
            statement.setString(2, accountNumber);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createMerchant(String id, String nameMerchantSc, LocalDateTime createdAt) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_MERCHANT);
            statement.setString(1, id);
            statement.setString(2, nameMerchantSc);
            statement.setTimestamp(3, Timestamp.valueOf(createdAt));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Merchant> getAllMerchants() {
        List<Merchant> merchants = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_MERCHANTS);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                merchants.add(new Merchant(set.getString(1),
                        set.getString(2),
                        set.getTimestamp(3).toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return merchants;
    }

    public static Merchant getMerchantById(String merchantID) {
        Merchant merchant = null;
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_MERCHANT_BY_ID);
            statement.setString(1, merchantID);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                merchant = new Merchant(set.getString(1), set.getString(2), set.getTimestamp(3).toLocalDateTime());
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return merchant;
    }

    public static boolean deleteMerchantById(String merchantID) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_MERCHANT_ACCOUNT);
            statement.setString(1, merchantID);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static void updateStatusMerchantBankAccount(BankAccount bankAccount) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_BANK_ACCOUNT);
            statement.setString(1, bankAccount.getStatus().toString());
            statement.setString(2, bankAccount.getAccountNumber());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}