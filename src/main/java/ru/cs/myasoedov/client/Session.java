package ru.cs.myasoedov.client;

import myasoedov.cs.models.trains.Train;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Session {
    private Integer currentHangar;
    private Map<Integer, Train> trains;

    public Session() {
    }

    public Session(Integer currentHangar) {
        this.currentHangar = currentHangar;
        trains = new ConcurrentHashMap<>();
    }

    public Integer getCurrentHangar() {
        return currentHangar;
    }

    public void setCurrentHangar(Integer currentHangar) {
        this.currentHangar = currentHangar;
    }

    public Integer getNumberOfHangars() {
        return trains.values().size();
    }

    public Map<Integer, Train> getTrains() {
        return trains;
    }

    public void setTrains(Map<Integer, Train> trains) {
        this.trains = trains;
    }
}
