package ABSTRACTION.JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            int[] dimestions = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            int x = dimestions[0];
            int y = dimestions[1];

            int[][] matrix = new int[x][y];

        fillMatrix(x, y, matrix);

        String command = scanner.nextLine();
            long sum = 0;
            while (!command.equals("Let the Force be with you")){
                int[] ivoStartPosition = Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                int[] evilPosition = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
                int evilRow = evilPosition[0];
                int evilCol = evilPosition[1];

                sum = matrixOperations(matrix, sum, ivoStartPosition, evilRow, evilCol);

                command = scanner.nextLine();
            }

        System.out.println(sum);


    }

    private static long matrixOperations(int[][] matrix, long sum, int[] ivoStartPosition, int evilRow, int evilCol) {
        while (evilRow >= 0 && evilCol >= 0) {
            if (inBounds(evilRow, evilCol, matrix)) {
                matrix[evilRow] [evilCol] = 0;
            }
            evilRow--;
            evilCol--;
        }

        int ivoRow = ivoStartPosition[0];
        int ivoCol = ivoStartPosition[1];

        while (ivoRow >= 0 && ivoCol < matrix[1].length) {
            if (inBounds(ivoRow, ivoCol, matrix)) {
                sum += matrix[ivoRow][ivoCol];
            }

            ivoCol++;
            ivoRow--;
        }
        return sum;
    }

    private static boolean inBounds(int row, int col, int[][] matrix){
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    private static void fillMatrix(int x, int y, int[][] matrix) {
        int value = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[i][j] = value++;
            }
        }
    }
}
