package INHERITANCE.NeedForSpeed;

public class CrossMotorcycle extends Motorcycle {
    private final static double DEFAULT_FUEL_CONSUMPTION = 1.25;
    public CrossMotorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
