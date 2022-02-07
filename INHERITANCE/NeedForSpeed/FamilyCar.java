package INHERITANCE.NeedForSpeed;

public class FamilyCar extends Car{
    private  static double DEFAULT_FUEL_CONSUMPTION = 3;
    public FamilyCar(double fuel, int horsePower) {
        super(fuel, horsePower);
        setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
    @Override
    public  void drive(double kilometers) {
        double needItFuel = kilometers * DEFAULT_FUEL_CONSUMPTION;
        if (needItFuel <= super.getFuel()){
            super.setFuel(super.getFuel() - needItFuel);
        }
    }
}
