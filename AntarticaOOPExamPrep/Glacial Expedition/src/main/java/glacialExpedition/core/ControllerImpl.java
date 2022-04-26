package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.*;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Explorer> explorerRepository;
    private Repository<State> stateRepository;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        BaseExplorer baseExplorer;
        switch (type) {
            case "AnimalExplorer":
                baseExplorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                baseExplorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                baseExplorer = new NaturalExplorer(explorerName);
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.EXPLORER_DOES_NOT_EXIST);
        }
        explorerRepository.add(baseExplorer);
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        List<String> exhibitsList = List.of(exhibits);
        State state = new StateImpl(stateName);
        for (String s : exhibitsList) {
            state.getExhibits().add(s);
        }
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        String message = "";

        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }

        explorerRepository.remove(explorer);

        message = String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);

        return message;
    }

    @Override
    public String exploreState(String stateName) {

        Collection<Explorer> collection = explorerRepository.getCollection()
                .stream().filter(explorer -> explorer.getEnergy() > 50)
                .collect(Collectors.toList());

        int size = collection.size();

        if (collection.size() == 0) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        Mission mission = new MissionImpl();

        mission.explore(stateRepository.byName(stateName), collection);
        return String.format(ConstantMessages.STATE_EXPLORER, stateName, size - collection.size());
    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%d states were explored%n", explorerRepository.getCollection().size()));
        sb.append("Information for the explorers:%n");

        Collection<Explorer> collection = explorerRepository.getCollection();

        for (Explorer explorer : collection) {
            sb.append(String.format("Name: %s%n", explorer.getName()));
            sb.append(String.format("Energy: %.2f%n", explorer.getEnergy()));
            sb.append("Suitcase exhibits: {");
            sb.append(explorer.getSuitcase().getExhibits().stream().collect(Collectors.joining(", ")))
                    .append("}").append(System.lineSeparator());
        }

        return sb.toString();
    }
}
