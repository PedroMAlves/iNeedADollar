package org.academiadecodigo.bootcamp8.server.handler;

import org.academiadecodigo.bootcamp8.server.model.User;
import org.academiadecodigo.bootcamp8.server.service.UserService;
import org.academiadecodigo.bootcamp8.shared.Values;
import org.academiadecodigo.bootcamp8.shared.message.Message;
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
    private UserService userService;
    boolean run;

    public ClientHandler(Socket clientSocket, UserService userService) {
        this.clientSocket = clientSocket;
        this.userService = userService;
        run = true;

    }


    @Override
    public void run() {
        setStreams();
        login();
        while (run) {
            try {
                read();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            clientSocket.close();
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

    private void login() {
        boolean exit = false;
        while (!exit) {
            Message<String[]> str = null;
            try {
                str = (Message<String[]>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("error reading from stream " + e.getMessage());
            }
            switch (str.getType()) {
                case LOGIN:
                    exit = log(str.getContent());
                    break;
                case REGISTER:
                    register(str.getContent());
                    break;

            }

        }
    }

    private boolean log(String[] str) {
        String[] s = str;
        if (userService.authenticate(s[0], s[1])){
            try {
                objectOutputStream.writeObject(Values.LOGIN_OK);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            objectOutputStream.writeObject(Values.LOGIN_FAIL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean register(String[] str) {
        String[] s = str;
        if (userService.addUser(new User(s[0], s[1], s[2]))) {

            try {
                objectOutputStream.writeObject(Values.REGISTER_OK);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            objectOutputStream.writeObject(Values.USER_TAKEN);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void read() throws IOException, ClassNotFoundException {
        Message msg;
        if((msg = (Message) objectInputStream.readObject()) != null){
            handle(msg);
            return;
        }
        run = false;
    }

    private void handle(Message msg) {

        switch (msg.getType()){

        }

    }
}
