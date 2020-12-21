package ru.cs.myasoedov.client;

import myasoedov.cs.models.trains.Train;

public class Session {
    private Integer currentHangar;
    private Integer numberOfHangars;
    private Train[] trains;

    public Session() {

    }

    public Session(Integer currentHangar) {
        this.currentHangar = currentHangar;
    }

    public Session(Integer currentHangar, Integer numberOfHangars) {
        this.currentHangar = currentHangar;
        this.numberOfHangars = numberOfHangars;
        trains = new Train[numberOfHangars];
    }

    public Integer getCurrentHangar() {
        return currentHangar;
    }

    public void setCurrentHangar(Integer currentHangar) {
        this.currentHangar = currentHangar;
    }

    public Integer getNumberOfHangars() {
        return numberOfHangars;
    }

    public void setNumberOfHangars(Integer numberOfHangars) {
        this.numberOfHangars = numberOfHangars;
    }

    public Train[] getTrains() {
        return trains;
    }

    public void setTrains(Train[] trains) {
        this.trains = trains;
    }
}
