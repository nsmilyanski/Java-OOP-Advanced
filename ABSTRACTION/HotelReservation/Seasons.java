package ABSTRACTION.HotelReservation;

public enum Seasons {
    AUTUMN(1),
    SPRING(2),
    WINTER(3),
    SUMMER(4);

    private int value;

    Seasons(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
