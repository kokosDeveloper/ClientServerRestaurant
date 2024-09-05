package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8083);
            System.out.println("Сервер запущен");
            while(true){
                new Thread(new ClientHandler(serverSocket.accept())).start();
                System.out.println("Клиент подключился");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
