package ru.cs.myasoedov.client.server.interaction;

import myasoedov.cs.DoubleContainer;
import ru.cs.myasoedov.utils.CommandAndHangar;
import ru.cs.myasoedov.utils.CommandAndTrains;
import ru.cs.myasoedov.utils.HangarAndTrain;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public DoubleContainer<String, Object> receive() {
        try {
            return (DoubleContainer<String, Object>) inputStream.getObjectInputFilter();
        } catch (ClassCastException e) {
            throw new ClassCastException("Не получилось принять данные с сервера");
        }
    }

    public void send(DoubleContainer<String, ?> doubleContainer) {
        try {
            synchronized (this.outputStream) {
                outputStream.writeObject(doubleContainer);
                outputStream.flush();
            }
        } catch (IOException e) {
            try {
                throw new IOException("Ошибка при передачи данных", e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public boolean close(HangarAndTrain hangarAndTrain) {
        try {
            CommandAndHangar commandAndHangar = new CommandAndHangar("disconnect", hangarAndTrain);
            outputStream.writeObject(new DoubleContainer<>());
            socket.close();
            return true;
        } catch (IOException e) {
            try {
                throw new IOException("Не получилось закрыть соединение", e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }
}
