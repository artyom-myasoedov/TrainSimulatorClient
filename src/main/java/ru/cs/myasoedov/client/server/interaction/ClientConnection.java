package ru.cs.myasoedov.client.server.interaction;

import myasoedov.cs.utils.DoubleContainer;
import myasoedov.cs.utils.CommandAndHangar;

import java.io.*;
import java.net.Socket;

public class ClientConnection {
    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public DoubleContainer<String, Object> receive() {
        try {
            DoubleContainer<String, Object> db = (DoubleContainer<String, Object>) inputStream.readObject();
            return db;
        } catch (ClassNotFoundException | ClassCastException | IOException e) {
            throw new RuntimeException("Не получилось принять данные с сервера", e);
        }
    }

    public void send(CommandAndHangar doubleContainer) {
        try {
            outputStream.writeObject(doubleContainer);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при передачи данных", e);
        }
    }

    public boolean initiateClose() {
        try {
            outputStream.writeObject(new CommandAndHangar("disconnect", null));
            return close();
        } catch (IOException e) {
            throw new RuntimeException("Не получилось закрыть соединение", e);
        }
    }

    public boolean close() throws IOException {
        try {
            socket.close();
            outputStream.close();
            inputStream.close();
            return true;
        } catch (IOException e) {
            throw new IOException("Не удалось закрыть соединение!");
        }
    }
}
