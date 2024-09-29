public enum ContractSuit {
    CLUBS ("Clubs", "C", 1),
    DIAMONDS ("Diamonds", "D", 2),
    HEARTS ("Hearts", "H", 3),
    SPADES ("Spades", "S", 4),
    NOTRUMPS ("Notrumps", "NT", 5);

    private final String longStr;
    private final String str;
    private final int order;

    ContractSuit(String longStr, String str, int order) {
        this.longStr = longStr;
        this.str = str;
        this.order = order;
    }

    int compareOrder(ContractSuit other) {
        return this.order - other.order;
    }

    String getLongStr() {
        return longStr;
    }

    String getStr() {
        return str;
    }
}
