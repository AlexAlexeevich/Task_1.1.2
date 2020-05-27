package service;

import dao.UserDao;
import dao.UserHibernateDao;
import dao.UserJdbcDao;
import exception.DBException;
import factory.UserDaoFactory;
import models.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class UserService {

    private final UserDao userDao;
    private static UserService instance;

    private UserService() {
        userDao = UserDaoFactory.createDao();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<User> getAllClient() {
        return userDao.getAllUsers();
    }

    public boolean isExist(String name, String password) throws SQLException {
        User temp = userDao.getUserByName(name);
        if (temp != null && temp.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addClient(User user) throws SQLException {
        if (user.getName().equals("") || user.getPassword().equals("")) {
            return false;
        }
        if (userDao.getUserByName(user.getName()) != null) {
            return false;
        }
        userDao.addUser(user);
        return true;
    }

    public void deleteUserById(Long id) throws SQLException {
        userDao.deleteUser(id);
    }

    public boolean updateClient(Long id, String login, String password) throws SQLException {
        if (login.equals("") || login.equals("")) {
            return false;
        }
        if (userDao.getUserByName(login) != null) {
            return false;
        }
        userDao.updateUser(id, login, password);
        return true;
    }

    public User getUserByLogin(String login) throws SQLException {
        return userDao.getUserByName(login);
    }

    public User getUserById(Long id) throws SQLException {
        return userDao.getUser(id);

    }

    public void createTable() throws SQLException {
        userDao.createTable();
    }

}
