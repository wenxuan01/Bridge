import java.util.Optional;

class Card {
    private final CardSuit suit;
    private final Rank rank;

    Card(Rank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    Rank getRank() {
        return this.rank;
    }

    CardSuit getCardSuit() {
        return this.suit;
    }

    int compareRank(Card other) {
        return this.rank.compareValue(other.rank);
    }

    /*
    int compareOfCardSuit(Card other, CardSuit suit) {
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

    int compareTo(Card other, CardSuit lead, Optional<CardSuit> trump) {
        return trump.map(t -> {
                    int cmp = this.compareOfCardSuit(other, t);
                    return cmp == 0 ? this.compareOfCardSuit(other, lead) : cmp;
                })
                .orElse(this.compareOfCardSuit(other, lead));
        
    }
     */

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
