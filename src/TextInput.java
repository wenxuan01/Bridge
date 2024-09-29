import java.util.Optional;

class TextInput {
    public static Optional<Optional<Suit>> decodeSuit(String s) {
        switch(s) {
            case "C": 
                return Optional.of(Optional.of(Suit.CLUBS));
            case "D":
                return Optional.of(Optional.of(Suit.DIAMONDS));
            case "H" :
                return Optional.of(Optional.of(Suit.HEARTS));
            case "S":
                return Optional.of(Optional.of(Suit.SPADES));
            case "NT":
                return Optional.of(Optional.empty());
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
