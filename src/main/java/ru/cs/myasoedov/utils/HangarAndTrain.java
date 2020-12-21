package ru.cs.myasoedov.utils;

import myasoedov.cs.DoubleContainer;
import myasoedov.cs.models.trains.Train;

import java.io.Serializable;

public class HangarAndTrain extends DoubleContainer<Integer, Train> implements Serializable {

    public HangarAndTrain() {
    }

    public HangarAndTrain(Integer currentHangar, Train train) {
        setFirst(currentHangar);
        setSecond(train);
    }
}
