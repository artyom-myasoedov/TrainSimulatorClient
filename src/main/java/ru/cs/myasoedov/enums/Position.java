package ru.cs.myasoedov.enums;

public enum Position {
    TAIL("Конец"),
    HEAD("Начало");

    private String name;

    Position(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
