package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {
    private Collection<Beverages> entities;

    public BeverageRepositoryImpl() {
        entities = new ArrayList<>();
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        for (Beverages beverage : entities) {
            if (beverage.getBrand().equals(drinkBrand) && beverage.getName().equals(drinkName)){
                return beverage;
            }
        }
        return null;
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public void add(Beverages entity) {
        entities.add(entity);
    }
}
