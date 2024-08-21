package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userServiceImpl = new UserServiceImpl();

        userServiceImpl.createUsersTable();

        User user1 = new User("test1", "test11", (byte) 1);
        User user2 = new User("test2", "test22", (byte) 2);
        User user3 = new User("test3", "test33", (byte) 3);
        User user4 = new User("test4", "test44", (byte) 4);

        userServiceImpl.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println(user1.getName() + " добавлен в базу данных");

        userServiceImpl.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println(user2.getName() + " добавлен в базу данных");

        userServiceImpl.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println(user3.getName() + " добавлен в базу данных");

        userServiceImpl.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println(user4.getName() + " добавлен в базу данных");

        System.out.println("Все пользователи из базы данных: " + userServiceImpl.getAllUsers());

        userServiceImpl.cleanUsersTable();
        System.out.println("Таблица пользователей очищена");

        userServiceImpl.dropUsersTable();
        System.out.println("Таблица пользователей удалена");
    }
}
