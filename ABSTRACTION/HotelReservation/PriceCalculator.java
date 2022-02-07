package ABSTRACTION.HotelReservation;

public class PriceCalculator {
    private double pricePerDat;
    private int days;
    private Seasons season;
    private  DiscountType discountType;

    public PriceCalculator(double pricePerDat, int days, Seasons season, DiscountType discountType) {
        this.pricePerDat = pricePerDat;
        this.days = days;
        this.season = season;
        this.discountType = discountType;
    }

    public double calculateHoliday(){
        return ((this.pricePerDat * season.getValue()) * days) * discountType.getValue();
    }


}
