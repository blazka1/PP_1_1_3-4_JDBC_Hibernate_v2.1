package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    public UserDaoJDBCImpl() {
        // Пустой конструктор
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(20), " +
                "lastName VARCHAR(20), " +
                "age INT)";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            logger.info("Table 'Users' has been created!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating Users table", e);
            throw new RuntimeException("Error creating Users table", e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            logger.info("Table 'Users' has been deleted!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error dropping Users table", e);
            throw new RuntimeException("Error dropping Users table", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users (name, lastName, age) VALUES(?, ?, ?)";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            logger.info("User saved");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saving user", e);
            throw new RuntimeException("Error saving user", e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE Id = ?";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            logger.info("User removed");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error removing user", e);
            throw new RuntimeException("Error removing user", e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User(name, lastName, age);
                users.add(user);
            }

            logger.info("Users retrieved from the database!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving users", e);
            throw new RuntimeException("Error retrieving users", e);
        }

        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            logger.info("Table 'Users' has been cleaned!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error cleaning Users table", e);
            throw new RuntimeException("Error cleaning Users table", e);
        }
    }
}
