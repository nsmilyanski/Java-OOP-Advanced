package INHERITANCE.NeedForSpeed;

public class Car extends Vehicle {
    private  static double DEFAULT_FUEL_CONSUMPTION = 3;

    public Car(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
    @Override
    public  void drive(double kilometers) {
        double needItFuel = kilometers * DEFAULT_FUEL_CONSUMPTION;
        if (needItFuel <= super.getFuel()){
            super.setFuel(super.getFuel() - needItFuel);
        }
    }
}
