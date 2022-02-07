package INHERITANCE.NeedForSpeed;

public class Motorcycle extends  Vehicle{
    private final static double DEFAULT_FUEL_CONSUMPTION = 1.25;
    public Motorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
