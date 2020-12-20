package ru.cs.myasoedov.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ControllerConnection {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField textFieldIp;

    @FXML
    private Text labelIp;

    @FXML
    private Text labelPort;

    @FXML
    private TextField textFieldPort;

    @FXML
    private Button buttonConnect;

    @FXML
    void initialize() {
        checkAsserts();
        buttonConnect.setOnAction(e -> {
            Utils.prepareStage(Main.primaryStage, getClass().getResource("main.fxml"), "Конструктор поездов", 1200, 600);
        });
    }

    private void checkAsserts() {
        assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'connection.fxml'.";
        assert textFieldIp != null : "fx:id=\"textFieldIp\" was not injected: check your FXML file 'connection.fxml'.";
        assert labelIp != null : "fx:id=\"labelIp\" was not injected: check your FXML file 'connection.fxml'.";
        assert labelPort != null : "fx:id=\"labelPort\" was not injected: check your FXML file 'connection.fxml'.";
        assert textFieldPort != null : "fx:id=\"textFieldPort\" was not injected: check your FXML file 'connection.fxml'.";
        assert buttonConnect != null : "fx:id=\"buttonConnect\" was not injected: check your FXML file 'connection.fxml'.";
    }
}
