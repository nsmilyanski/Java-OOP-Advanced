package Encapsulation.Problem_Two_AnimalFarm;

public class Chicken {
    private String name;
    private  int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private void setName(String name) {
        if (name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    private void setAge(int age) {
        if (age > 15 || age < 0){
            throw  new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }
    private double calculateProductPerDay(){
        if (this.age < 6){
            return 2.00;
        }else if (this.age < 12){
            return 1.00;
        }
        return 0.75;
    }
    public double productPerDay(){
        return this.calculateProductPerDay();
    }

    @Override
    public String toString() {
        return String.format("Chicken Choko (age %d)can produce %.2f eggs per day.", this.age,
                this.productPerDay());
    }
}
