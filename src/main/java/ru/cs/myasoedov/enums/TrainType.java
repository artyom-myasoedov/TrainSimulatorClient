package ru.cs.myasoedov.enums;

public enum TrainType {
    PASSENGER("Passenger train"),
    FREIGHT("Freight train");

    private String name;

    TrainType(String s) {
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
