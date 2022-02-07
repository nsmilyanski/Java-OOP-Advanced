package ABSTRACTION.CardSuit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FourSuits[] fourSuits = FourSuits.values();

        System.out.println("Card Suits:");

        for (FourSuits cards: fourSuits) {
            System.out.printf("Ordinal value: %d; Name value: %S%n", cards.ordinal(), cards.name());
        }
    }
}
