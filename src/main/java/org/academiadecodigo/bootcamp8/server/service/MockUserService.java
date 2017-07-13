package org.academiadecodigo.bootcamp8.server.service;

import org.academiadecodigo.bootcamp8.server.model.User;

/**
 * Created by Prashanta on 13/07/17.
 */
public class MockUserService implements UserService  {

    @Override
    public void addUser(User user) {

    }

    @Override
    public boolean authenticate(String username, String pass) {
        return false;
    }

    @Override
    public User getUser(String username) {
        return null;
    }
}
