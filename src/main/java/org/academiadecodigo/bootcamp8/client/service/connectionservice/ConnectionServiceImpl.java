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
    public String getReply() {

        Message<String> msg = null;
        try {
            msg = (Message)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading stream " + e.getMessage());
        }
        return msg.getContent();
    }

    @Override
    public String getName() {
        return ConnectionService.class.getSimpleName();
    }
}
