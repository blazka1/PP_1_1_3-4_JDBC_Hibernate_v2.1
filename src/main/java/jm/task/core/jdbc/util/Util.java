package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    private static final Logger logger = Logger.getLogger(Util.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/new_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("Попытка установить соединение...");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Соединение успешно установлено.");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Драйвер не найден: {0}", e.getMessage());
            throw new RuntimeException("Драйвер не найден", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка подключения: {0}", e.getMessage());
            throw new RuntimeException("Ошибка подключения к базе данных", e);
        }
        return connection;
    }
}
