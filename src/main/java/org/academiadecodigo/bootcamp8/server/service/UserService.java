package org.academiadecodigo.bootcamp8.server.service;

import org.academiadecodigo.bootcamp8.server.model.User;

/**
 * Created by Prashanta on 13/07/17.
 */
public interface UserService {


    boolean authenticate(String username, String password);

    boolean addUser(User user);

    boolean deleteAccount(String username, String password);

    User getUser(String username);

    String getBalance(String username);

    boolean addRequest(String user, String request);
}
