package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDao implements UserDao {

    private static UserHibernateDao instance;
    private final SessionFactory sessionFactory = DBHelper.getConfiguration();

    private UserHibernateDao() {
    }

    public static UserHibernateDao getInstance() {
        if (instance == null) {
            instance = new UserHibernateDao();
        }
        return instance;
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        String hql = "FROM User WHERE name =: name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void createTable() {

    }

    public List getAllUsers() {
        Session session = sessionFactory.openSession();
        List temp = session.createQuery("FROM User").list();
        session.close();
        return temp;
    }

    public void deleteUser(Long id) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM User WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void updateUser(Long id, String name, String password) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE User SET name = :name, password = :password WHERE id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            query.setParameter("password", password);
            query.setParameter("id", id);
            int o = query.executeUpdate();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public User getUser(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        String hql = "FROM User WHERE id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }
}
