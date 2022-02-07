package INHERITANCE.NeedForSpeed;

public class SportCar extends Car{
    private  static double DEFAULT_FUEL_CONSUMPTION = 10;
    public SportCar(double fuel, int horsePower) {
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
