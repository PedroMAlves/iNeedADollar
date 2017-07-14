package org.academiadecodigo.bootcamp8.client.service.connectionservice;

import org.academiadecodigo.bootcamp8.shared.message.Message;
import org.academiadecodigo.bootcamp8.shared.message.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Prashanta on 13/07/17.
 */
public class ConnectionServiceImpl implements ConnectionService {

    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    private String loggedUser;


    public ConnectionServiceImpl() {
        try {
            socket = new Socket("127.0.0.1", 4040);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            System.err.println("Unable to connect to server");
        }
    }


    @Override
    public void authenticateUser(String username, String password) {
        String[] msgArr = {username, password};
        Message msg = new Message<String[]> (MessageType.LOGIN, msgArr);

        try {
            objectOutputStream.writeObject(msg);
        } catch (IOException e) {
            System.err.println("Unable to write object.");
        }
    }

    @Override
    public void registerUser(String username, String password, String email) {
        String[] msgArr = {username, password, email};
        Message msg = new Message<String[]>(MessageType.REGISTER, msgArr);

        try {
            objectOutputStream.writeObject(msg);
        } catch (IOException e) {
            System.err.println("Unable to write object.");
        }
    }

    @Override
    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    @Override
    public String getBalance() {
        String balance = null;
        try {
            objectOutputStream.writeObject(new Message<String>(MessageType.BALANCE, new String(loggedUser)));
            balance = (String)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return balance;
    }

    @Override
    public void logoutUser() {
        loggedUser = null;
        try {
            objectOutputStream.writeObject(new Message<String>(MessageType.LOGOUT, new String(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getReply() {

        String msg = null;
        try {
            msg = (String) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading stream " + e.getMessage());
        }
        return msg;
    }

    @Override
    public String requestDollar(String[] insert) {

        String msg = null;
        try {

            objectOutputStream.writeObject(new Message<String[]>(MessageType.ASKDOLLAR, insert));
            msg = (String) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String getName() {
        return ConnectionService.class.getSimpleName();
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
