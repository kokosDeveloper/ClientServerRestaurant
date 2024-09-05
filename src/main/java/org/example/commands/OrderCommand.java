package org.example.commands;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,       // Добавляем информацию о классе
        include = JsonTypeInfo.As.PROPERTY, // Добавляем как свойство
        property = "@class"                // Имя поля для хранения типа
)
public interface OrderCommand {
    void execute();
}
