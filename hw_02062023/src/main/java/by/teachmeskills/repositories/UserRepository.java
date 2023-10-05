package by.teachmeskills.repositories;

import by.teachmeskills.entities.User;

public interface UserRepository extends BaseRepository<User> {

    User findById(int id);
    User findByEmailAndPassword(String email, String password);
}