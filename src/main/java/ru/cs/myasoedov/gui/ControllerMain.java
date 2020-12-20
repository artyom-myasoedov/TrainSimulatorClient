package ru.cs.myasoedov.gui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.trains.PassengerTrain;
import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;
import ru.cs.myasoedov.client.*;
import ru.cs.myasoedov.gui.drawers.TrainDrawer;

public class ControllerMain {

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Button buttonAddWagon;

    @FXML
    private Button buttonDeleteWagon;

    @FXML
    private Label labelChangeText;

    @FXML
    private ChoiceBox<?> choiceBoxWagon;

    @FXML
    private Button buttonCreateTrain;

    @FXML
    private Button buttonClear;

    @FXML
    private Button buttonAddLocomotive;

    @FXML
    private Button buttonDeleteLocomotive;

    @FXML
    private ChoiceBox<?> choiceBoxLocomotive;

    @FXML
    private AnchorPane hangarAnchor;

//    @FXML
//    private ScrollPane scrollPane1;
//
//    @FXML
//    private AnchorPane anchor1;
//
//    @FXML
//    private ScrollPane scrollPane2;
//
//    @FXML
//    private AnchorPane anchor2;
//
//    @FXML
//    private ScrollPane scrollPane3;
//
//    @FXML
//    private AnchorPane anchor3;
//
//    @FXML
//    private ScrollPane scrollPane4;
//
//    @FXML
//    private AnchorPane anchor4;
//
//    @FXML
//    private ScrollPane scrollPane5;
//
//    @FXML
//    private AnchorPane anchor5;

    @FXML
    private ChoiceBox<?> choiceBoxPosition;

    @FXML
    private ChoiceBox<?> choiceBoxTrainType;

    @FXML
    private Label labelWagon;

    @FXML
    private Label labelLocomotive;

    @FXML
    private Label labelTrain;

    @FXML
    private Button buttonSaveToDB;

    @FXML
    private Button buttonLoadFromDB;

    @FXML
    private Button ButtonLeaveRoom;

    private static Client currentClient;
    private final static List<ScrollPane> hangars = new ArrayList<>();

    @FXML
    void initialize() {
        checkAsserts();
        currentClient = new Client(0);
        noteSelected();
        hangars.add(createHangar(0));
        currentClient.setTrainType(true);
        currentClient.setTrain(new PassengerTrain(UUID.randomUUID()));

        TrainDrawer trainDrawer = new TrainDrawer((AnchorPane) hangars.get(0).getContent());
        buttonAddWagon.setOnAction(e -> {
            trainDrawer.draw(currentClient.getTrain());
        });

    }

    private void checkAsserts() {
        assert mainAnchor != null : "fx:id=\"mainAnchor\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonAddWagon != null : "fx:id=\"buttonAddWagon\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonDeleteWagon != null : "fx:id=\"buttonDeleteWagon\" was not injected: check your FXML file 'main.fxml'.";
        assert labelChangeText != null : "fx:id=\"labelChangeText\" was not injected: check your FXML file 'main.fxml'.";
        assert choiceBoxWagon != null : "fx:id=\"choiceBoxWagon\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonCreateTrain != null : "fx:id=\"buttonCreateTrain\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonClear != null : "fx:id=\"buttonClear\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonAddLocomotive != null : "fx:id=\"buttonAddLocomotive\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonDeleteLocomotive != null : "fx:id=\"buttonDeleteLocomotive\" was not injected: check your FXML file 'main.fxml'.";
        assert choiceBoxLocomotive != null : "fx:id=\"choiceBoxLocomotive\" was not injected: check your FXML file 'main.fxml'.";
        assert hangarAnchor != null : "fx:id=\"hangarAnchor\" was not injected: check your FXML file 'main.fxml'.";
        assert choiceBoxPosition != null : "fx:id=\"choiceBoxPosition\" was not injected: check your FXML file 'main.fxml'.";
        assert choiceBoxTrainType != null : "fx:id=\"choiceBoxTrainType\" was not injected: check your FXML file 'main.fxml'.";
        assert labelWagon != null : "fx:id=\"labelWagon\" was not injected: check your FXML file 'main.fxml'.";
        assert labelLocomotive != null : "fx:id=\"labelLocomotive\" was not injected: check your FXML file 'main.fxml'.";
        assert labelTrain != null : "fx:id=\"labelTrain\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonSaveToDB != null : "fx:id=\"buttonSaveToDB\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonLoadFromDB != null : "fx:id=\"buttonLoadFromDB\" was not injected: check your FXML file 'main.fxml'.";
        assert ButtonLeaveRoom != null : "fx:id=\"ButtonLeaveRoom\" was not injected: check your FXML file 'main.fxml'.";
    }

    private void noteSelected() {
        Rectangle rectangle = new Rectangle(0, currentClient.getHangerNumber() * 120, 50, 120);
        rectangle.setFill(Color.GREENYELLOW);
        hangarAnchor.getChildren().add(rectangle);
    }

    private ScrollPane createHangar(int num) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(120);
        scrollPane.setPrefWidth(750);
        scrollPane.setLayoutX(50);
        scrollPane.setLayoutY(120 * num);
        AnchorPane pane = new AnchorPane();
        pane.setPrefHeight(120);
        pane.setPrefWidth(750);
        scrollPane.setContent(pane);
        hangarAnchor.getChildren().add(scrollPane);
        return scrollPane;
    }
}
