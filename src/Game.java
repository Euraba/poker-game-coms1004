
// add your own banner here
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private final Player player;
    private Deck deck;
    private final Scanner input ;
    private final HashMap<Character, Integer> suit = new HashMap<>() {{
        put('c', 1) ;
        put('h', 2) ;
        put('s', 3) ;
        put('d', 4) ;
    }} ;
    private final HashMap<String, Integer> multipliers = new HashMap<>() {{
        put("Nothing", 0) ;
        put("Pair", 1) ;
        put("Two pairs", 2) ;
        put("Three of a kind", 3) ;
        put("Straight", 4) ;
        put("Flush", 5) ;
        put("Full House", 6) ;
        put("Four of a kind", 25) ;
        put("Straight Flush", 50) ;
        put("Royal Flush", 250) ;
    }} ;
    String [] hand ;
    // you'll probably need some more here


    public Game(String[] testHand) {
        // This constructor is to help test your code.
        // use the contents of testHand to
        // make a hand for the player
        // use the following encoding for cards
        // c = clubs
        // d = diamonds
        // h = hearts
        // s = spades
        // 1-13 correspond to ace-king
        // example: s1 = ace of spades
        // example: testhand = {s1, s13, s12, s11, s10} = royal flush

        player = new Player() ;
        deck = new Deck() ;
        input = new Scanner(System.in) ;

        hand = testHand ;
    }

    public Game() {
        // This no-argument constructor is to actually play a normal game
        player = new Player() ;
        deck = new Deck() ;
        input = new Scanner(System.in) ;

    }

    public void play() {

        if (hand != null) {
            playTest() ;
        }

        // this method should play the game
        deck = new Deck() ;
        deck.shuffle();

        System.out.println("Hello! You just started a game of poker!");
        System.out.println("You have " + player.getBankroll() + " tokens in your account");

        // deck.showDeck();

        boolean wantsToPlay = true ;

        while (wantsToPlay && player.getBankroll() > 0) {

            int howManyCardsLeft = deck.howManyCardLeft() ;
            if (howManyCardsLeft < 5) {
                System.out.println("Sorry, but the deck is empty.");
                break;
            }

            double bet = getBet();

            dealCards();
            changeUnwantedCards();
            player.arrangeCards() ;

            computeHand(bet);

            System.out.println("You have " + player.getBankroll() + " tokens in your account");
            if (player.getBankroll() == 0) {
                break;
            }
            System.out.println("If you want to quit type -1, else type 1");

            int turn = input.nextInt() ;
            if (turn == -1) {
                wantsToPlay = false ;
            }

            player.loseCards() ;
        }

        if (player.getBankroll() == 0) {
            System.out.println("You are out of money! Good luck next time!");
        }
    }

    private double getBet() {
        System.out.println("How much do you want to bet?");
        double bet = input.nextDouble() ;

        while (bet > player.getBankroll()) {
            System.out.println("You don't have that much money!");
            System.out.println("Choose another amount");
            bet = input.nextDouble() ;
        }

        player.bets(bet);
        return bet;
    }

    private void computeHand(double bet) {
        String howIsTheHand = player.checkHand() ;
        int multiplier = multipliers.get(howIsTheHand) ;

        System.out.println("You have " + howIsTheHand + " !!!");

        if (multiplier == 0) {
            System.out.println("You lost the money!");
        } else {
            System.out.println("You won " + bet * multiplier + "!!!");
        }

        player.winnings(multiplier);
    }

    private void changeUnwantedCards() {
        System.out.println("How many cards do you want to change? ");
        ArrayList<Card> cardsToBeRemoved = new ArrayList<Card>() ;

        for (int howManyToChange = input.nextInt() ; howManyToChange > 0 ; -- howManyToChange) {
            System.out.println("What card do you wish to change? ") ;
            System.out.println("Input your answer as either the index of "
                    + " the card or the first letter of the symbol followed by the rank") ;
            System.out.println("If you wish to stop changing cards type -1");
            String change = input.next() ;
            int index = -1 ;

            do {
                if (change == "-1") {
                    break;
                }
                if (change.length() == 1) {
                    index = change.charAt(0) - 1 - '0';
                    if (index != -1) {
                        cardsToBeRemoved.add(player.cardAtIndex(index));
                        break;
                    }
                } else if (change.length() <= 3){
                    index = player.getIndexOfCard(new Card(change));
                    if (index != -1) {
                        cardsToBeRemoved.add(player.cardAtIndex(index));
                        break;
                    }
                }
                System.out.print("Enter a valid card!");
                change = input.next() ;
            } while (index == -1) ;
        }

        for (var card : cardsToBeRemoved) {
            player.removeCard(card);
        }

        for (int i = 0 ; i < cardsToBeRemoved.size() ; ++ i) {
            player.addCard(deck.deal());
        }
        player.showCards();
    }

    private void dealCards() {
        System.out.println("I will deal you your first 5 cards!");
        for (int i = 1 ; i <= 5 ; ++ i) {
            player.addCard(deck.deal());
        }
        System.out.println("Your cards are:");
        player.showCards();
    }

    private void playTest() {
        for (int i = 0 ; i < hand.length ; ++ i) {
            player.addCard(new Card(hand[i]));
        }
        player.showCards();
        String result = player.checkHand() ;

        System.out.println(result);
        System.exit(0);
    }

    public String checkHand(ArrayList<Card> hand) {
        // this method should take an ArrayList of cards
        // as input and then determine what evaluates to and
        // return that as a String

        return null;
    }


    // you will likely want many more methods here
    // per discussion in class
}
