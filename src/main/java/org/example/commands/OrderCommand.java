package org.example.commands;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.example.dishes.Dish;
import org.example.kitchen.Chef;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,       // Добавляем информацию о классе
        include = JsonTypeInfo.As.PROPERTY, // Добавляем как свойство
        property = "@class"                // Имя поля для хранения типа
)
public interface OrderCommand {
    Dish execute();
    void setChef(Chef chef);
}
