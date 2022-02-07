package Encapsulation.PizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        switch (flourType){
            case "White":
            case "Wholegrain":
                this.flourType = flourType;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setBakingTechnique(String bakingTechnique) {
        switch (bakingTechnique){
            case "Crispy":
            case "Chewy":
            case "Homemade":
                this.bakingTechnique = bakingTechnique;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }

    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200){
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }
    public double calculateCalories(){

        double bakingTechniqueValue = 0;
        switch (this.bakingTechnique){
            case "Crispy":
                bakingTechniqueValue = 0.9;
                break;
            case "Chewy":
                bakingTechniqueValue = 1.1;
                break;
            case "Homemade":
                bakingTechniqueValue = 1.0;
                break;

        }
                double flourTypeValue = 0.0;

        switch (this.flourType){
            case "White":
                flourTypeValue = 1.5;
                break;
            case "Wholegrain":
                flourTypeValue = 1.0;
                break;
        }

        return 2 * weight * bakingTechniqueValue * flourTypeValue;
    }
}
