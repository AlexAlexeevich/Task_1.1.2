package dao;

import models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void addUser(User user) throws SQLException;
    List getAllUsers();
    void deleteUser(User user) throws SQLException;
    void updateUser(User user, User oldUser) throws SQLException;
    User getUser(String name) throws SQLException;
}
