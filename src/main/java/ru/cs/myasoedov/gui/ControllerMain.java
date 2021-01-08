package ru.cs.myasoedov.gui;

import java.io.IOException;
import java.util.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.trains.FreightTrain;
import myasoedov.cs.trains.PassengerTrain;
import ru.cs.myasoedov.client.Client;
import ru.cs.myasoedov.enums.Position;
import ru.cs.myasoedov.enums.TrainType;
import ru.cs.myasoedov.enums.WagonType;
import ru.cs.myasoedov.gui.drawers.TrainDrawer;
import ru.cs.myasoedov.utils.Utils;

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
    public ChoiceBox<WagonType> choiceBoxWagon;

    @FXML
    private Button buttonCreateTrain;

    @FXML
    private Button buttonClear;

    @FXML
    private Button buttonAddLocomotive;

    @FXML
    private Button buttonDeleteLocomotive;

    @FXML
    private ChoiceBox<WagonType> choiceBoxLocomotive;

    @FXML
    private AnchorPane hangarAnchor;

    @FXML
    private ChoiceBox<Position> choiceBoxPosition;

    @FXML
    public ChoiceBox<TrainType> choiceBoxTrainType;

    @FXML
    private Label labelWagon;

    @FXML
    private Label labelID;

    @FXML
    private TextField textFieldID;

    @FXML
    private Label labelLocomotive;

    @FXML
    private Label labelTrain;

    @FXML
    private Button buttonSaveToDB;

    @FXML
    private Button buttonLoadFromDB;

    @FXML
    private Button buttonLeaveRoom;

    public final static List<TrainDrawer> trainDrawers = new ArrayList<>();
    private final static List<WagonType> locomotiveTypes = new ArrayList<>();
    public final static List<WagonType> freightWagonTypes = new ArrayList<>();
    public final static List<WagonType> passengerWagonTypes = new ArrayList<>();
    private final static List<TrainType> trainTypes = new ArrayList<>();
    private final static List<Position> positions = new ArrayList<>();
    private final static List<TextField> textFields = new ArrayList<>();
    private Timeline updateFrame;
    private Timeline checkDisconnect;

    @FXML
    void initialize() {
        Main.controllerMain = this;
        Main.currentClient.process();

        checkAsserts();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initializeTrainDrawers(Main.currentClient.getMaxHangarNumbers());
        createID(Main.currentClient.getHangarNumber());
        fillChoiceCollections();
        buttonAddWagon.setOnAction(this::addWagon);
        buttonAddLocomotive.setOnAction(this::addLocomotive);
        buttonDeleteLocomotive.setOnAction(this::unhookLocomotive);
        buttonDeleteWagon.setOnAction(this::unhookWagon);
        buttonClear.setOnAction(this::clearHangar);
        buttonCreateTrain.setOnAction(this::createTrain);
        buttonSaveToDB.setOnAction(this::saveToDB);
        buttonLoadFromDB.setOnAction(this::loadFromDB);
        buttonLeaveRoom.setOnAction(ControllerMain::disconnect);


        noteSelected();

        startAnimation();
        checkDisconnet();
    }

    private void checkDisconnet() {
        checkDisconnect = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (!Main.currentClient.isConnected()) {
                updateFrame.stop();
                checkDisconnect.stop();
                Main.currentClient = new Client();
                Utils.alert("Вы отключены");
                Utils.prepareStage(Main.primaryStage, Main.class.getResource("connection.fxml"), "Подключение", 600, 400);
            }
        }));
        checkDisconnect.setCycleCount(Animation.INDEFINITE);
        checkDisconnect.play();
    }

    private void startAnimation() {
        updateFrame = new Timeline(new KeyFrame(Duration.seconds(1), e -> drawAll()));
        updateFrame.setCycleCount(Animation.INDEFINITE);
        updateFrame.play();
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
        assert buttonLeaveRoom != null : "fx:id=\"ButtonLeaveRoom\" was not injected: check your FXML file 'main.fxml'.";
    }

    private void noteSelected() {
        Rectangle rectangle = new Rectangle(0, Main.currentClient.getHangarNumber() * 120, 50, 120);
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

    private void initializeTrainDrawers(int maxNumberClients) {
        trainDrawers.clear();
        for (int i = 0; i < maxNumberClients; i++) {
            trainDrawers.add(new TrainDrawer((AnchorPane) createHangar(i).getContent()));
        }
        trainDrawers.forEach(t -> t.draw(null));
    }

    private void fillChoiceCollections() {
//        positions.clear();
//        passengerWagonTypes.clear();
//        freightWagonTypes.clear();
//        locomotiveTypes.clear();
//        trainDrawers.clear();
//        trainTypes.clear();
        passengerWagonTypes.add(WagonType.COUPE_WAGON);
        freightWagonTypes.add(WagonType.COVERED_WAGON);
        freightWagonTypes.add(WagonType.PLATFORM_WAGON);
        freightWagonTypes.add(WagonType.TANK_WAGON);
        freightWagonTypes.add(WagonType.REFRIGERATOR_WAGON);
        passengerWagonTypes.add(WagonType.SLEEP_WAGON);
        passengerWagonTypes.add(WagonType.SEAT_WAGON);
        passengerWagonTypes.add(WagonType.RESTAURANT_WAGON);
        locomotiveTypes.add(WagonType.DIESEL_LOCOMOTIVE);
        locomotiveTypes.add(WagonType.ELECTRIC_LOCOMOTIVE);
        locomotiveTypes.add(WagonType.STEAM_LOCOMOTIVE);
        trainTypes.add(TrainType.FREIGHT);
        trainTypes.add(TrainType.PASSENGER);
        positions.add(Position.HEAD);
        positions.add(Position.TAIL);
        choiceBoxLocomotive.setItems(FXCollections.observableList(locomotiveTypes));
        choiceBoxPosition.setItems(FXCollections.observableList(positions));
        choiceBoxTrainType.setItems(FXCollections.observableList(trainTypes));
        choiceBoxWagon.setItems(FXCollections.observableList(passengerWagonTypes));
        choiceBoxWagon.setValue(WagonType.COUPE_WAGON);
        choiceBoxPosition.setValue(Position.HEAD);
        choiceBoxLocomotive.setValue(WagonType.DIESEL_LOCOMOTIVE);
        choiceBoxTrainType.setValue(TrainType.PASSENGER);
    }

    private void createID(int num) {
        Label label = new Label("ID:");
        label.setFont(Font.font(12));
        label.setLayoutX(1220);
        label.setLayoutY(40 + num * 120);
        TextField textField = new TextField();
        textField.setLayoutX(1220);
        textField.setLayoutY(60 + num * 120);
        textField.setFont(Font.font(12));
        textField.setPrefWidth(100);
        textField.setEditable(false);
        mainAnchor.getChildren().addAll(textField, label);
        textFields.clear();
        textFields.add(textField);
    }

    private void addWagon(ActionEvent e) {
        try {
            if (choiceBoxPosition.getValue().equals(Position.HEAD))
                Main.currentClient.getTrain().addHeadWagon(Utils.createDefaultWagonByType(choiceBoxWagon.getValue()));
            else Main.currentClient.getTrain().addTailWagon(Utils.createDefaultWagonByType(choiceBoxWagon.getValue()));

            Main.currentClient.sendUpdate();
        } catch (Exception exception) {
            Exception exception1 = exception;
            if (Main.currentClient.getTrain() == null) {
                exception1 = new Exception("Попытка добавления вагона в несуществующий поезд", exception);
            }
            Utils.alert(exception1);
        }
    }

    private void addLocomotive(ActionEvent e) {
        try {
            Main.currentClient.getTrain().addLocomotive((Locomotive) Utils.createDefaultWagonByType(choiceBoxLocomotive.getValue()));
            Main.currentClient.sendUpdate();
        } catch (Exception exception) {
            Exception exception1 = exception;
            if (Main.currentClient.getTrain() == null) {
                exception1 = new Exception("Попытка добавления локомотива в несуществующий поезд", exception);
            }
            Utils.alert(exception1);
        }

    }


    public static void drawAll() {
        final int[] i = {0};
        trainDrawers.forEach(t -> t.draw(Main.currentClient.getSession().getTrains().get(i[0]++)));
    }

    private void unhookLocomotive(ActionEvent e) {
        try {
            Main.currentClient.getTrain().unhookLocomotive();
            Main.currentClient.sendUpdate();
        } catch (Exception exception) {
            Exception exception1 = exception;
            if (Main.currentClient.getTrain() == null) {
                exception1 = new Exception("Попытка удаления локомотива из несуществующего поезда", exception);
            }
            Utils.alert(exception1);
        }

    }

    private void unhookWagon(ActionEvent e) {
        try {
            if (choiceBoxPosition.getValue().equals(Position.HEAD)) {
                Main.currentClient.getTrain().unhookHeadWagon();
            } else {
                Main.currentClient.getTrain().unhookTailWagon();
            }

            Main.currentClient.sendUpdate();
        } catch (Exception exception) {
            Exception exception1 = exception;
            if (Main.currentClient.getTrain() == null) {
                exception1 = new Exception("Попытка удаления вагона из несуществующего поезда", exception);
            }
            Utils.alert(exception1);
        }
    }


    private void clearHangar(ActionEvent e) {
        try {
            Main.currentClient.clearTrain();
            Main.currentClient.sendUpdate();
        } catch (Exception exception) {
            Utils.alert(exception);
        }
    }


    private void createTrain(ActionEvent e) {
        try {
            if (choiceBoxTrainType.getValue().equals(TrainType.PASSENGER)) {
                Main.currentClient.setTrain(new PassengerTrain(UUID.randomUUID()));
                choiceBoxWagon.setItems(FXCollections.observableList(passengerWagonTypes));
                choiceBoxWagon.setValue(WagonType.COUPE_WAGON);
            } else {
                Main.currentClient.setTrain(new FreightTrain(UUID.randomUUID()));
                choiceBoxWagon.setItems(FXCollections.observableList(freightWagonTypes));
                choiceBoxWagon.setValue(WagonType.COVERED_WAGON);
            }
            textFields.get(0).setText(Main.currentClient.getTrain().getId().toString());
            changeChoiceBoxes();
            Main.currentClient.sendUpdate();
        } catch (Exception exception) {
            Utils.alert(exception);
        }
    }

    private void saveToDB(ActionEvent e) {
        boolean isSaved = false;
        try {
            if (Main.currentClient.getTrain() == null) {
                throw new NullPointerException("Нет поезда!");
            }
            Main.currentClient.sendSave();
            isSaved = true;
        } catch (NullPointerException exception) {
            Utils.alert(new Exception("Нет поезда!", exception));
        } catch (Exception exception) {
            Utils.alert(new Exception("Не удалось сохранить поезд!", exception));
        }
        if (isSaved) Utils.alert("Поезд успешно сохранён!");
    }


    private void loadFromDB(ActionEvent e) {
        try {
            String str = textFieldID.getText();
            if (str.equals("")) {
                throw new IllegalArgumentException("Недопустимый Id");
            }
            Main.currentClient.setTrain(new PassengerTrain(UUID.fromString(str)));
            Main.currentClient.sendLoad();

            Timeline tm = new Timeline(new KeyFrame(Duration.seconds(1), ev -> changeChoiceBoxes()));
            tm.setCycleCount(3);
            tm.play();
        } catch (Exception ioException) {
            Utils.alert(new Exception("Не удалось загрузить поезд!", ioException));
        }
    }

    public void changeChoiceBoxes() {
        if (Main.currentClient.getTrain() != null) {
            if (Main.currentClient.getTrain().getClass().equals(FreightTrain.class)) {
                choiceBoxWagon.setItems(FXCollections.observableList(freightWagonTypes));
                choiceBoxWagon.setValue(WagonType.COVERED_WAGON);
                choiceBoxTrainType.setValue(TrainType.FREIGHT);
            } else {
                choiceBoxWagon.setItems(FXCollections.observableList(passengerWagonTypes));
                choiceBoxWagon.setValue(WagonType.COUPE_WAGON);
                choiceBoxTrainType.setValue(TrainType.PASSENGER);
            }
        }
    }

    private static void disconnect(ActionEvent e) {
        Main.currentClient.setConnected(false);
        Main.currentClient.initiateClose();
        positions.clear();
        passengerWagonTypes.clear();
        freightWagonTypes.clear();
        locomotiveTypes.clear();
        trainDrawers.clear();
        trainTypes.clear();

    }

}
