import java.util.Optional;

class Bid {
    private final BidType bidType;
    private final Optional<Contract> contract;
    private final Position position;

    Bid(BidType bidType, Optional<Contract> contract, Position position) {
        this.bidType = bidType;
        this.contract = contract;
        this.position = position;
    }

    BidType getBidType() {
        return this.bidType;
    }

    Optional<Contract> getContract() {
        return this.contract;
    }

    Position getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return this.position.getLongStr() + ": "  
                + ((this.bidType != BidType.CONTRACT) ? bidType.toString() + " " 
                : this.contract.map(contract -> contract.toStringShort())
                        .orElse("Invalid Contract Bid"));
    }
}
