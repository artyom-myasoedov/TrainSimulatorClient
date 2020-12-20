package ru.cs.myasoedov.gui.drawers;

import javafx.scene.layout.AnchorPane;
import myasoedov.cs.models.abstractWagons.Wagon;
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


    public void draw(Train<? extends Wagon> train) {
        train.getLocomotives().forEach(l -> wagonDrawer.draw(l, pane, 0));
        train.getWagons().forEach(w -> wagonDrawer.draw(w, pane, train.getLocomotivesSize()));
    }

    public WagonDrawer getWagonDrawer() {
        return wagonDrawer;
    }

    public void setWagonDrawer(WagonDrawer wagonDrawer) {
        this.wagonDrawer = wagonDrawer;
    }
}

