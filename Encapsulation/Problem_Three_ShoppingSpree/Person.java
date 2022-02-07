package Encapsulation.Problem_Three_ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Person {
    private  String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.name = name;
        this.money = money;
        products = new ArrayList<>();
    }

    private void setName(String name) {
        if (name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }


    public String getName() {
        return name;
    }

    private void setMoney(double money) {
        if (money < 0){
            throw  new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }
    public  void buyProduct(Product product){
        if (this.money < product.getCost()){
            String message = String.format("%s can't afford %s", this.name, product.getName());
            throw new IllegalArgumentException(message);
        }
        products.add(product);
        money -= product.getCost();
    }

    @Override
    public String toString() {
        if (this.products.isEmpty()){
            return String.format("%s - Nothing bought", this.name);
        }
        String collect = products.stream().map(Objects::toString).collect(Collectors.joining(", "));

        return String.format("%s - %s", this.name, collect);
    }
}
