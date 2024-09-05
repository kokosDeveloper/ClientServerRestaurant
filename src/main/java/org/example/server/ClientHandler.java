package org.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import lombok.AllArgsConstructor;
import org.example.commands.OrderCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
@AllArgsConstructor
public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static void configureObjectMapper(){
        objectMapper.activateDefaultTyping(BasicPolymorphicTypeValidator.builder().allowIfSubType(OrderCommand.class).build(),
                ObjectMapper.DefaultTyping.NON_FINAL);
    }

    @Override
    public void run() {
        try(BufferedReader readerFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writerToConsole = new PrintWriter(System.out, true);
            PrintWriter writerToClient = new PrintWriter(clientSocket.getOutputStream(), true)){
            configureObjectMapper();
            String orderJson;
            while((orderJson = readerFromClient.readLine()) != null){
                writerToConsole.println("Заказ принят: " + orderJson);
                OrderCommand order = objectMapper.readValue(orderJson, OrderCommand.class);
                order.execute();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
