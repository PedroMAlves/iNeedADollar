package org.academiadecodigo.bootcamp8.server;

import org.academiadecodigo.bootcamp8.server.handler.ClientHandler;
import org.academiadecodigo.bootcamp8.server.service.UserService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Prashanta on 13/07/17.
 */
public class Server {
    private UserService userService;
    private static int PORT;
    private ServerSocket serverSocket;

    public Server(UserService userService) {
      this.userService = userService;
      Server.PORT = 4040;
    }


    public Server(int port, UserService userService) {
        this.userService = userService;
        Server.PORT = port;
    }


    public void start() throws IOException {

        serverSocket = new ServerSocket(Server.PORT);
        ExecutorService cachedPool = Executors.newCachedThreadPool();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            cachedPool.submit(new ClientHandler(clientSocket, userService));
        }
    }

    public void stop() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
        System.exit(0);

    }
}
