package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;
import glacialExpedition.models.suitcases.Suitcase;

import java.util.Collection;

public class MissionImpl implements Mission {
    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        for (Explorer explorer: explorers) {
            if (!explorer.canSearch()) {
                continue;
            }
            for (String exhibit : state.getExhibits()) {
                if (explorer.canSearch()) {
                    explorer.getSuitcase().getExhibits().add(exhibit);
                    explorer.search();
                }
            }

            state.getExhibits().removeAll(explorer.getSuitcase().getExhibits());

        }
    }
}
