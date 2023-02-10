package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        final String createTable = """
                CREATE TABLE IF NOT EXISTS `katappdb`.`user` (
                  `ID` INT NOT NULL AUTO_INCREMENT,
                  `NAME` VARCHAR(45) NULL,
                  `LASTNAME` VARCHAR(45) NULL,
                  `AGE` INT(3) NULL,
                  PRIMARY KEY (`ID`))""";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(createTable)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {


        final String dropTable = "DROP TABLE IF EXISTS user";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(dropTable)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {


        final String insertNew = "INSERT INTO USER (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insertNew)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем - " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {


        String removeByID = "delete from user where id = ?";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(removeByID)) {
            //preparedStatement = ;
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {


        List<User> userList = new ArrayList<>();
        String getAllUsers = "SELECT ID, NAME, LASTNAME, AGE FROM user";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getAllUsers);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
            System.out.println(userList);
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void cleanUsersTable() {

        String deleteAll = "DELETE FROM USER";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(deleteAll)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
