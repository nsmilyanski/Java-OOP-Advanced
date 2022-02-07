package Encapsulation.Problem_One_Box;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height){
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }


    private void setLength(double length) {
        if (length <= 0){
            throw new IllegalArgumentException("Length cannot be zero or negative.");
        }
        this.length = length;
    }

    private void setWidth(double width) {
        if (width <= 0){
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }
        this.width = width;
    }

    private void setHeight(double height) {
        if (height <= 0){
            throw new IllegalArgumentException("Height cannot be zero or negative.");
        }
        this.height = height;
    }
    public  double calculateSurfaceArea(){
        return (2 * this.width * height) + (2 * this.length * height) + (2 * length * width);
    }
    public  double calculateLateralSurfaceArea(){
        return (2 * this.width * height) + (2 * this.length * height);
    }
    public  double calculateVolume(){
        return this.width * this.length * this.height;
    }
}
