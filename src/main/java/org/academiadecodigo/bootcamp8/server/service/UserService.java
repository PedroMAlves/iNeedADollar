package org.academiadecodigo.bootcamp8.server.service;

import org.academiadecodigo.bootcamp8.server.model.User;

/**
 * Created by Prashanta on 13/07/17.
 */
public interface UserService {

    void addUser(User user);

    boolean authenticate(String username, String pass);

    User getUser(String username);



}
