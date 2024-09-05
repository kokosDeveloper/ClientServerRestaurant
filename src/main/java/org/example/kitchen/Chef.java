package org.example.kitchen;

import org.example.dishes.Pizza;
import org.example.dishes.Wok;

public class Chef {
    public Pizza cookPizza(String size){
        return new Pizza(size);
    }
    public Wok cookWok(String spicy){
        return new Wok(spicy);
    }
}
