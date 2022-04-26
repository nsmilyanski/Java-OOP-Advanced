package spaceStation.models.astronauts;

import spaceStation.models.bags.Bag;

public class Biologist extends BaseAstronaut{
    private static final double BIOLOGIST_OXYGEN = 70;
    public Biologist(String name) {
        super(name, BIOLOGIST_OXYGEN);
    }

    @Override
    public void breath() {
        super.setOxygen(super.getOxygen() - 5);

        if (super.getOxygen() < 0) {
            super.setOxygen(0);
        }
    }
}
