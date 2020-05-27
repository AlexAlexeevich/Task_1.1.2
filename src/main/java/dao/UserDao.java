package dao;

import models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void addUser(User user) throws SQLException;
    List getAllUsers();
    void deleteUser(Long id) throws SQLException;
    void updateUser(Long id, String name, String password) throws SQLException;
    User getUser(Long id) throws SQLException;
    void createTable() throws SQLException;
    User getUserByName(String name) throws SQLException;
}
