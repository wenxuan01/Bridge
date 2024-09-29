import java.util.Scanner;

abstract class Player {
    protected final Hand hand;
    protected final Position position;

    Player(Hand hand, Position position) {
        this.hand = hand;
        this.position = position;
    }

    Hand getHand() {
        return this.hand;
    }

    Position getPosition() {
        return this.position;
    }

    abstract Bid getBid(Auction auction, Scanner scanner);
}
