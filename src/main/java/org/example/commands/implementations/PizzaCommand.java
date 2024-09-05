package org.example.commands.implementations;

import lombok.*;
import org.example.commands.OrderCommand;
import org.example.dishes.Dish;
import org.example.kitchen.Chef;



@Data
@NoArgsConstructor
public class PizzaCommand implements OrderCommand {
    private String size;
    private Chef chef;

    public PizzaCommand(String size) {
        this.size = size;
    }

    @Override
    public Dish execute() {
        return (Dish) chef.cookPizza(size);
    }
}
