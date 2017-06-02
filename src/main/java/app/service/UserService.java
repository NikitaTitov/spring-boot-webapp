package app.service;


import app.model.User;

import java.util.List;

public interface UserService {
    User getUserByName(String name);

    User getUserById(Long id);

    List<User> getAllUsers();

    boolean addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
