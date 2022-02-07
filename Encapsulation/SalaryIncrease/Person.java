package Encapsulation.SalaryIncrease;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setSalary(salary);
    }
    public void increaseSalary(double bonus){
        if (this.age < 30){
            this.setSalary(this.getSalary() + (this.getSalary() * bonus / 200));
        }else {
            this.setSalary(this.getSalary() + (this.getSalary() * bonus / 100));
        }
    }

    public void setFirstName(String firstName) {
        if (firstName.length() < 3){
            throw  new IllegalArgumentException("First name cannot be less than 3 symbols ");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        if (age <= 0){
            throw  new IllegalArgumentException("Age cannot be zero or negative integer");
        }
        this.age = age;
    }

    public void setSalary(double salary) {
//        if (salary < 460){
//            throw  new IllegalArgumentException("Salary can not be less than 460 leva");
//        }
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s %s gets %f leva", firstName, lastName, salary);
    }
}
