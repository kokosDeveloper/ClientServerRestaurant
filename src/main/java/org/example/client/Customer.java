package org.example.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.example.commands.CommandRegister;
import org.example.commands.OrderCommand;
import org.example.commands.factories.implementations.PizzaCommandFactory;
import org.example.commands.factories.implementations.WokCommandFactory;
import org.example.commands.implementations.PizzaCommand;
import org.example.commands.implementations.WokCommand;
import org.example.dishes.Dish;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;

public class Customer {
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 8083;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static CommandRegister commandRegister = new CommandRegister();
    private static void configureObjectMapperForDishes(){
        objectMapper.activateDefaultTyping(BasicPolymorphicTypeValidator.builder().allowIfSubType(Dish.class).build(),
                ObjectMapper.DefaultTyping.NON_FINAL);
    }



    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(IP_ADDRESS, PORT);
             BufferedReader readerFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedReader readerFromConsole = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writerToConsole = new PrintWriter(System.out, true);
             PrintWriter writerToServer = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String input;
            commandRegister.registerCommandFactory("пицца", new PizzaCommandFactory(readerFromConsole, writerToConsole));
            commandRegister.registerCommandFactory("вок", new WokCommandFactory(readerFromConsole, writerToConsole));
            configureObjectMapperForDishes();
            while(true){
                writerToConsole.println("Введите заказ: ");
                input = readerFromConsole.readLine();
                if(input.equals("выход"))
                    break;
                OrderCommand order = null;
                order = commandRegister.getCommand(input);
                if (order != null) {
                    String orderJson = objectMapper.writeValueAsString(order);
                    writerToServer.println(orderJson);
                    writerToConsole.println("Заказ отправлен: " + orderJson);
                    String dishJson = readerFromServer.readLine();
                    if(dishJson != null && !dishJson.isEmpty()){
                        Dish dish = objectMapper.readValue(dishJson, Dish.class);
                        writerToConsole.println("Получено блюдо: " + dish.toString());
                    }else{
                        writerToConsole.println("Не удалось получить блюдо");
                    }
                }else {
                    writerToConsole.println("Нераспознанный заказ");
                }

            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
