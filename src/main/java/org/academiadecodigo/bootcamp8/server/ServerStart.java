package org.academiadecodigo.bootcamp8.server;

import org.academiadecodigo.bootcamp8.server.service.MockUserService;
import org.academiadecodigo.bootcamp8.server.service.UserService;

import java.io.IOException;


/**
 * Created by Prashanta on 13/07/17.
 */
public class ServerStart {

    public static void main(String[] args) {

        Server server;
        //ConnectionManager connectionManager = new ConnectionManager();
        UserService userService = new Jdbc();

        if (args.length < 1 || Integer.parseInt(args[0]) < 1025) {
            System.out.println("Connect client to port 4040.");
            server = new Server(userService);
        } else {
            server = new Server(Integer.parseInt(args[0]), userService);
        }


        try {
            server.start();
        } catch (IOException e) {
            System.err.println("Unable to start server socket");
        } finally {
            try {
                server.stop();
            } catch (IOException e) {
                System.err.println("Something went terribly wrong while closing");
            }
        }



    }
}
