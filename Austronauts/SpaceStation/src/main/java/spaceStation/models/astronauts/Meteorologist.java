package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut{
    private static final double Meteorologist_OXYGEN = 90;
    public Meteorologist(String name) {
        super(name, Meteorologist_OXYGEN);
    }
}
