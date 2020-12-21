package ru.cs.myasoedov.client;

import myasoedov.cs.models.trains.Train;
import ru.cs.myasoedov.client.server.interaction.Connection;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Session session;
    private Connection connection;

    public Client() {

    }

    public Client(Socket socket) throws IOException {
        connection = new Connection(socket);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Socket socket) throws IOException {
        this.connection = new Connection(socket);
    }

    public Integer getHangarNumber() {
        return session.getCurrentHangar();
    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Integer getMaxHangarNumbers() {
        return session.getNumberOfHangars();
    }


    public Train getTrain() {
        if (session.getTrains()[session.getCurrentHangar()] == null) {
            return null;
        }
        return session.getTrains()[session.getCurrentHangar()];
    }

    public void setTrain(Train train) {
        session.getTrains()[session.getCurrentHangar()] = train;
    }

    public void clearTrain() {
        setTrain(null);
    }
}
