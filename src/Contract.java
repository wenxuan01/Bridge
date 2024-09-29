import java.util.Optional;

class Contract {
    private final Optional<Suit> suit;
    private final int level;
    private final boolean doubled;
    private final boolean redoubled;
    private final Position declarer;

    Contract(Optional<Suit> suit, int level, Position declarer) {
        this(suit, level, declarer, false, false);
    }

    private Contract(Optional<Suit> suit, int level, Position declarer,
            boolean doubled, boolean redoubled) {
        this.suit = suit;
        this.level = level;
        this.declarer = declarer;
        this.doubled = doubled;
        this.redoubled = redoubled;
    }
    
    Contract toDoubled() {
        return new Contract(this.suit, this.level, this.declarer, true, false);
    }

    Contract toRedoubled() {
        return new Contract(this.suit, this.level, this.declarer, true, true);
    }

    int contractPoints(boolean vul, int tricksTaken) {
        return 0;
    }

    int compareTo(Contract other) {
        if (this.level != other.level) {
            return this.level - other.level;
        }
        return this.suit.map(thisSuit -> other.suit
                .map(otherSuit -> thisSuit.compareOrder(otherSuit))
                        .orElse(-1))
                .orElse(other.suit.isPresent() ? 1 : 0);
    }
    
    Optional<Suit> getSuit() {
        return this.suit;
    }

    int getLevel() {
        return this.level;
    }

    Position getDeclarer() {
        return this.declarer;
    }

    boolean isDoubled() {
        return this.doubled;
    }

    boolean isRedoubled() {
        return this.redoubled;
    }
    
    String toStringShort() {
        return this.level + this.suit.map(s -> s.getStr())
                .orElse("NT");
    }

    @Override
    public String toString() {
        return this.toStringShort()
                + (this.doubled ? "X" : "")
                + (this.redoubled ? "X" : "")
                + " by " + this.declarer.getLongStr();
    }
}
