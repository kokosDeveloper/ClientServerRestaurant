package org.example.commands.implementations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.commands.OrderCommand;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WokCommand implements OrderCommand {
    private String spicy;
    @Override
    public void execute() {
        System.out.println("Wok made, spicy: " + spicy);
    }
}
