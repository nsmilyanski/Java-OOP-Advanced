package Encapsulation.PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            String[] pizzaArr = scanner.nextLine().split("\\s+");

            String pizzaName = pizzaArr[1];
            int numberOfToppings = Integer.parseInt(pizzaArr[2]);
            Pizza pizza = new Pizza(pizzaName, numberOfToppings);

            String[] doughArr = scanner.nextLine().split("\\s+");

            String flourTye = doughArr[1];
            String bakingTechnique = doughArr[2];
            double weight = Double.parseDouble(doughArr[3]);

            Dough dough = new Dough(flourTye, bakingTechnique, weight);

            pizza.setDough(dough);
            String input = scanner.nextLine();

            while (!input.equals("END")) {
                String[] toppingArr = input.split("\\s+");

                String toppingType = toppingArr[1];
                double weightInGrams = Double.parseDouble(toppingArr[2]);
                Topping topping = new Topping(toppingType, weightInGrams);
                pizza.addTopping(topping);


                input = scanner.nextLine();
            }

            System.out.printf("%s - %.2f%n", pizza.getName(), pizza.getOverallCalories());
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}
