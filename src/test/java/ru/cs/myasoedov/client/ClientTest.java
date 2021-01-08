package ru.cs.myasoedov.client;

import junit.framework.TestCase;
import myasoedov.cs.Configs;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.models.trains.Train;
import myasoedov.cs.storages.train.PassengerTrainDBStorage;
import myasoedov.cs.trains.PassengerTrain;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import ru.cs.myasoedov.Server;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.UUID;

public class ClientTest extends TestCase {

    Server server;
    Client client;

    @Override
    protected void setUp() throws Exception {
        server = new Server(8002);

        new Thread(() -> {
            try {
                server.accept();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
        client = new Client();

        client.setConnection(new Socket("localhost", 8002));
        client.process();
        client.setConnected(true);

        Train train = new PassengerTrain(UUID.randomUUID());
        train.addHeadWagon(WagonFactory.createDefaultCoupeWagon());
        train.addHeadWagon(WagonFactory.createDefaultCoupeWagon());
        train.addLocomotive(WagonFactory.createDefaultDieselLocomotive());
        train.addLocomotive(WagonFactory.createDefaultDieselLocomotive());
        client.setTrain(train);
    }

    @Test(expected = NullPointerException.class)
    public void testGetTrain() {
        Client client = new Client();
        client.setSession(new Session(1));
        client.getTrain();
    }

    public void testJackson() throws IOException {
        Train train = new PassengerTrain(UUID.randomUUID());
        ObjectMapper om = new ObjectMapper();
        String str = om.writeValueAsString(train);
        Train train1 = om.readValue(str, Train.class);
        assertEquals(train1, train);
    }

    public void testInteraction() throws InterruptedException {
        client.sendUpdate();
        Thread.sleep(1000);
        assertEquals(server.getTrains().get(0), client.getTrain());
    }

    public void testSaveLoadDB() throws SQLException, InterruptedException {
        client.sendSave();
        Thread.sleep(1000);
        Storage<PassengerTrain> storage = new PassengerTrainDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        assertEquals(client.getTrain(), storage.get(client.getTrain().getId()));
    }

    public void testDisconnect() throws InterruptedException {
        client.sendSave();
        client.initiateClose();
        Thread.sleep(1000);
        assertEquals(0, server.getConnections().size());
    }

    public void testTwoClients() throws IOException, InterruptedException {
        Client client = new Client();
        client.setConnection(new Socket("localhost", 8002));
        Thread.sleep(1000);
        assertEquals(2, server.getConnections().size());
    }
}