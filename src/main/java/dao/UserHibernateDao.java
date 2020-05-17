package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DBHelper;

import java.util.List;

public class UserHibernateDao implements UserDao {

    private static UserHibernateDao instance;
    private SessionFactory sessionFactory = DBHelper.getSessionFactory();

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

    public List getAllUsers() {
        Session session = sessionFactory.openSession();
        List temp = session.createQuery("FROM User").list();
        session.close();
        return temp;
    }

    public void deleteUser(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM User WHERE name = :name and password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("name", user.getName());
            query.setParameter("password", user.getPassword());
            query.executeUpdate();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void updateUser(User user, User oldUser) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE User SET name = :name, password = :password WHERE id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("name", user.getName());
            query.setParameter("password", user.getPassword());
            query.setParameter("id", oldUser.getId());
            System.out.println(user.getName() + " " + user.getPassword() + " " + oldUser.getId());
            int o = query.executeUpdate();
            System.out.println(o);
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
