package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar {
    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, 5000);
    }

    @Override
    protected void checkHorsePower(int hPower) {
        if (hPower < 400 || hPower > 600){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, hPower));
        }

    }
}
