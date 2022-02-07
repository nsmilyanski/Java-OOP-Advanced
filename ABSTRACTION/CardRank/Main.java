package ABSTRACTION.CardRank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String rank = scanner.nextLine();
        String suit = scanner.nextLine();

        Card card = new Card(CardsRank.valueOf(rank), FourSuits.valueOf(suit));

        System.out.printf("Card name: %s of %s; Card power: %d%n", rank, suit, card.getPower());
    }
}
