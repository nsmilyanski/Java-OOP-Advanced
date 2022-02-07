package ABSTRACTION.HotelReservation;

public enum DiscountType {
    VIP(0.8),
    SECONDVISIT(0.9),
    NONE(1.00);

    private double value;

    DiscountType(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
