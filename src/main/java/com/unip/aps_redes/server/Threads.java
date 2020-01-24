package com.unip.aps_redes.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class Threads {

    private Socket socket;
    private ArrayList<PrintStream> clients;

    public Threads() {
    }

    public Threads(Socket socket, ArrayList<PrintStream> clients) {
        this.socket = socket;
        this.clients = clients;
        Thread();
    }

    private void Thread() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                String message = "";

                try {
                    InputStreamReader reader = new InputStreamReader(socket.getInputStream());
                    BufferedReader buffer = new BufferedReader(reader);

                    while ((message = buffer.readLine()) != null) {
                        sendMessage(message);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    private void sendMessage(String message) {
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).println(message);
            clients.get(i).flush();
        }
    }

}
