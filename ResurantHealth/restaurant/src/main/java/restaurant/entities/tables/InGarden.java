package restaurant.entities.tables;

public class InGarden extends BaseTable{
    private static final double pricePerPerson = 4.50;
    public InGarden(int number, int size) {
        super(number, size, pricePerPerson);
    }

    @Override
    public String tableInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Table - %d%n", super.getTableNumber()));
        sb.append(String.format("Size - %d%n", super.getSize()));
        sb.append(String.format("Type - %s%n", this.getClass().getSimpleName()));
        sb.append(String.format("All price - %.2f%n", pricePerPerson));
        return sb.toString();
    }
}
