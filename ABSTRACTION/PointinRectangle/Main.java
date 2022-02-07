package ABSTRACTION.PointinRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();

        int bottomLeftX = array[0];
        int bottomLeftY = array[1];
        int topRightX = array[2];
        int topRightY = array[3];

        Point leftP = new Point(bottomLeftX, bottomLeftY);
        Point rightP = new Point(topRightX, topRightY);

        Rectangle rectangle = new Rectangle(leftP, rightP);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt)
                    .toArray();
            int x = tokens[0];
            int y = tokens[1];
            Point newPoint = new Point(x, y);
            System.out.println(rectangle.contains(newPoint));

        }


    }
}
