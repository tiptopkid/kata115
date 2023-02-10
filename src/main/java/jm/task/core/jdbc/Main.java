package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Jane", "Air", (byte) 18);
        userService.saveUser("Harry", "Potter", (byte) 16);
        userService.saveUser("Joe", "Black", (byte) 24);
        userService.saveUser("John","Doe", (byte) 33);
        userService.getAllUsers();
        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
