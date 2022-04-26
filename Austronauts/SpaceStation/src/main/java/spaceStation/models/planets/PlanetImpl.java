package spaceStation.models.planets;

import spaceStation.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;

public class PlanetImpl implements Planet{
    private String name;
    private Collection<String> items;

    public PlanetImpl(String name) {
        this.setName(name);
        this.items = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<String> getItems() {
        return items;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
