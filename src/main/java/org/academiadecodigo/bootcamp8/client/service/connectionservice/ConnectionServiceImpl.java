package org.academiadecodigo.bootcamp8.client.service.connectionservice;

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
    public String getName() {
        return ConnectionService.class.getSimpleName();
    }
}
