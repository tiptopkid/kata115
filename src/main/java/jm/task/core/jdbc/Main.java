package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {

    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Jane", "Air", (byte) 18);
        userService.saveUser("Harry", "Potter", (byte) 16);
        userService.saveUser("Joe", "Black", (byte) 24);
        userService.saveUser("John","Doe", (byte) 33);
        System.out.println(userService.getAllUsers());
        userService.removeUserById(3);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();














    }
}
