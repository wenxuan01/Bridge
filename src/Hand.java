import java.util.List;

class Hand {
    private final List<Card> cards;
    
    Hand(List<Card> cards) {
        this.cards = cards;
    }

    List<Card> getCards() {
        return this.cards;
    }

    List<Card> getCards(CardSuit suit) {
        return this.cards.stream()
                .filter(card -> card.getCardSuit() == suit)
                .sorted((a, b) -> b.compareRank(a))
                .toList();
    }

    Hand play(Card card) {
        return new Hand(this.cards.stream()
                .filter(c -> c != card)
                .toList());
    }
    
    private String getFormattedCardSuit(CardSuit suit) {
        return suit.getStr() + ": " + this.getCards(suit).stream()
                .map(card -> card.getRank().getStr() + " ")
                .reduce("", (x, y) -> x + y);
    }

    @Override
    public String toString() {
        return this.getFormattedCardSuit(CardSuit.CLUBS) + "\n"
                + this.getFormattedCardSuit(CardSuit.DIAMONDS) + "\n"
                + this.getFormattedCardSuit(CardSuit.HEARTS) + "\n"
                + this.getFormattedCardSuit(CardSuit.SPADES);
    }
}
