package org.academiadecodigo.bootcamp8.server.service;

import org.academiadecodigo.bootcamp8.server.model.ConnectionManager;
import org.academiadecodigo.bootcamp8.server.model.User;
import java.sql.SQLException;

/**
 * Created by Jramos on 29-06-2017.
 */
public class JdbcUserService implements UserService {

    private ConnectionManager connectionManager;

    public JdbcUserService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public boolean addUser(User user) {
        if (getUser(user.getUsername()) != null) {
            return false;
        }
        return connectionManager.insertUser(user.getUsername(), user.getPassword(), user.getEmail());

    }


    @Override
    public boolean deleteAccount(String username, String password) {
        return (authenticate(username, password)) && connectionManager.deleteAccount(username);
    }

    @Override
    public User getUser(String username) {

        User user = null;
        try {
            user = connectionManager.findUser(username);
        } catch (SQLException e) {

        }
        return user;
    }


    @Override
    public String getBalance(String username) {
        return connectionManager.getBalance(username);
    }

    @Override
    public boolean addRequest(String user, String request) {
        return connectionManager.addRequest(user, request);
    }
}
