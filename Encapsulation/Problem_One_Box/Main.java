package Encapsulation.Problem_One_Box;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double length = Double.parseDouble(scanner.nextLine());
        double width = Double.parseDouble(scanner.nextLine());
        double height = Double.parseDouble(scanner.nextLine());

        try {
        Box box = new Box(length, width, height);
            double surfaceArea = box.calculateSurfaceArea();
            System.out.println(surfaceArea);
            double v = box.calculateLateralSurfaceArea();
            System.out.println(v);
            double volume = box.calculateVolume();
            System.out.println(volume);


        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }


    }
}
