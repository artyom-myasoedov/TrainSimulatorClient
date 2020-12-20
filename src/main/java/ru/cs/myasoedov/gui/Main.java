package ru.cs.myasoedov.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        Utils.prepareStage(primaryStage, getClass().getResource("connection.fxml"), "Подключение", 600, 400);
    }

    public static void main(String[] args) {
        launch();
    }
}
