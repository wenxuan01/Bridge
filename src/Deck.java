import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Random;

class Deck {
    private static final int HAND_SIZE = 13;
    private static final int DECK_SIZE = 52;
    private final List<Card> cards;

    Deck(List<Card> cards) {
        this.cards = cards;
    }

    static Deck sortedDeck() {
        return new Deck(Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(CardSuit.values())
                        .map(suit -> new Card(rank, suit)))
                .toList());
    }

    static Deck randomDeck() {
        Random rand = new Random();
        int[] indices = new int[DECK_SIZE];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }
        for (int i = 0; i < indices.length; i++) {
            int randomIndexToSwap = rand.nextInt(indices.length);
            int temp = indices[randomIndexToSwap];
            indices[randomIndexToSwap] = indices[i];
            indices[i] = temp;
        }
        Deck deck = Deck.sortedDeck();
        return new Deck(IntStream.range(0, DECK_SIZE)
                .mapToObj(i -> deck.cards.get(indices[i]))
                .toList());
    }
    
    List<Hand> hands() {
        Hand hand1 = new Hand(this.cards.subList(0, HAND_SIZE));
        Hand hand2 = new Hand(this.cards.subList(HAND_SIZE, 
                    HAND_SIZE * 2));
        Hand hand3 = new Hand(this.cards.subList(HAND_SIZE * 2, 
                    HAND_SIZE * 3));
        Hand hand4 = new Hand(this.cards.subList(HAND_SIZE * 3, 
                    HAND_SIZE * 4));
        return List.<Hand>of(hand1, hand2, hand3, hand4);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
