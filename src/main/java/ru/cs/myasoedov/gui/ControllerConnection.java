package ru.cs.myasoedov.gui;

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ru.cs.myasoedov.utils.Utils;

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
            try {
                //Main.currentClient.setConnection(new Socket(textFieldIp.getText(), Integer.parseInt(textFieldPort.getText())));
                Utils.prepareStage(Main.primaryStage, getClass().getResource("main.fxml"), "Конструктор поездов", 1350, 600);
                System.out.println("connected");
            } catch (Exception exception) {
                Exception exception1 = new Exception("Не удалось подключится к серверу!", exception);
                Utils.alert(exception1);
            }
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
