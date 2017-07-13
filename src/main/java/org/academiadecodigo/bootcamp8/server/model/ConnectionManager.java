package org.academiadecodigo.bootcamp8.server.model;

import org.academiadecodigo.bootcamp8.server.model.Utils.Queries;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
    public boolean insertUser(String username, String password) {

        boolean registered = true;
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(Queries.INSERT_USER);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();
            statement = connection.prepareStatement(Queries.INSERT_INTO_BIO);
            statement.setString(1, username);
            statement.executeUpdate();
            statement = connection.prepareStatement(Queries.INSERT_ACCOUNT);
            statement.setString(1, username);
            statement.setString(2, "5");      // Remove magic number from here
            statement.executeUpdate();


        } catch (SQLException e) {
            registered = false;

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            }
        }

        return registered;
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
     * Changes the password of a username
     *
     * @param username to target password change
     * @param newPass  new password
     * @return true if the actions was successful
     */
    public boolean changePass(String username, String newPass) {

        boolean passChanged = true;
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(Queries.ALTER_PASSWORD);
            statement.setString(1, newPass);
            statement.setString(2, username);
            statement.execute();

        } catch (SQLException e1) {

            passChanged = false;

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            }
        }

        return passChanged;
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

    /**
     * Return the biography of a username
     *
     * @param username to target
     * @return an list with the username biography
     * @throws SQLException if exist an exception occurs when access the database
     */
    public List<String> getUserBio(String username) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(Queries.SHOW_BIO);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        List<String> userbio = new LinkedList<String>();

        if (resultSet.next()) {
            userbio.add(resultSet.getString("user_bio"));
            userbio.add(resultSet.getString("name"));
            userbio.add(resultSet.getString("email"));
            userbio.add(resultSet.getString("date_birth"));
            userbio.add(resultSet.getString("location"));
            userbio.add(resultSet.getString("bio"));
        }

        statement.close();

        return userbio;
    }

    /**
     * Updates the username biography
     *
     * @param username  username to target
     * @param email     new email information
     * @param dateBirth new date birth information
     * @param bio       new bio information
     * @param
     *@param  @return true if the operation was successful
     */
    public boolean updateBio(String username, String name , String email, String dateBirth, String location, String bio) {

        PreparedStatement statement = null;
        boolean updated = true;

        try {

            statement = connection.prepareStatement(Queries.UPDATE_BIO);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, dateBirth);
            statement.setString(4, location);
            statement.setString(5, bio);
            statement.execute();

        } catch (SQLException e) {
            updated = false;
        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            }

        }

        return updated;
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