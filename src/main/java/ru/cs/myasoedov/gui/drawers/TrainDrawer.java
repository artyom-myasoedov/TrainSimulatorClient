package ru.cs.myasoedov.gui.drawers;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import myasoedov.cs.models.trains.Train;


public class TrainDrawer {
    private AnchorPane pane;
    private WagonDrawer wagonDrawer;

    public TrainDrawer(AnchorPane anchorPane) {
        pane = anchorPane;
        wagonDrawer = new WagonDrawer();
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }


    public void draw(Train train) {
        pane.getChildren().clear();
        if (train != null) {
            train.getLocomotives().forEach(l -> wagonDrawer.draw(l, pane, 0));
            train.getWagons().forEach(w -> wagonDrawer.draw(w, pane, train.getLocomotivesSize()));
        } else {
            Label label = new Label("Пустой ангар");
            label.setFont(Font.font(20));
            label.setLayoutY(40);
            label.setLayoutX(300);
            pane.getChildren().add(label);
        }

    }

    public WagonDrawer getWagonDrawer() {
        return wagonDrawer;
    }

    public void setWagonDrawer(WagonDrawer wagonDrawer) {
        this.wagonDrawer = wagonDrawer;
    }
}

