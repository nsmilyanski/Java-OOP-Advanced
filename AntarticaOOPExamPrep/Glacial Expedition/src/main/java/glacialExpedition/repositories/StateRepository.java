package glacialExpedition.repositories;

import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StateRepository implements Repository<State>{
    private Map<String, State> states;

    public StateRepository() {
        states = new LinkedHashMap<>();
    }

    @Override
    public Collection<State> getCollection() {
        return Collections.unmodifiableCollection(states.values());
    }

    @Override
    public void add(State entity) {
        states.putIfAbsent(entity.getName(), entity);
    }

    @Override
    public boolean remove(State entity) {
        return states.remove(entity) != null;
    }

    @Override
    public State byName(String name) {
        return states.get(name);
    }
}
