package org.example.commands;

import lombok.AllArgsConstructor;
import org.example.commands.factories.CommandFactory;

import java.util.HashMap;
import java.util.Map;

public class CommandRegister {
    private Map<String, CommandFactory> commandFactoryMap = new HashMap<>();
    public void registerCommandFactory(String commandName, CommandFactory factory){
        commandFactoryMap.put(commandName, factory);
    }
    public OrderCommand getCommand(String commandName){
        CommandFactory factory = commandFactoryMap.get(commandName);
        if(factory != null){
            return factory.createCommand();
        }
        throw new IllegalArgumentException("Команда не найдена: " + commandName);
    }
}
