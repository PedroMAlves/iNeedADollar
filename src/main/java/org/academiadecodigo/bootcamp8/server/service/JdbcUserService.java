package org.academiadecodigo.bootcamp8.server.service;

import org.academiadecodigo.bootcamp8.server.model.ConnectionManager;
import org.academiadecodigo.bootcamp8.server.model.User;



import java.sql.SQLException;
import java.util.List;


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
        return connectionManager.insertUser(user.getUsername(), user.getPassword());
    }


    @Override
    public boolean updateBio(List<String> updatedBio) {
        return connectionManager.updateBio(updatedBio.get(0), updatedBio.get(1), updatedBio.get(2), updatedBio.get(3), updatedBio.get(4),updatedBio.get(5));
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
    public boolean changePassword(String username, String oldPass, String newPass) {
        return authenticate(username, oldPass) && connectionManager.changePass(username, newPass);
    }

    @Override
    public List<String> getUserBio(String username) {

        List<String> message = null;

        try {
            message = connectionManager.getUserBio(username);

        } catch (SQLException e) {

        }

        return message;

    }



}
