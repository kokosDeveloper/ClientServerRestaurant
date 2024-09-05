package org.example.commands.factories.implementations;

import lombok.AllArgsConstructor;
import org.example.commands.OrderCommand;
import org.example.commands.factories.CommandFactory;
import org.example.commands.implementations.PizzaCommand;
import org.example.commands.implementations.WokCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
@AllArgsConstructor
public class PizzaCommandFactory implements CommandFactory {
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    @Override
    public OrderCommand createCommand() {
        String[] args = supplyData();
        return new PizzaCommand(args[0]);
    }

    @Override
    public String[] supplyData() {
        try {
            printWriter.println("Введите размер пиццы: ");
            String size = bufferedReader.readLine();
            return new String[]{size};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
