package ru.cs.myasoedov.client;

import junit.framework.TestCase;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.trains.Train;
import myasoedov.cs.trains.PassengerTrain;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.UUID;

public class ClientTest extends TestCase {


    @Test(expected = NullPointerException.class)
    public void testGetTrain() {
        Client client = new Client();
        client.setSession(new Session(1));
        client.getTrain();
    }

    public void testClearTrain() {
        Client client = new Client();
        client.setSession(new Session(1));
        client.setTrain(new PassengerTrain(UUID.randomUUID()));
        assertEquals(client.getTrain().getWagonsSize(), 0);
        client.clearTrain();
        assertNull(client.getTrain());
    }

    public void testTrain() throws IOException {
        Train train = new PassengerTrain(UUID.randomUUID());
        train.addLocomotive(WagonFactory.createDefaultDieselLocomotive());
        ObjectMapper om = new ObjectMapper();
        String str = om.writeValueAsString(train);
        System.out.println(str);
        Train train1 = om.readValue(new StringReader(str), PassengerTrain.class);
    }
}