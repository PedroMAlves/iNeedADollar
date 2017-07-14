package org.academiadecodigo.bootcamp8.server.model;

import org.academiadecodigo.bootcamp8.server.model.Utils.Queries;

import java.sql.*;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> JPM Ramos
 */

public class ConnectionManager {
    private Connection connection;

    public ConnectionManager() {
        connection = getConnection();
    }

    /**
     * Gets the connection to the DB
     *
     * @return the connection
     */
    public Connection getConnection() {

        final String URL = "jdbc:mysql://localhost:3306/dollar";
        final String USER = "root";
        final String PASSWORD = "";

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        return connection;
    }

    /**
     * Inserts a user to the DB
     *
     * @param username Username of the client
     * @param password Password of the client
     * @return true if the operation was successful
     */
    public boolean insertUser(String username, String password, String email) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(Queries.INSERT_USER);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();
            statement = connection.prepareStatement(Queries.INSERT_NEW_BIO);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.execute();
            statement = connection.prepareStatement(Queries.INSERT_ACCOUNT);
            statement.setString(1, username);
            statement.setString(2, "5");      // Remove magic number from here
            statement.execute();
            return true;


        } catch (SQLException e) {
            System.err.println("SQL exception " + e.getMessage());;
            return false;

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     * Finds the user by his username
     *
     * @param username User's name
     * @return the User if he exists and null otherwise
     * @throws SQLException if exist an exception occurs when access the database
     */
    public User findUser(String username) throws SQLException {

        User user = null;
        System.out.println(username);
        PreparedStatement statement = connection.prepareStatement(Queries.SELECT_USER);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        System.out.println(resultSet);

        if (resultSet.next()) {

            String usernameValue = resultSet.getString("user_name");
            String passwordValue = resultSet.getString("user_password");
            user = new User(usernameValue, passwordValue);
        }

        statement.close();
        return user;
    }


    /**
     * Delete the account of a specific username
     *
     * @param username the username to target
     * @return true if the operation was successful
     */
    public boolean deleteAccount(String username) {

        boolean deleted = true;
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(Queries.DELETE_USER);
            statement.setString(1, username);
            statement.execute();

        } catch (SQLException e1) {

            deleted = false;

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            }

        }

        return deleted;
    }

    /**
     * Return the number of user registered on the database
     *
     * @return the count of users
     * @throws SQLException if exist an exception occurs when access the database
     */
    public int count() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Queries.COUNT_USERS);

        return resultSet.next() ? resultSet.getInt(1) : 0;

    }


    public String getBalance(String username) {
        PreparedStatement statement = null;
        String msg = null;

        try {

            statement = connection.prepareStatement(Queries.BALANCE);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                msg = resultSet.getString("balance");
            }

        } catch (SQLException e1) {

            System.err.println("Database error.");

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            }

        }
        return msg;
    }

    public boolean addRequest(String user, String request) {
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(Queries.INSERT_REQUEST);
            statement.setString(1, user);
            statement.setString(2, request);
            statement.setString(3, "Active");
            statement.execute();


        } catch (SQLException e) {
            System.err.println("SQL exception " + e.getMessage());;
            return false;

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            }
        }
        return true;
    }


    /**
     * Close the connection to the database
     */
    public void close() {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

}