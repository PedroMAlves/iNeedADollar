package org.academiadecodigo.bootcamp8.server.model.Utils;

/**
 * Created by Jramos on 30-06-2017.
 */
public class Queries {


    // Querys users are ok need to change bio querys

    public static final String SELECT_USER = "SELECT * FROM user WHERE user_name=?";
    public static final String DELETE_USER = "DELETE FROM user USING user WHERE user.user_name =?";
    public static final String ALTER_PASSWORD = "UPDATE user SET user.user_password = ? WHERE user_name=?";
    public static final String INSERT_USER = "INSERT INTO user(user_name, user_password) VALUES(?, ?)";
    public static final String COUNT_USERS = "SELECT COUNT(*) FROM users";

    public static final String INSERT_INTO_BIO = "INSERT INTO bio(user_name, name, email, date_birth, location, bio) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_BIO = "UPDATE bio SET name = ?, email = ? , date_birth = ? , location =? , bio = ? WHERE user_name = ? ";
    public static final String SHOW_BIO = "SELECT * FROM bio WHERE user_name = ?";

    public static final String INSERT_ACCOUNT = "INSERT INTO account(user_name, balance) VALUES (?, ?)";

}

