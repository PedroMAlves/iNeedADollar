package org.academiadecodigo.bootcamp8.client.service.connectionservice;

import org.academiadecodigo.bootcamp8.client.service.Service;

/**
 * Created by Prashanta on 13/07/17.
 */
public interface ConnectionService extends Service {

    void authenticateUser(String username, String password);
    void close();

    String getReply();

    void registerUser(String username, String password, String email);

    void setLoggedUser(String loggedUser);

    void logoutUser();

}

