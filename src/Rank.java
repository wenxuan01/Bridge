public enum Rank {
    ACE ("Ace", "A", 14),
    KING ("King", "K", 13),
    QUEEN ("Queen", "Q", 12),
    JACK ("Jack", "J", 11),
    TEN ("Ten", "T", 10),
    NINE ("Nine", "9", 9),
    EIGHT ("Eight", "8", 8),
    SEVEN ("Seven", "7", 7),
    SIX ("Six", "6", 6),
    FIVE ("Five", "5", 5),
    FOUR ("Four", "4", 4),
    THREE ("Three", "3", 3),
    TWO ("Two", "2", 2);
    
    private final String longStr;
    private final String str;
    private final int value;

    Rank(String longStr, String str, int value) {
        this.longStr = longStr;
        this.str = str;
        this.value = value;
    }

    String getLongStr() {
        return longStr;
    }

    String getStr() {
        return str;
    }

    int compareValue(Rank other) {
        return this.value - other.value;
    }
}
