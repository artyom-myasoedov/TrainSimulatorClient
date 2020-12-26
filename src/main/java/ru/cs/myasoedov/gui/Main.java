package ru.cs.myasoedov.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.cs.myasoedov.client.Client;
import ru.cs.myasoedov.utils.Utils;

import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends Application {

    static Stage primaryStage;
    final static Client currentClient = new Client();
    public static ControllerMain controllerMain;
    public static TestController testController;
    //--module-path
    //"C:\Program Files\Java\javafx-sdk-15.0.1\lib"
    //--add-modules
    //javafx.controls,javafx.fxml

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        Utils.prepareStage(primaryStage, getClass().getResource("connection.fxml"), "Подключение", 600, 400);
    }

    public static void main(String[] args) throws IOException {

        currentClient.setConnection(new Socket("localhost", 8001));
        currentClient.process();
        launch();
    }
}
