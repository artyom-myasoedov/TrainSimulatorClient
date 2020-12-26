package ru.cs.myasoedov.client;

import myasoedov.cs.utils.DoubleContainer;
import myasoedov.cs.models.trains.Train;
import myasoedov.cs.utils.CommandAndHangar;
import myasoedov.cs.utils.HangarAndTrain;
import ru.cs.myasoedov.client.server.interaction.ClientConnection;
import ru.cs.myasoedov.gui.ControllerMain;
import ru.cs.myasoedov.gui.Main;
import ru.cs.myasoedov.utils.Utils;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class Client {

    private Session session;
    private ClientConnection connection;

    public Client() {
    }

    public Client(Socket socket) throws IOException {
        connection = new ClientConnection(socket);
    }

    public ClientConnection getConnection() {
        return connection;
    }

    public void setConnection(Socket socket) throws IOException {
        this.connection = new ClientConnection(socket);
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
        if (session.getTrains().get(session.getCurrentHangar()) == null) {
            return null;
        }
        return session.getTrains().get(session.getCurrentHangar());
    }

    public void setTrain(Train train) {
        session.getTrains().put(session.getCurrentHangar(), train);
    }

    public void clearTrain() {
        setTrain(null);
    }

    public void sendUpdate() {
        try {
            connection.send(new CommandAndHangar("update", new HangarAndTrain(getHangarNumber(), getTrain())));
        } catch (IOException e) {
            Utils.alert(e);
        }
    }

    public void sendSave() {
        try {
            connection.send(new CommandAndHangar("save", new HangarAndTrain(getHangarNumber(), getTrain())));
        } catch (IOException e) {
            Utils.alert(e);
        }
    }

    public void sendLoad() {
        try {
            connection.send(new CommandAndHangar("load", new HangarAndTrain(getHangarNumber(), getTrain())));
        } catch (IOException e) {
            Utils.alert(e);
        }
    }

    public boolean initiateClose() {
        try {
            return connection.initiateClose();
        } catch (IOException e) {
            Utils.alert(e);
        }
        return false;
    }

    public void process() {
        new Thread(() -> {
            while (true) {
                try {
                    DoubleContainer<String, Object> dbc = connection.receive();
                    switch (dbc.getFirst()) {
                        case "disconnect" -> connection.close();
                        case "update" -> getSession().setTrains((Map<Integer, Train>) dbc.getSecond());
                        case "start" -> {
                            DoubleContainer<Integer, Map<Integer, Train>> dbt = (DoubleContainer<Integer, Map<Integer, Train>>) dbc.getSecond();
                            setSession(new Session(dbt.getFirst()));
                            getSession().setTrains(dbt.getSecond());
                        }
                        case "exception" -> Utils.alert((Throwable) dbc.getSecond());
                    }
                } catch (Exception e) {
                    Utils.alert(e);
                }
            }
        }).start();

    }
}
