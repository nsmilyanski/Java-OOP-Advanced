package spaceStation.core;

import spaceStation.common.Command;
import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private final Repository<Astronaut> astronautRepository;
    private final Repository<Planet> planetRepository;
    private int exploredPlanets = 0;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);

        for (String item : items) {
            planet.getItems().add(item);
        }

        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        if (astronautRepository.findByName(astronautName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        astronautRepository.remove(astronautRepository.findByName(astronautName));
        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = planetRepository.findByName(planetName);
        Mission mission = new MissionImpl();

        List<Astronaut> astronautList = new ArrayList<>();

        int numOfAstronauts = astronautList.size();

        for (Astronaut model : astronautRepository.getModels()) {
            if (model.getOxygen() > 60) {
                astronautList.add(model);
            }
        }

        if (astronautList.isEmpty()) {
            throw  new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        exploredPlanets++;

        mission.explore(planet, astronautList);
        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, numOfAstronauts  - astronautList.size());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d planets were explored!%n", exploredPlanets));
        sb.append("Astronauts info:%n");
        for (Astronaut model : astronautRepository.getModels()) {
            sb.append(String.format("Name: %s%n", model.getName()));
            sb.append(String.format("Oxygen: %.0f%n", model.getOxygen()));
            if (model.getBag().getItems().isEmpty()){
                sb.append("Bag items:  none");
            }else {
                sb.append("Bag items: ").append(model.getBag().getItems().stream()
                                .collect(Collectors.joining(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER)))
                        .append(System.lineSeparator());
            }

        }
        return sb.toString();
    }
}
