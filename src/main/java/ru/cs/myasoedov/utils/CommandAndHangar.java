package ru.cs.myasoedov.utils;

import myasoedov.cs.DoubleContainer;
import myasoedov.cs.models.trains.Train;

import java.io.Serializable;

public class CommandAndHangar extends DoubleContainer<String, HangarAndTrain> implements Serializable {

    public CommandAndHangar() {
    }

    public CommandAndHangar(String command, Integer currentHangar, Train train) {
        setFirst(command);
        setSecond(new HangarAndTrain(currentHangar, train));
    }

    public CommandAndHangar(String command, HangarAndTrain hangarAndTrain) {
        setFirst(command);
        setSecond(hangarAndTrain);
    }
}
