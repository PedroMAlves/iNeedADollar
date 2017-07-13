package org.academiadecodigo.bootcamp8.server.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Prashanta on 13/07/17.
 */
public class ClientHandler implements Runnable {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket clientSocket;
    boolean run;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        run = true;

    }


    @Override
    public void run() {
        setStreams();
        login();
        while(run) {
            read();
        }

    }

    private void login() {
        try {
            System.out.println(objectInputStream.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setStreams() {
        try {
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            System.err.println("Unable to open  objectStreams");
        }
    }
}
