package restaurant.entities.healthyFoods;

public class Salad extends Food{
    private static final double SALAD_PORTION_SIZE = 150;

    public Salad(String name, double price) {
        super(name, SALAD_PORTION_SIZE, price);
    }
}
