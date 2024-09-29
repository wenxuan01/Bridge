import java.util.Optional;

class Card {
    private final Suit suit;
    private final Rank rank;

    Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    Rank getRank() {
        return this.rank;
    }

    Suit getSuit() {
        return this.suit;
    }

    int compareRank(Card other) {
        return this.rank.compareValue(other.rank);
    }

    int compareOfSuit(Card other, Suit suit) {
        if (this.suit == suit && other.suit == suit) {
            return this.compareRank(other);
        }
        if (this.suit == suit && other.suit != suit) {
            return 1;
        }
        if (this.suit != suit && other.suit == suit) {
            return -1;
        }
        return 0;
    }

    int compareTo(Card other, Suit lead, Optional<Suit> trump) {
        return trump.map(t -> {
                    int cmp = this.compareOfSuit(other, t);
                    return cmp == 0 ? this.compareOfSuit(other, lead) : cmp;
                })
                .orElse(this.compareOfSuit(other, lead));
        
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Card card) {
            return this.suit == card.suit && this.rank == card.rank;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.rank.getLongStr() + " of " + this.suit.getLongStr();
    }
}
