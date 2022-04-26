package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalEarned;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
       HealthyFood current;
       if (type.equals("Salad")){
           current = new Salad(name,price);
       }else{
           current = new VeganBiscuits(name,price);
       }
       if (healthFoodRepository.foodByName(name) != null){
           throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST,name));
       }
       healthFoodRepository.add(current);
       return String.format(OutputMessages.FOOD_ADDED,name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        Beverages current;
        if (type.equals("Fresh")){
            current = new Fresh(name,counter,brand);
        }else{
            current = new Smoothie(name,counter,brand);
        }
        if (beverageRepository.beverageByName(name,brand) != null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST,name));
        }
        beverageRepository.add(current);
        return String.format(OutputMessages.BEVERAGE_ADDED,type,brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table current;
        if (type.equals("Indoors")){
            current = new Indoors(tableNumber,capacity);
        }else{
            current = new InGarden(tableNumber,capacity);
        }
        if (tableRepository.byNumber(tableNumber) != null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED,tableNumber));
        }
        tableRepository.add(current);
        return String.format(OutputMessages.TABLE_ADDED,tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        for (Table currentTable : tableRepository.getAllEntities()) {
            if (currentTable.getSize() >= numberOfPeople && !currentTable.isReservedTable()){
                currentTable.reserve(numberOfPeople);
                return String.format(OutputMessages.TABLE_RESERVED,currentTable.getTableNumber(),numberOfPeople);
            }
        }
        throw new IllegalArgumentException(String.format(OutputMessages.RESERVATION_NOT_POSSIBLE,numberOfPeople));
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table searchedTable;
        if (tableRepository.byNumber(tableNumber) == null){
            return String.format(OutputMessages.WRONG_TABLE_NUMBER,tableNumber);
        }
            searchedTable = tableRepository.byNumber(tableNumber);

        if (healthFoodRepository.foodByName(healthyFoodName) == null){
            return String.format(OutputMessages.NONE_EXISTENT_FOOD,healthyFoodName);
        }
        HealthyFood searchedHealthyFood =healthFoodRepository.foodByName(healthyFoodName);
        searchedTable.orderHealthy(searchedHealthyFood);
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL,healthyFoodName,tableNumber);

    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table searchedTable;
        if (tableRepository.byNumber(tableNumber) == null){
            return String.format(OutputMessages.WRONG_TABLE_NUMBER,tableNumber);
        }
        searchedTable = tableRepository.byNumber(tableNumber);
        if (beverageRepository.beverageByName(name,brand) == null){
            return String.format(OutputMessages.NON_EXISTENT_DRINK,name,brand);
        }
        Beverages searchedBeverage = beverageRepository.beverageByName(name,brand);
        searchedTable.orderBeverages(searchedBeverage);
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL,name,tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table searchedTable = tableRepository.byNumber(tableNumber);
        double bill = searchedTable.bill();
        searchedTable.clear();
        totalEarned += bill;
        return String.format(OutputMessages.BILL,tableNumber,bill);
    }


    @Override
    public String totalMoney() {
        return String.format(OutputMessages.TOTAL_MONEY,totalEarned);
    }
}
