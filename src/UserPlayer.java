import java.util.Scanner;
import java.util.Optional;

class UserPlayer extends Player {
    UserPlayer(Hand hand, Position position) {
        super(hand, position);
    }

    @Override
    Bid getBid(Auction auction, Scanner scanner) {
        Bid bid = null;
        while(bid == null || !auction.canBid(bid)) {
            System.out.println("Please input a bid for " 
                    + super.position.getLongStr() + " eg. P/X/XX/1C/1NT");
            String inputBid = scanner.next();
            switch(inputBid) {
                case "P": 
                    bid = new Bid(BidType.PASS, Optional.empty(), 
                            super.position);
                    break;
                case "X": 
                    bid = new Bid(BidType.DOUBLE, Optional.empty(),
                            super.position);
                    break;
                case "XX": 
                    bid = new Bid(BidType.REDOUBLE, Optional.empty(),
                            super.position);
                    break;
                default:
                    Optional<Integer> level = TextInput.decodeLevel(inputBid
                            .substring(0, 1));
                    Optional<ContractSuit> suit = TextInput.decodeContractSuit(inputBid
                            .substring(1, inputBid.length()));
                    bid = level.flatMap(l -> suit
                            .map(s -> new Bid(BidType.CONTRACT,
                                    Optional.of(new Contract(s, l, 
                                            auction.getDeclarer(super.position, 
                                                    s))),
                                    super.position)))
                        .orElse(null);
            }
        }
        return bid;
    }
}
