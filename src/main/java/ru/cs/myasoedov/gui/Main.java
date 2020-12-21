package ru.cs.myasoedov.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.cs.myasoedov.client.Client;
import ru.cs.myasoedov.utils.Utils;


public class Main extends Application {

    static Stage primaryStage;
    final static Client currentClient = new Client();
    static ControllerMain controllerMain;


    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        Utils.prepareStage(primaryStage, getClass().getResource("connection.fxml"), "Подключение", 600, 400);
    }

    public static void main(String[] args) {
        launch();
    }
}
