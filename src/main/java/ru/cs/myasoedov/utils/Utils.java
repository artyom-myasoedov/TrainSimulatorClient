package ru.cs.myasoedov.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.abstractWagons.Wagon;
import ru.cs.myasoedov.enums.WagonType;

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
            Utils.alert(e);
            e.printStackTrace();
        }
    }

    public static void alert(Throwable throwable) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(throwable.getMessage());
        throwable.printStackTrace();
        alert.showAndWait();
    }

    public static void alert(String mes) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Внимание!");
        alert.setHeaderText(mes);
        alert.show();
    }

    public static Wagon createDefaultWagonByType(WagonType wagonType) {
        Wagon wagon;
        switch (wagonType) {
            case COUPE_WAGON -> wagon = WagonFactory.createDefaultCoupeWagon();
            case SEAT_WAGON -> wagon = WagonFactory.createDefaultSeatWagon();
            case SLEEP_WAGON -> wagon = WagonFactory.createDefaultSleepWagon();
            case RESTAURANT_WAGON -> wagon = WagonFactory.createDefaultRestaurantWagon();
            case TANK_WAGON -> wagon = WagonFactory.createDefaultTankWagon();
            case COVERED_WAGON -> wagon = WagonFactory.createDefaultCoveredWagon();
            case PLATFORM_WAGON -> wagon = WagonFactory.createDefaultPlatformWagon();
            case REFRIGERATOR_WAGON -> wagon = WagonFactory.createDefaultRefrigeratorWagon();
            case DIESEL_LOCOMOTIVE -> wagon = WagonFactory.createDefaultDieselLocomotive();
            case STEAM_LOCOMOTIVE -> wagon = WagonFactory.createDefaultSteamLocomotive();
            case ELECTRIC_LOCOMOTIVE -> wagon = WagonFactory.createDefaultElectricLocomotive();
            default -> throw new IllegalStateException("Unexpected value: " + wagonType);
        }
        return wagon;
    }

}
