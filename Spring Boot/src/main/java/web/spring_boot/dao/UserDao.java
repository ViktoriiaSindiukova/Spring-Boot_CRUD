package web.spring_boot.dao;

import web.spring_boot.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    List<User> getAllUsers();

    void updateUser(User editedUser, int id);

    User getUserById(int id);

    void removeUserById(int id);

}