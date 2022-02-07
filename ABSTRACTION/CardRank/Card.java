package ABSTRACTION.CardRank;

public class Card {
    private CardsRank cardsRank;
    private FourSuits fourSuits;

    public Card(CardsRank cardsRank, FourSuits fourSuits) {
        this.cardsRank = cardsRank;
        this.fourSuits = fourSuits;
    }

    public int getPower(){
        return cardsRank.getValue() + fourSuits.getValue();
    }
}
