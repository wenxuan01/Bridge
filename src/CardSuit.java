public enum CardSuit {
    CLUBS ("Clubs", "C"),
    DIAMONDS ("Diamonds", "D"),
    HEARTS ("Hearts", "H"),
    SPADES ("Spades", "S");

    private final String longStr;
    private final String str;

    CardSuit(String longStr, String str) {
        this.longStr = longStr;
        this.str = str;
    }

    String getLongStr() {
        return longStr;
    }

    String getStr() {
        return str;
    }
}
