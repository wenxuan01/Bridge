public enum Suit {
    CLUBS ("Clubs", "C", 1),
    DIAMONDS ("Diamonds", "D", 2),
    HEARTS ("Hearts", "H", 3),
    SPADES ("Spades", "S", 4);

    private final String longStr;
    private final String str;
    private final int order;

    Suit(String longStr, String str, int order) {
        this.longStr = longStr;
        this.str = str;
        this.order = order;
    }

    int compareOrder(Suit other) {
        return this.order - other.order;
    }

    String getLongStr() {
        return longStr;
    }

    String getStr() {
        return str;
    }
}
