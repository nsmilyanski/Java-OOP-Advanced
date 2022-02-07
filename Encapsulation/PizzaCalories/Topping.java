package Encapsulation.PizzaCalories;

public class Topping {
    private String toppingType;
    private  double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        switch (toppingType){
            case "Meat":
            case "Veggies":
            case "Cheese":
            case "Sauce":
                this.toppingType = toppingType;
                break;
            default:
                String message = String.format("Cannot place %s on top of your pizza.", toppingType);
                throw  new IllegalArgumentException(message);
        }

    }

    private void setWeight(double weight) {
        if (weight< 1 || weight > 50){
            String messsage = String.format("%s weight should be in the range [1..50].", this.toppingType);
            throw  new IllegalArgumentException(messsage);
        }
        this.weight = weight;
    }

    public  double calculateCalories(){
        double toppingTypeValue = 0.0;
        switch (this.toppingType){
            case "Meat":
                toppingTypeValue = 1.2;
                break;
            case "Veggies":
                toppingTypeValue = 0.8;
                break;
            case "Cheese":
                toppingTypeValue = 1.1;
                break;
            case "Sauce":
                toppingTypeValue = 0.9;
                break;
        }
        return this.weight * 2 * toppingTypeValue;

    }
}
