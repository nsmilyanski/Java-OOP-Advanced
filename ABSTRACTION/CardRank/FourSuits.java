package ABSTRACTION.CardRank;

public enum FourSuits {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

   private int value;

    FourSuits(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
