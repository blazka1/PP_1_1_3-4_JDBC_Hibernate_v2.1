package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/new_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Попытка установить соединение...");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Соединение успешно установлено.");
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер не найден: " + e.getMessage());
            throw new RuntimeException("Драйвер не найден", e);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения: " + e.getMessage());
            throw e;
        }
        return connection;
    }
}

