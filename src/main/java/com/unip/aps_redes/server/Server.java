package com.unip.aps_redes.server;

import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {

        ArrayList<PrintStream> clients = new ArrayList<>();

        try {
            ServerSocket server = new ServerSocket(5000);
            Socket socket;
            System.out.println("Server IP: " + InetAddress.getLocalHost().getHostAddress());
            while (true) {
                socket = server.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

                clients.add(new PrintStream(socket.getOutputStream()));

                Threads thread = new Threads(socket, clients);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}
