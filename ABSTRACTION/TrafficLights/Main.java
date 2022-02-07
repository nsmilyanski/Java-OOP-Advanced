package ABSTRACTION.TrafficLights;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");

        int n = Integer.parseInt(scanner.nextLine());

        List<TrafficLight> trafficLightList = new ArrayList<>();
        for (String lights: input) {
            Lights lights1 = Lights.valueOf(lights);
            TrafficLight trafficLight = new TrafficLight(lights1);
            trafficLightList.add(trafficLight);
        }

        for (int i = 0; i < n; i++) {

            for (TrafficLight trafics: trafficLightList) {
                trafics.changeColor();
                System.out.print(trafics.getLight() + " ");
            }
            System.out.println();
        }


    }
}
