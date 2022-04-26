package catHouse.entities.houses;

import catHouse.entities.cat.Cat;

public class LongHouse extends BaseHouse{
    public LongHouse(String name) {
        super(name, 30);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s %s:", this.getName(), this.getClass().getSimpleName())).append(System.lineSeparator());
        sb.append("Cats: ");

        if (super.getCats().size() == 0){
            sb.append("none ");
        }else {
            for (Cat cat : super.getCats()) {
                sb.append(cat.getName()).append(" ");
            }
        }
        sb.append(System.lineSeparator());
        sb.append(String.format("Toys: %d Softness: %d%n", super.getToys().size(), super.sumSoftness()));
        return sb.toString();
    }
}
