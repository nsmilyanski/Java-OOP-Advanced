package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerImpl implements Controller{
    private ToyRepository toys;
    private Map<String, House> houses;
    @Override
    public String addHouse(String type, String name) {
        switch (type) {
            case "ShortHouse":
                houses.put(name, new ShortHouse(name));
                break;
            case "LongHouse":
                houses.put(name, new LongHouse(name));
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    public ControllerImpl() {
        houses = new LinkedHashMap<>();
        toys = new ToyRepository();
    }

    @Override
    public String buyToy(String type) {
        switch (type) {
            case "Ball":
                toys.buyToy(new Ball());
                break;
            case "Mouse":
                toys.buyToy(new Mouse());
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);
        if (toy == null){
            throw  new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }

        for (House house : houses.values()) {
            if (house.getName().equals(houseName)) {
                house.buyToy(toy);
                break;
            }
        }

        toys.removeToy(toy);

        return String.format(String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName));
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        if (houses.get(houseName) != null) {
            if (catType.equals("ShorthairCat")) {
            Cat cat1 = new ShorthairCat(catName, catBreed, price);
                if (!houses.get(houseName).getClass().getSimpleName().equals("ShortHouse")) {
                    return ConstantMessages.UNSUITABLE_HOUSE;
                }
                houses.get(houseName).addCat(new ShorthairCat(catName, catBreed, price));
            }else if (catType.equals("LonghairCat")) {
                Cat cat = new LonghairCat(catName, catBreed, price);
                if (!houses.get(houseName).getClass().getSimpleName().equals("LongHouse")) {
                    return ConstantMessages.UNSUITABLE_HOUSE;
                }
                houses.get(houseName).addCat(cat);
            }else  {
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
            }
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        int numCat = 0;

        numCat = houses.get(houseName).getCats().size();
        houses.get(houseName).feeding();

        return String.format(ConstantMessages.FEEDING_CAT, numCat);
    }

    @Override
    public String sumOfAll(String houseName) {
        double sum = 0;

        sum += houses.get(houseName).getCats().stream().mapToDouble(Cat::getPrice).sum();
        sum += houses.get(houseName).getToys().stream().mapToDouble(Toy::getPrice).sum();

        return String.format(ConstantMessages.VALUE_HOUSE,houseName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (House house : houses.values()) {
            sb.append(house.getStatistics());
        }

        return sb.toString();
    }
}
