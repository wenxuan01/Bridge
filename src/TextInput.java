import java.util.Optional;

class TextInput {
    public static Optional<ContractSuit> decodeContractSuit(String s) {
        switch(s) {
            case "C": 
                return Optional.of(ContractSuit.CLUBS);
            case "D":
                return Optional.of(ContractSuit.DIAMONDS);
            case "H" :
                return Optional.of(ContractSuit.HEARTS);
            case "S":
                return Optional.of(ContractSuit.SPADES);
            case "NT":
                return Optional.of(ContractSuit.NOTRUMPS);
            default:
                System.out.println("Unable to decode suit " + s);
                return Optional.empty();
        }
    }

    public static Optional<Integer> decodeLevel(String s) {
        try {
            return Optional.of(Integer.decode(s));
        } catch (NumberFormatException e) {
            System.out.println("Unable to decode level " + s);
        }
        return Optional.empty();
    }

    public static Optional<Position> decodePosition(String s) {
        switch(s) {
            case "N":
                return Optional.of(Position.NORTH);
            case "E":
                return Optional.of(Position.EAST);
            case "S":
                return Optional.of(Position.SOUTH);
            case "W":
                return Optional.of(Position.WEST);
            default:
                System.out.println("Unable to decode position " + s);
                return Optional.empty();
        }
    }
}
