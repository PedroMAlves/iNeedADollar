package org.academiadecodigo.bootcamp8.server.model.Utils;

/**
 * Created by Jramos on 30-06-2017.
 */
public class Queries {


    // Querys users are ok need to change bio querys

    public static final String SELECT_USER = "SELECT * FROM user WHERE user_name= ?";
    public static final String DELETE_USER = "DELETE FROM user USING user WHERE user.user_name =?";
    public static final String ALTER_PASSWORD = "UPDATE user SET user.user_password = ? WHERE user_name=?";
    public static final String INSERT_USER = "INSERT INTO user(user_name, user_password) VALUES(?, ?)";
    public static final String COUNT_USERS = "SELECT COUNT(*) FROM users";

    public static final String INSERT_NEW_BIO = "INSERT INTO bio(user_bio, email) VALUES (?,?)";

    public static final String INSERT_ACCOUNT = "INSERT INTO account(user_account, balance) VALUES (?, ?)";


    public static final String INSERT_REQUEST = "INSERT INTO request(user_name_request, request_motiv, state) VALUES (?, ?, ?)";
    public static final String UPDATE_REQUEST = "UPDATE request SET user_name_answer = ? , operation_id = ? , request_answer = ?, state = ? WHERE request_id = ?";
    public static final String ACTIVE_REQUEST = "SELECT * FROM request WHERE state = ?";
    public static final String BALANCE = "SELECT balance FROM account WHERE user_account = ?";
    //public static final String OPERATIONS = "SELECT * FROM OPERATION WHERE user_account = ?";
    public static final String OPERATIONS = "SELECT * FROM operation INNER JOIN request ON operation.request_id = request.request_id WHERE operation.user_account = ?";


}

