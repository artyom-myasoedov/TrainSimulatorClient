package ru.cs.myasoedov.client;

import myasoedov.cs.utils.DoubleContainer;
import myasoedov.cs.models.trains.Train;
import myasoedov.cs.utils.CommandAndHangar;
import myasoedov.cs.utils.HangarAndTrain;
import myasoedov.cs.utils.JsonConverter;
import ru.cs.myasoedov.client.server.interaction.ClientConnection;
import ru.cs.myasoedov.utils.Utils;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;


public class Client {

    private Session session;
    private ClientConnection connection;
    private final JsonConverter converter;
    private int exceptionCounter = 0;
    private Thread thread;
    private boolean isConnected;

    public Client() {
        converter = new JsonConverter();
    }

    public Client(Socket socket) throws IOException {
        connection = new ClientConnection(socket);
        converter = new JsonConverter();
    }

    public ClientConnection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
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

    public Thread getThread() {
        return thread;
    }

    public void setTrain(Train train) {
        session.getTrains().put(session.getCurrentHangar(), train);
    }

    public void clearTrain() {
        setTrain(null);
    }

    public void sendUpdate() {
        try {
            connection.send(new CommandAndHangar("update", new HangarAndTrain(getHangarNumber(), converter.trainToJson(getTrain()))));
        } catch (IOException e) {
            Utils.alert(e);
        }
    }

    public void sendSave() {
        try {
            connection.send(new CommandAndHangar("save", new HangarAndTrain(getHangarNumber(), converter.trainToJson(getTrain()))));
        } catch (IOException e) {
            Utils.alert(e);
        }
    }

    public void sendLoad() {
        try {
            connection.send(new CommandAndHangar("load", new HangarAndTrain(getHangarNumber(), converter.trainToJson(getTrain()))));
        } catch (IOException e) {
            Utils.alert(e);
        }
    }

    public boolean initiateClose() {
        thread.interrupt();
        return connection.initiateClose();
    }

    public void process() {
        thread = new Thread(() -> {
            while (isConnected) {
                try {
                    DoubleContainer<String, Object> dbc = connection.receive();
                    exceptionCounter = 0;
                    switch (dbc.getFirst()) {
                        case "disconnect" -> connection.close();
                        case "update" -> getSession().setTrains(converter.jsonToTrains((Map<Integer, String>) dbc.getSecond()));
                        case "start" -> {
                            DoubleContainer<Integer, Map<Integer, String>> dbt = (DoubleContainer<Integer, Map<Integer, String>>) dbc.getSecond();
                            setSession(new Session(dbt.getFirst()));
                            getSession().setTrains(converter.jsonToTrains(dbt.getSecond()));
                        }
                        case "exception" -> {
                            exceptionCounter = 0;
                            throw new RuntimeException("Ошибка на сервере!");
                        }
                    }
                } catch (Exception e) {
                    checkForDisconnect();
                }
            }
        });
        thread.start();
    }

    private void checkForDisconnect() {
        if (exceptionCounter++ > 2) {
            isConnected = false;
            try {
                thread.interrupt();
                connection.close();
            } catch (IOException exception) {
                throw new RuntimeException("Ошибка отсоединения!");
            }
        }
    }
}
