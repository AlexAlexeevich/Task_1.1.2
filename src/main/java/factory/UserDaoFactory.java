package factory;

import dao.UserDao;
import dao.UserHibernateDao;
import dao.UserJdbcDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDao createDao() {
        Properties properties = new Properties();
        FileInputStream fis;
        String string = "";
        try {
            fis = new FileInputStream("C:\\Users\\Alex\\Desktop\\test\\src\\main\\resources\\config.properties");
            properties.load(fis);
            string = properties.getProperty("daotype");
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println();
        }

        UserDao userDao = null;
        switch (string) {
            case "hibernate":
                userDao = UserHibernateDao.getInstance();
                break;
            case "jdbc":
                userDao = UserJdbcDao.getInstance();
        }
        return userDao;
    }
}
