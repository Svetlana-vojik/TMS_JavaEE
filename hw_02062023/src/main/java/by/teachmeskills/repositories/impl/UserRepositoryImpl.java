package by.teachmeskills.repositories.impl;

import by.teachmeskills.entities.User;
import by.teachmeskills.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final static Logger log = LogManager.getLogger(UserRepositoryImpl.class);
    private static final String ADD_USER = "INSERT INTO shop.users (email,password,name,surname,birthday,balance,address) values (?,?,?,?,?,?,?)";
    private final static String GET_ALL_USERS = "SELECT * FROM  shop.users";
    private final static String UPDATE_ADDRESS = "UPDATE  shop.users SET address = ? WHERE id = ?";
    private final static String DELETE_USER = "DELETE FROM shop.users WHERE id=?";
    private static final String GET_USER_BY_ID = "SELECT * FROM shop.users WHERE id=?";
    private final static String GET_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM shop.users WHERE email=? and password=?";

    @Override
    public User create(User entity) {
        Connection connection = pool.getConnection();
        try (PreparedStatement psInsert = connection.prepareStatement(ADD_USER)) {
            psInsert.setString(1, entity.getEmail());
            psInsert.setString(2, entity.getPassword());
            psInsert.setString(3, entity.getName());
            psInsert.setString(4, entity.getSurname());
            psInsert.setString(5, String.valueOf(entity.getBirthday()));
            psInsert.setInt(6, entity.getBalance());
            psInsert.setString(7, entity.getAddress());
            psInsert.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public List<User> read() {
        List<User> users = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(User.builder().id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .birthday(LocalDate.parse(resultSet.getString("birthday")))
                        .balance(resultSet.getInt("balance"))
                        .address(resultSet.getString("address"))
                        .build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return users;
    }

    @Override
    public User update(User entity) {
        int userId = entity.getId();
        Connection connection = pool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS)) {
            preparedStatement.setString(1, entity.getAddress());
            preparedStatement.setInt(2, userId);
            preparedStatement.execute();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public void delete(int id) {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
    }

    @Override
    public User findById(int id) {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            User user = User.builder().id(resultSet.getInt("id"))
                    .email(resultSet.getString("email"))
                    .password(resultSet.getString("password"))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .birthday(LocalDate.parse(resultSet.getString("birthday")))
                    .balance(resultSet.getInt("balance"))
                    .address(resultSet.getString("address")).build();
            return user;
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_EMAIL_AND_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return User.builder().id(resultSet.getInt("id")).email(resultSet.getString("email"))
                    .password(resultSet.getString("password"))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .birthday(LocalDate.parse(resultSet.getString("birthday")))
                    .balance(resultSet.getInt("balance"))
                    .address(resultSet.getString("address")).build();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return null;
    }
}