package ru.cs.myasoedov.client;

import junit.framework.TestCase;
import myasoedov.cs.trains.PassengerTrain;
import org.junit.Test;

import java.util.UUID;

public class ClientTest extends TestCase {


    @Test(expected = NullPointerException.class)
    public void testGetTrain() {
        Client client = new Client();
        client.setSession(new Session(1, 3));
        client.getTrain();
    }

    public void testClearTrain() {
        Client client = new Client();
        client.setSession(new Session(1, 3));
        client.setTrain(new PassengerTrain(UUID.randomUUID()));
        assertEquals(client.getTrain().getWagonsSize(), 0);
        client.clearTrain();
        assertNull(client.getTrain());
    }
}