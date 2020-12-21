package ru.cs.myasoedov.enums;

public enum WagonType {

    COVERED_WAGON("Covered wagon"),
    PLATFORM_WAGON("Platform wagon"),
    TANK_WAGON("Tank wagon"),
    REFRIGERATOR_WAGON("Refrigerator wagon"),
    DIESEL_LOCOMOTIVE("Diesel locomotive"),
    ELECTRIC_LOCOMOTIVE("Electric locomotive"),
    STEAM_LOCOMOTIVE("Steam locomotive"),
    COUPE_WAGON("Coupe wagon"),
    SLEEP_WAGON("Sleep wagon"),
    SEAT_WAGON("Seat wagon"),
    RESTAURANT_WAGON("Restaurant wagon")
    ;

    private final String name;

    WagonType(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
}
