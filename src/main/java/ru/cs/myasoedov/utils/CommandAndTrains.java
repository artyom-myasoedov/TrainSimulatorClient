package ru.cs.myasoedov.utils;

import myasoedov.cs.DoubleContainer;
import myasoedov.cs.models.trains.Train;

import java.io.Serializable;

public class CommandAndTrains extends DoubleContainer<String, Train[]> implements Serializable {
    public CommandAndTrains() {
    }

    public CommandAndTrains(String command, Train[] trains) {
        setFirst(command);
        setSecond(trains);
    }
}
