package org.example.commands.implementations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.commands.OrderCommand;
import org.example.dishes.Dish;
import org.example.kitchen.Chef;


@NoArgsConstructor
@Data
public class WokCommand implements OrderCommand {
    private String spicy;
    private Chef chef;

    public WokCommand(String spicy) {
        this.spicy = spicy;
    }

    @Override
    public Dish execute() {
        return (Dish) chef.cookWok(spicy);
    }
}
