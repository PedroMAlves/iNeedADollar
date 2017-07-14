package org.academiadecodigo.bootcamp8.server.service;

import org.academiadecodigo.bootcamp8.server.model.User;

import java.util.List;

/**
 * Created by Prashanta on 13/07/17.
 */
public interface UserService {


    boolean authenticate(String username, String password);

    boolean addUser(User user);

    boolean updateBio(List<String> updatedBio);

    boolean deleteAccount(String username, String password);

    User getUser(String username);

    boolean changePassword(String username, String oldPass, String newPass);

    List<String> getUserBio(String username);

    String getBalance(String username);
}
