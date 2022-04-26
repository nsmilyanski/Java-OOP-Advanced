package restaurant.entities.tables;

public class Indoors extends BaseTable {
    private static final double PRICE_PER_PERSON = 3.50;
    public Indoors(int number, int size) {
        super(number, size, PRICE_PER_PERSON);
    }

    @Override
    public String tableInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Table - %d%n", super.getTableNumber()));
        sb.append(String.format("Size - %d%n", super.getSize()));
        sb.append(String.format("Type - %s%n", this.getClass().getSimpleName()));
        sb.append(String.format("All price - %.2f%n", PRICE_PER_PERSON));
        return sb.toString();
    }
}
