package service;

import dao.UserJdbcDao;
import exception.DBException;
import models.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserJdbcDao userJdbcDao = UserJdbcDao.getInstance(getMysqlConnection());
    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<User> getAllClient() {
        return userJdbcDao.getAllUsers();
    }

    public boolean addClient(User user) throws SQLException {
        if (user.getName().equals("") || user.getPassword().equals("")) {
            return false;
        }
        List<User> users = getAllClient();
        for (User temp : users) {
            if (temp.getName().equals(user.getName())) {
                return false;
            }
        }
        userJdbcDao.addUser(user);
        return true;
    }

    public void deleteClient(User user) throws SQLException {
        List<User> users = getAllClient();
        for (User temp : users) {
            if (temp.getName().equals(user.getName())) {
                if (temp.getPassword().equals(user.getPassword())) {
                    userJdbcDao.deleteUser(user);
                }
            }
        }
    }

    public boolean updateClient(User user, String oldName) throws SQLException {
        if (user.getName().equals("") || user.getPassword().equals("")) {
            return false;
        }
        List<User> users = getAllClient();
        for (User temp : users) {
            if (temp.getName().equals(oldName)) {
                System.out.println(user.getName() + ", " + user.getPassword() + "======" + oldName + " " + temp.getPassword());
                userJdbcDao.updateUser(user, new User(oldName, temp.getPassword()));
                return true;
            }
        }
        return false;
    }

//    public void createTables() throws DBException {
//        try {
//            userJdbcDao.createTable();
//        } catch (SQLException e) {
//            throw new DBException(e);
//        }
//    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root").        //password
                    append("&serverTimezone=UTC");

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }


}
