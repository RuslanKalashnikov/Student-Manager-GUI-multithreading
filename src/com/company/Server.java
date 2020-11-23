package com.company;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1998);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
