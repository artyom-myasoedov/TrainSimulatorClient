//package ru.cs.myasoedov.client;
//
//import myasoedov.cs.models.abstractWagons.FreightWagon;
//import myasoedov.cs.models.abstractWagons.Locomotive;
//import myasoedov.cs.models.abstractWagons.PassengerWagon;
//import myasoedov.cs.models.abstractWagons.Wagon;
//import myasoedov.cs.models.trains.Train;
//import myasoedov.cs.trains.FreightTrain;
//import myasoedov.cs.trains.PassengerTrain;
//
//import java.util.UUID;
//
//public class PrevClient {
//
//    private Integer hangerNumber;
//    private PassengerTrain passengerTrain;
//    private FreightTrain freightTrain;
//    private boolean isPassengerTrain;
//
//    public PrevClient() {
//    }
//
//    public PrevClient(Integer num) {
//        hangerNumber = num;
//    }
//
//    public PrevClient(Integer num, FreightTrain train) {
//        hangerNumber = num;
//        freightTrain = train;
//        passengerTrain = null;
//        isPassengerTrain = false;
//    }
//
//    public PrevClient(Integer num, PassengerTrain train) {
//        hangerNumber = num;
//        freightTrain = null;
//        passengerTrain = train;
//        isPassengerTrain = true;
//    }
//
//    public Integer getHangerNumber() {
//        return hangerNumber;
//    }
//
//    public void setHangerNumber(Integer hangerNumber) {
//        this.hangerNumber = hangerNumber;
//    }
//
//    public boolean isPassengerTrain() {
//        return isPassengerTrain;
//    }
//
//    public void setTrainType(boolean passengerTrain) {
//        isPassengerTrain = passengerTrain;
//        if (isPassengerTrain) setTrain(new PassengerTrain(UUID.randomUUID()));
//        else setTrain(new FreightTrain(UUID.randomUUID()));
//    }
//
//    public Train<? extends Wagon> getTrain() {
//        return isPassengerTrain ? passengerTrain : freightTrain;
//    }
//
//    private void setTrain(Train<? extends Wagon> train) {
//        try {
//            if (isPassengerTrain) {
//                passengerTrain = (PassengerTrain) train;
//                freightTrain = null;
//            } else {
//                passengerTrain = null;
//                freightTrain = (FreightTrain) train;
//            }
//        } catch (Exception e) {
//            throw new IllegalStateException("Ошибка создания поезда", e);
//        }
//    }
//
//    public void addHeadWagon(Wagon wagon) {
//        try {
//            if (isPassengerTrain) passengerTrain.addHeadWagon((PassengerWagon) wagon);
//            else freightTrain.addHeadWagon((FreightWagon) wagon);
//        } catch (Exception e) {
//            throw new IllegalStateException("Ошибка добавления вагона", e);
//        }
//    }
//
//    public void addTailWagon(Wagon wagon) {
//        try {
//            if (isPassengerTrain) passengerTrain.addTailWagon((PassengerWagon) wagon);
//            else freightTrain.addTailWagon((FreightWagon) wagon);
//        } catch (Exception e) {
//            throw new IllegalStateException("Ошибка добавления вагона", e);
//        }
//    }
//
//    public Wagon unhookHeadWagon() {
//        try {
//            if (isPassengerTrain) return passengerTrain.unhookHeadWagon();
//            else return freightTrain.unhookHeadWagon();
//        } catch (NullPointerException e) {
//            throw new IllegalStateException("Поезд пуст!", e);
//        }
//    }
//
//    public Wagon unhookTailWagon() {
//        try {
//            if (isPassengerTrain) return passengerTrain.unhookTailWagon();
//            else return freightTrain.unhookTailWagon();
//        } catch (NullPointerException e) {
//            throw new IllegalStateException("Поезд пуст!", e);
//        }
//    }
//
//    public void addLocomotive(Locomotive locomotive) {
//        try {
//            if (isPassengerTrain) passengerTrain.addLocomotive(locomotive);
//            else freightTrain.addLocomotive(locomotive);
//        } catch (IllegalStateException e) {
//            throw new IllegalStateException("Достигнуто максимальное число локомотивов!", e);
//        }
//    }
//
//    public Locomotive unhookLocomotive() {
//        try {
//            if (isPassengerTrain) return passengerTrain.unhookLocomotive();
//            else return freightTrain.unhookLocomotive();
//        } catch (NullPointerException e) {
//            throw new IllegalStateException("Состав локомотивов пуст!", e);
//        }
//    }
//
//    public void clearHangar() {
//        passengerTrain = null;
//        freightTrain = null;
//    }
//
//}
