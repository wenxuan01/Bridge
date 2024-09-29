import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

class Auction {
    private static final int MIN_CONTRACT_LEVEL = 1;
    private static final int MAX_CONTRACT_LEVEL = 7;

    private final List<Bid> bids;
    private final Position dealer;
    private final Position nextBidder;

    public Auction(Position dealer) {
        this.bids = List.<Bid>of();
        this.dealer = dealer;
        this.nextBidder = dealer;
    }

    private Auction(List<Bid> bids, Position dealer, Position nextBidder) {
        this.bids = bids;
        this.dealer = dealer;
        this.nextBidder = nextBidder;
    }

    @Override
    public String toString() {
        return this.bids.toString();
    }

    List<Bid> getBids() {
        return this.bids;
    }

    Position getNextBidder() {
        return this.nextBidder;
    }

    boolean isPassed() {
        if (this.bids.size() < 4) {
            return false;
        }
        return this.bids.subList(bids.size() - 3, this.bids.size())
                .stream()
                .allMatch(bid -> bid.getBidType() == BidType.PASS);
    }

    boolean isDoubled() {
        return this.bids.stream()
                .filter(bid -> bid.getBidType() != BidType.PASS)
                .reduce((x, y) -> y)
                .map(bid -> bid.getBidType() == BidType.DOUBLE)
                .orElse(false);
    }

    boolean isRedoubled() {
        return this.bids.stream()
                .filter(bid -> bid.getBidType() != BidType.PASS)
                .reduce((x, y) -> y)
                .map(bid -> bid.getBidType() == BidType.REDOUBLE)
                .orElse(false);
    }

    Optional<Contract> getContract() {
        return this.bids.stream()
                .filter(bid -> bid.getBidType() == BidType.CONTRACT)
                .reduce((x, y) -> y)
                .flatMap(bid -> bid.getContract().map(bidContract -> {
                            if (this.isDoubled()) {
                                return bidContract.toDoubled();
                            } else if (this.isRedoubled()) {
                                return bidContract.toRedoubled();
                            } else {
                            return bidContract;
                            }
                        }));
    }

    Position getDeclarer(Position position, Optional<Suit> suit) {
        return this.bids.stream()
                .filter(bid -> bid.getBidType() == BidType.CONTRACT
                        && (bid.getPosition() == position 
                                || bid.getPosition() == position.partner())
                        && bid.getContract().map(bidContract ->
                                bidContract.getSuit().equals(suit))
                                .orElse(false))
                .findFirst()
                .map(bid -> bid.getPosition())
                .orElse(position);
    }

    Auction bid(Bid bid) {
        ArrayList<Bid> copy = new ArrayList<Bid>(this.bids);
        copy.add(bid);
        return new Auction(List.<Bid>copyOf(copy), 
                this.dealer, this.nextBidder.next());
    }

    boolean canBid(Bid bid) {
        return switch(bid.getBidType()) {
            case BidType.PASS -> true;
            case BidType.CONTRACT -> bid.getContract()
                    .map(bidContract -> this.getContract().map(contract -> 
                            bidContract.compareTo(contract) > 0)
                            .orElse(true)
                            && bidContract.getLevel() <= MAX_CONTRACT_LEVEL
                            && bidContract.getLevel() >= MIN_CONTRACT_LEVEL)
                    .orElse(false);
            case BidType.DOUBLE -> this.getContract()
                    .map(contract -> !contract.isDoubled()
                            && contract.getDeclarer() != nextBidder
                            && contract.getDeclarer() != nextBidder.partner())
                    .orElse(false);
            case BidType.REDOUBLE -> this.getContract()
                    .map(contract -> !contract.isRedoubled()
                            && contract.isDoubled()
                            && (contract.getDeclarer() == nextBidder
                                    || contract.getDeclarer() == nextBidder.partner()))
                    .orElse(false);
            default -> throw new IllegalStateException("Invalid bid: " + bid); 
        };
    }
}
