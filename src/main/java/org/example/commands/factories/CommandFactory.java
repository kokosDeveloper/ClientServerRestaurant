package org.example.commands.factories;

import org.example.commands.OrderCommand;

public interface CommandFactory {
    OrderCommand createCommand();
    String[] supplyData();
}
