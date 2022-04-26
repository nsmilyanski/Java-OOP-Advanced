package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class SportsCar extends BaseCar{
    public SportsCar(String model, int horsePower) {
        super(model, horsePower, 3000);
    }

    @Override
    protected void checkHorsePower(int hPower) {
        if (hPower < 250 || hPower > 450){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, hPower));
        }
    }
}
