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

    public DoubleContainer<String, Object> receive() throws Exception {
        try {
            return (DoubleContainer<String, Object>) inputStream.readObject();
        } catch (ClassCastException e) {
            throw new ClassCastException("Не получилось принять данные с сервера");
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Не получилось принять данные с сервера", e);
        }
    }

    public void send(CommandAndHangar doubleContainer) throws IOException {
        try {
            synchronized (outputStream) {
                outputStream.writeObject(doubleContainer);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new IOException("Ошибка при передачи данных", e);
        }
    }

    public boolean initiateClose() throws IOException {
        try {
            CommandAndHangar commandAndHangar = new CommandAndHangar("disconnect", null);
            outputStream.writeObject(commandAndHangar);
            socket.close();
            return true;
        } catch (IOException e) {
            throw new IOException("Не получилось закрыть соединение", e);
        }
    }

    public boolean close() throws Exception {
        try {
            socket.close();
            return true;
        } catch (IOException e) {
            throw new Exception("Не удалось закрыть соединение!");
        }
    }
}
