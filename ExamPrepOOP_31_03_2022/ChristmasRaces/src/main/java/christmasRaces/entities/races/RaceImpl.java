package christmasRaces.entities.races;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        drivers = new ArrayList<>();
    }

    public void setName(String name) {
        if (name.isBlank() || name.length() < 5){
            throw  new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name));
        }
        this.name = name;
    }

    public void setLaps(int laps) {
        if (laps < 1){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS, laps));
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return Collections.unmodifiableCollection(drivers);
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null){
            throw new IllegalArgumentException(ExceptionMessages.DRIVER_INVALID);
        }else if (!driver.getCanParticipate()){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE, driver.getName()));
        }

        for (Driver driver1 : drivers) {
            if (driver.getName().equals(driver1.getName())){
                throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_ALREADY_ADDED,
                        driver.getName(), this.name));
            }
        }

        drivers.add(driver);

    }
}
