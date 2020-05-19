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

    public boolean addClient(User user) throws SQLException {
        if (user.getName().equals("") || user.getPassword().equals("")) {
            return false;
        }
        System.out.println(1);
        if (userDao.getUser(user.getName()) != null) {
            return false;
        }
        userDao.addUser(user);
        return true;
    }

    public void deleteClient(User user) throws SQLException {
        userDao.deleteUser(user);
    }

    public boolean updateClient(User user, String oldName) throws SQLException {
        if (user.getName().equals("") || user.getPassword().equals("")) {
            return false;
        }
        User temp = userDao.getUser(oldName);
        if (temp == null) {
            return false;
        }
        userDao.updateUser(user, new User(temp.getId(), oldName, temp.getPassword()));
        return true;
    }

//    public void createTables() throws DBException {
//        try {
//            userJdbcDao.createTable();
//        } catch (SQLException e) {
//            throw new DBException(e);
//        }
//    }

}
