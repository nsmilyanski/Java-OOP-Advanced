package ABSTRACTION.StudentSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentSystem studentSystem = new StudentSystem();

        String input =scanner.nextLine();

        while (!input.equals("Exit")) {
            String[] inputArr = input.split("\\s+");

            studentSystem.ParseCommand(inputArr);
            input = scanner.nextLine();
        }
    }
}
