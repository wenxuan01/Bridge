import java.util.List;

class Hand {
    private final List<Card> cards;
    
    Hand(List<Card> cards) {
        this.cards = cards;
    }

    List<Card> getCards() {
        return this.cards;
    }

    List<Card> getCards(Suit suit) {
        return this.cards.stream()
                .filter(card -> card.getSuit() == suit)
                .sorted((a, b) -> b.compareRank(a))
                .toList();
    }

    Hand play(Card card) {
        return new Hand(this.cards.stream()
                .filter(c -> c != card)
                .toList());
    }
    
    private String getFormattedSuit(Suit suit) {
        return suit.getStr() + ": " + this.getCards(suit).stream()
                .map(card -> card.getRank().getStr() + " ")
                .reduce("", (x, y) -> x + y);
    }

    @Override
    public String toString() {
        return this.getFormattedSuit(Suit.CLUBS) + "\n"
                + this.getFormattedSuit(Suit.DIAMONDS) + "\n"
                + this.getFormattedSuit(Suit.HEARTS) + "\n"
                + this.getFormattedSuit(Suit.SPADES);
    }
}
