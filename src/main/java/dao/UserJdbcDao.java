package dao;

import models.User;
import org.hibernate.Transaction;
import util.DBHelper;

import java.sql.*;
import java.util.*;

public class UserJdbcDao implements UserDao {

    private static UserJdbcDao instance;
    private final Connection connection = DBHelper.getConnection();

    private UserJdbcDao() {
    }

    public static UserJdbcDao getInstance() {
        if (instance == null) {
            instance = new UserJdbcDao();
        }
        return instance;
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("insert into users (name, password) values(?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.setAutoCommit(true);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("select id, name, password from users");
            while (result.next()) {
                users.add(new User(result.getLong(1), result.getString(2), result.getString(3)));
            }
        } catch (SQLException e) {
        }
        return users;
    }

    public void deleteUser(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("delete from users where name = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.setAutoCommit(true);
        }
    }

    public void updateUser(User user, User oldUser) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("update users set name= ?, password=? where name= ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, oldUser.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.setAutoCommit(true);
        }
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256),  password varchar(256), primary key (id))");
        stmt.close();
    }
}
