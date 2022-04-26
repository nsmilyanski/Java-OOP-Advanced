package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Car> carRepository;
    private Repository<Driver> driverRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl( Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        if (driverRepository.getByName(driver) != null){
            throw  new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driver));
        }
        Driver driver1 = new DriverImpl(driver);

        driverRepository.add(driver1);

        return String.format(OutputMessages.DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (carRepository.getByName(model) != null){
            throw  new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        }
        String message = "";
        switch (type){
            case "Muscle":
                MuscleCar muscleCar = new MuscleCar(model, horsePower);
                carRepository.add(muscleCar);
                message = String.format(OutputMessages.CAR_CREATED, type, model);
                break;
            case "Sports":
                SportsCar sportsCar = new SportsCar(model, horsePower);
                carRepository.add(sportsCar);
                message = String.format(OutputMessages.CAR_CREATED, type, model);
                break;
        }

        return message;
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        if (driverRepository.getByName(driverName) == null){
            throw  new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }else if (carRepository.getByName(carModel) == null){
            throw  new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        }

        Driver driverByName = driverRepository.getByName(driverName);
        Car byName = carRepository.getByName(carModel);

        driverByName.addCar(byName);

        driverRepository.add(driverByName);

        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        if (raceRepository.getByName(raceName) == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }else if (driverRepository.getByName(driverName) == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }

        Race byName = raceRepository.getByName(raceName);
        Driver driverByName = driverRepository.getByName(driverName);

        byName.addDriver(driverByName);

        raceRepository.add(byName);

        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {

        StringBuilder sb = new StringBuilder();

        if (raceRepository.getByName(raceName) == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }else if (raceRepository.getByName(raceName).getDrivers().size() < 3){
            throw  new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }

        List<Driver> collect = raceRepository.getByName(raceName).getDrivers()
                .stream()
                .sorted((d1, d2) -> (int) (d2.getCar()
                        .calculateRacePoints(raceRepository.getByName(raceName).getLaps())
                        - d1.getCar().calculateRacePoints(raceRepository.getByName(raceName).getLaps())))
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(String.format(OutputMessages.DRIVER_FIRST_POSITION, collect.get(0).getName(), raceName));
        System.out.println(String.format(OutputMessages.DRIVER_SECOND_POSITION, collect.get(1).getName(), raceName));
        System.out.println(String.format(OutputMessages.DRIVER_THIRD_POSITION, collect.get(2).getName(), raceName));

        return sb.toString();
    }

    @Override
    public String createRace(String name, int laps) {
        if (raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }
        Race race = new RaceImpl(name, laps);
        raceRepository.add(race);

        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
