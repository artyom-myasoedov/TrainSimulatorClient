package ru.cs.myasoedov.gui.drawers;

import javafx.scene.layout.AnchorPane;
import junit.framework.TestCase;
import myasoedov.cs.factories.WagonFactory;
import org.junit.Rule;

public class WagonDrawerTest extends TestCase {
//    @Rule
//    public JavaFX javafxRule = new JavaFXThreadingRule();
    public void testDraw() {
        WagonDrawer wagonDrawer = new WagonDrawer();
        AnchorPane pane = new AnchorPane();
        assertEquals(pane.getChildren().size(), 0);
        wagonDrawer.draw(WagonFactory.createDefaultPlatformWagon(), pane, 0);
        wagonDrawer.draw(WagonFactory.createDefaultPlatformWagon(), pane, 0);
    }
}