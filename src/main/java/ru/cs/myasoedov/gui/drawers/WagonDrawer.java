package ru.cs.myasoedov.gui.drawers;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.wagons.freightWagons.CoveredWagon;
import myasoedov.cs.wagons.freightWagons.PlatformWagon;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;
import myasoedov.cs.wagons.freightWagons.TankWagon;
import myasoedov.cs.wagons.locomotives.DieselLocomotive;
import myasoedov.cs.wagons.locomotives.ElectricLocomotive;
import myasoedov.cs.wagons.locomotives.SteamLocomotive;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;
import myasoedov.cs.wagons.passengerWagons.RestaurantWagon;
import myasoedov.cs.wagons.passengerWagons.SeatWagon;
import myasoedov.cs.wagons.passengerWagons.SleepWagon;

import java.util.HashMap;
import java.util.Map;

public class WagonDrawer {

    public final static Map<Class<?>, Color> colors = fillColorsMap();
    private AnchorPane pane;

    public WagonDrawer(AnchorPane anchorPane) {
        pane = anchorPane;
    }

    public WagonDrawer() {

    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public Group draw(Wagon wagon, AnchorPane pane, int locomotiveNum) {
        Group group = new Group();
        Rectangle rect = new Rectangle(150, 72, 10, 8);
        rect.setFill(Color.BROWN);
        Label label = new Label(wagon.getClass().getSimpleName() + " " + wagon.getNumberInComposition().toString());
        label.setFont(Font.font(16));
        label.setLayoutX(3);
        label.setLayoutY(32);
        group.getChildren().addAll(new Rectangle(150, 80, colors.get(wagon.getClass())), label, createWheel(20), createWheel(130), rect);
        group.setLayoutX((wagon.getNumberInComposition() - 1 + locomotiveNum) * 160);
        group.setLayoutY(5);
        pane.getChildren().add(group);
        return group;
    }

    private Circle createWheel(double x) {
        Circle circle = new Circle(20);
        circle.setFill(Color.GREY);
        circle.setCenterX(x);
        circle.setCenterY(76);
        return circle;
    }

    private static Map<Class<?>, Color> fillColorsMap() {
        Map<Class<?>, Color> map = new HashMap<>();
        map.put(SleepWagon.class, Color.rgb(135, 206, 235));
        map.put(RestaurantWagon.class, Color.rgb(135, 206, 235));
        map.put(CoupeWagon.class, Color.rgb(135, 206, 235));
        map.put(SeatWagon.class, Color.rgb(135, 206, 235));
        map.put(ElectricLocomotive.class, Color.rgb(240, 128, 128));
        map.put(DieselLocomotive.class, Color.rgb(240, 128, 128));
        map.put(SteamLocomotive.class, Color.rgb(240, 128, 128));
        map.put(CoveredWagon.class, Color.rgb(240, 230, 140));
        map.put(RefrigeratorWagon.class, Color.rgb(240, 230, 140));
        map.put(PlatformWagon.class, Color.rgb(240, 230, 140));
        map.put(TankWagon.class, Color.rgb(240, 230, 140));
        return map;
    }
}
