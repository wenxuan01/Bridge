import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Bridge simulator! Here are all the  hands. You are playing in all positions.");
        List<Hand> hands = Deck.randomDeck().hands();
        Player north = new UserPlayer(hands.get(0), Position.NORTH);
        Player east = new UserPlayer(hands.get(1), Position.EAST);
        Player south = new UserPlayer(hands.get(2), Position.SOUTH);
        Player west = new UserPlayer(hands.get(3), Position.WEST);
        
        HashMap<Position,Player> players = new HashMap<>();
        players.put(Position.NORTH, north);
        players.put(Position.EAST, east);
        players.put(Position.SOUTH, south);
        players.put(Position.WEST, west);
        
        System.out.println("North: ");
        System.out.println(north.getHand());
        System.out.println("East: ");
        System.out.println(east.getHand());
        System.out.println("South: ");
        System.out.println(south.getHand());
        System.out.println("West: ");
        System.out.println(west.getHand());
        Position dealer = null;

        Scanner scanner = new Scanner(System.in);

        while (dealer == null) {
            System.out.println("Who shall be the dealer? N/S/E/W");
            String inputDealer = scanner.next();
            switch(inputDealer) {
                case "N": dealer = Position.NORTH;
                          break;
                case "E": dealer = Position.EAST;
                          break;
                case "S": dealer = Position.SOUTH;
                          break;
                case "W": dealer = Position.WEST;
                          break;
                default: System.out.println("Invalid input");
            }
        }
        System.out.println("Dealer is " + dealer);
        
        Auction auction = new Auction(dealer);
        while (!auction.isPassed()) {
            Player nextBidder = players.get(auction.getNextBidder());
            Bid nextBid = nextBidder.getBid(auction, scanner);
            auction = auction.bid(nextBid);
            System.out.println(nextBid);
        }
        System.out.println(auction);
        System.out.println(auction.getContract()
                .map(contract -> "Final contract is: " +  contract.toString())
                .orElse("Passed out"));
        


        scanner.close();
        System.out.println("End of program");
    }
}
