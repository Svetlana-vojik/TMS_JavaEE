package by.teachmeskills.services;

import by.teachmeskills.entities.User;

public interface UserService extends BaseService<User> {
    User findById(int id);
    User findByEmailAndPassword(String email, String password);
}