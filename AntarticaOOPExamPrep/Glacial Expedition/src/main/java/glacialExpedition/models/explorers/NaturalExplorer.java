package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    public NaturalExplorer(String name) {
        super(name, 60);
    }


    @Override
    public void search() {
        super.setEnergy(super.getEnergy() - 7);
        if (super.getEnergy() < 0) {
            super.setEnergy(0);
        }
    }
}
