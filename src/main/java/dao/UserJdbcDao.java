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

    public void deleteUser(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setLong(1, id);
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

    public void updateUser(Long id, String name, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("update users set name= ?, password=? where id= ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setLong(3, id);
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

    @Override
    public User getUser(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User temp = null;
        if(resultSet.next()) {
            temp = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
        }
        preparedStatement.close();
        resultSet.close();
        return temp;
    }

    public User getUserByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name=?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        User temp = null;
        if(resultSet.next()) {
            temp = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }
        preparedStatement.close();
        resultSet.close();
        return temp;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256),  password varchar(256), role varchar(256), primary key (id))");
        stmt.close();
    }

}
