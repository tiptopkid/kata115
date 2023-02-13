package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        final String createTable = """
                CREATE TABLE IF NOT EXISTS `katappdb`.`user` (
                  `ID` INT NOT NULL AUTO_INCREMENT,
                  `NAME` VARCHAR(45) NULL,
                  `LASTNAME` VARCHAR(45) NULL,
                  `AGE` INT(3) NULL,
                  PRIMARY KEY (`ID`))""";
        try(SessionFactory sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
        }

    }

    @Override
    public void dropUsersTable() {
        final String dropTable = "DROP TABLE IF EXISTS user";
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(dropTable).executeUpdate();

        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class , id);
            session.delete(user);


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            return session.createQuery("from User" , User.class).list();
        }

    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
