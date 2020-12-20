package ru.cs.myasoedov.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public final class Utils {

    private Utils() {
    }

    public static void prepareStage(Stage stage, URL url, String title, double width, double height) {
        Scene scene;
        try {
            scene = new Scene(FXMLLoader.load(url), width, height);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException | NullPointerException e) {
            Utils.alert("Не удалось загрузить окно");
            e.printStackTrace();
        }
    }

    public static void alert(String str) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }

}
