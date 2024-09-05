package org.example.commands.implementations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.commands.OrderCommand;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PizzaCommand implements OrderCommand {
    private String size;
    @Override
    public void execute() {
        System.out.println("Pizza made, size: " + size);
    }
}
