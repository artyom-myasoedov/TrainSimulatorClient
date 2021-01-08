package ru.cs.myasoedov.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.models.trains.Train;
import myasoedov.cs.trains.PassengerTrain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import ru.cs.myasoedov.gui.drawers.TrainDrawer;
import ru.cs.myasoedov.gui.drawers.WagonDrawer;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.*;

public class MainTest extends ApplicationTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("testMain.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void testButtonCreateTrain() {
        clickOn("#buttonCreateTrain");
        assertEquals(Main.currentClient.getTrain().getWagonsSize(),0);
    }

    @Test
    public void testButtonAddLocomotive() {
        clickOn("#buttonCreateTrain");
        assertEquals(Main.currentClient.getTrain().getWagonsSize(),0);
        clickOn("#buttonAddLocomotive");
        assertEquals(Main.currentClient.getTrain().getLocomotivesSize(),1);
        clickOn("#buttonAddLocomotive");
        clickOn("#buttonAddLocomotive");
    }

    @Test
    public void testButtonUnhookLocomotive() {
        clickOn("#buttonCreateTrain");
        assertEquals(Main.currentClient.getTrain().getWagonsSize(),0);
        clickOn("#buttonAddLocomotive");
        assertEquals(Main.currentClient.getTrain().getLocomotivesSize(),1);
        clickOn("#buttonDeleteLocomotive");
        assertEquals(Main.currentClient.getTrain().getLocomotivesSize(),0);
    }

    @Test
    public void testButtonAddWagon() throws InterruptedException {
        clickOn("#buttonCreateTrain");
        assertEquals(Main.currentClient.getTrain().getWagonsSize(),0);
        clickOn("#buttonAddWagon");
        assertEquals(Main.currentClient.getTrain().getWagonsSize(),1);
    }

    @Test
    public void testButtonUnhookWagon() {
        clickOn("#buttonCreateTrain");
        assertEquals(Main.currentClient.getTrain().getWagonsSize(),0);
        clickOn("#buttonAddWagon");
        assertEquals(Main.currentClient.getTrain().getWagonsSize(),1);
        clickOn("#buttonDeleteWagon");
        assertEquals(Main.currentClient.getTrain().getWagonsSize(),0);
    }

    @Test
    public void testButtonClearTrain() {
        clickOn("#buttonClear");
        assertNull(Main.currentClient.getTrain());
    }

    @Test(expected = NullPointerException.class)
    public void testWagonDrawerException() {
        Wagon wagon = WagonFactory.createDefaultPlatformWagon();
        WagonDrawer wagonDrawer = new WagonDrawer();
        AnchorPane pane = new AnchorPane();
        wagonDrawer.draw(wagon, pane, 0);
    }

    @Test
    public void testWagonDrawer() {
        Wagon wagon = WagonFactory.createDefaultPlatformWagon();
        wagon.setNumberInComposition(1L);
        WagonDrawer wagonDrawer = new WagonDrawer();
        AnchorPane pane = new AnchorPane();
        wagonDrawer.draw(wagon, pane, 0);
        assertEquals(pane.getChildren().size(), 1);
    }

    @Test
    public void testTrainDrawer() {
        Train train = new PassengerTrain(UUID.randomUUID());
        train.addLocomotive(WagonFactory.createDefaultDieselLocomotive());
        train.addHeadWagon(WagonFactory.createDefaultRestaurantWagon());
        train.addHeadWagon(WagonFactory.createDefaultRestaurantWagon());
        train.addHeadWagon(WagonFactory.createDefaultRestaurantWagon());
        AnchorPane pane = new AnchorPane();
        TrainDrawer trainDrawer = new TrainDrawer(pane);
        trainDrawer.draw(train);
        assertEquals(pane.getChildren().size(), 4);
    }

    @Test
    public void testTrainDrawerException() {
        AnchorPane pane = new AnchorPane();
        TrainDrawer trainDrawer = new TrainDrawer(pane);
        trainDrawer.draw(null);
        assertEquals(pane.getChildren().get(0).getClass(), Label.class);
    }


}