
// add your own banner here
import java.util.*;

public class Game implements ConstantVariables{

    private final Player player;
    private Deck deck;
    private final Scanner input ;
    private String [] hand ;
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

        deck = new Deck() ;
        deck.shuffle();

        if (hand != null) {
            playTest() ;
        }

        // this method should play the game

        System.out.println("Hello! You just started a game of poker!");
        System.out.println("You have " + player.getBankroll() +
                " tokens in your account");

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

            System.out.println("You have " + player.getBankroll() +
                    " tokens in your account");
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

        while (bet > player.getBankroll() || bet > 5) {
            System.out.println("You don't have that much money or you chose to bet more than 5!");
            System.out.println("Choose another amount");
            bet = input.nextDouble() ;
        }

        player.bets(bet);
        return bet;
    }

    private void computeHand(double bet) {
        String howIsTheHand = checkHand(player.getHand()) ;
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
        System.out.println("How many cards do you want to change?");
        int howManyToChange = input.nextInt() ;
        ArrayList<Card> cardsToBeRemoved = new ArrayList<>() ;

        for (int i = 1 ; i <= howManyToChange ; ++ i) {
            changeCard(cardsToBeRemoved) ;
        }

        for (var card : cardsToBeRemoved) {
            player.removeCard(card);
            player.addCard(deck.deal());
        }

        player.showCards();
    }

    private void changeCard(ArrayList<Card> cardsToBeRemoved) {
        Card chosenCard = null;
        boolean chose = false ;
        System.out.println("Enter either the index of the card you " +
                "want to change or the first leter of the suit followed by" +
                " the rank.");
        while (!chose) {
            String card = input.next() ;

            int rank, suit ;

            if (Character.isDigit(card.charAt(0))) {
                rank = Integer.parseInt(card) ;
                rank -- ; // card for player starts at 0
                if (0 <= rank && rank <= 4 && !cardsToBeRemoved.contains(player.cardAtIndex(rank))) {
                    chose = true ;
                    chosenCard = player.cardAtIndex(rank) ;
                    break;
                } else {
                    System.out.println("You either already chose this card or chose an invalid index. Please try again.");
                }
                continue;
            }

            suit = suits.get(card.charAt(0)) ;
            rank = Integer.parseInt(card.substring(1)) ;

            if (!(1 <= rank && rank <= 13)) {
                System.out.println("That is not a valid rank. Please try again.");
                continue;
            }

            Card possibleCard = new Card(suit, rank) ;

            if (!cardsToBeRemoved.contains(possibleCard)) {
                chose = true ;
                int index = player.getIndexOfCard(possibleCard) ;
                chosenCard = player.cardAtIndex(index);
            } else {
                System.out.println("You already chosed this card. Please try again");
                continue;
            }
        }
        cardsToBeRemoved.add(chosenCard);
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
            Card aux = new Card(hand[i]) ;
            player.addCard(aux);
            deck.removeCard(aux) ;
        }
    }

    public String checkHand(ArrayList<Card> hand) {
        // this method should take an ArrayList of cards
        // as input and then determine what evaluates to and
        // return that as a String
        player.arrangeCards();
        boolean isRoyalFlush = royalFlush(hand) ;
        if (isRoyalFlush) {
            return "Royal Flush" ;
        }

        boolean isStraightFlush = straightFlush(hand) ;
        if (isStraightFlush) {
            return "Straight Flush" ;
        }

        boolean isFourOfAKind = fourOfAKind(hand) ;
        if (isFourOfAKind) {
            return "Four of a kind" ;
        }

        boolean isFullHouse= fullHouse(hand) ;
        if (isFullHouse) {
            return "Full House" ;
        }

        boolean isFlush = flush(hand) ;
        if (isFlush) {
            return "Flush" ;
        }

        boolean isStraight = straight(hand) ;
        if (isStraight) {
            return "Straight" ;
        }

        boolean isThreeOfAKinds = threeOfAKind(hand) ;
        if (isThreeOfAKinds) {
            return "Three of a kind" ;
        }

        boolean isTwoPairs = twoPairs(hand) ;
        if (isTwoPairs) {
            return "Two pairs" ;
        }

        boolean isPair = pair(hand) ;
        if (isPair) {
            return "Pair" ;
        }

        return "Nothing" ;
    }

    private boolean pair(ArrayList<Card> hand) {
        for (int i = 0 ; i + 1 < hand.size() ; ++ i) {
            if (hand.get(i).sameRank(hand.get(i + 1))) {
                return true ;
            }
        }
        return false ;
    }

    private boolean twoPairs(ArrayList<Card> hand) {
        int indexFirstPair = -1 ;
        for (int i = 0 ; i + 1 < hand.size() ; ++ i) {
            if (hand.get(i).sameRank(hand.get(i + 1))) {
                indexFirstPair = i ;
                break;
            }
        }
        if (indexFirstPair == -1) {
            return false ;
        }
        for (int i = indexFirstPair + 1 ; i + 1 < hand.size() ; ++ i) {
            if (hand.get(i).sameRank(hand.get(i + 1))) {
                return true ;
            }
        }
        return false ;
    }
    private boolean threeOfAKind(ArrayList<Card> hand) {
        for (int i = 0 ; i + 2 < hand.size() ; ++ i) {
            if (hand.get(i).sameRank(hand.get(i + 1)) &&
                    hand.get(i + 1).sameRank(hand.get(i + 2))) {
                return true ;
            }
        }
        return false ;
    }
    private boolean straight(ArrayList<Card> hand) {
        if (hand.get(0).isRank(1) && hand.get(1).isRank(10) &&
                hand.get(2).isRank(11) && hand.get(3).isRank(12) &&
                hand.get(4).isRank(13)) {
            return true ;
        }
        for (int i = 0 ; i < hand.size() - 1 ; ++ i) {
            if (!hand.get(i).oneLess(hand.get(i + 1))) {
                return false ;
            }
        }
        return true ;
    }

    private boolean flush(ArrayList<Card> hand) {
        return sameSuit(hand) ;
    }

    private boolean fullHouse(ArrayList<Card> hand) {
        if (hand.get(0).sameRank(hand.get(1)) &&
                hand.get(2).sameRank(hand.get(3)) &&
                hand.get(3).sameRank(hand.get(4))) {
            return true ;
        }
        if (hand.get(0).sameRank(hand.get(1)) &&
                hand.get(1).sameRank(hand.get(2)) &&
                hand.get(3).sameRank(hand.get(4))) {
            return true ;
        }
        return false ;
    }

    private boolean fourOfAKind(ArrayList<Card> hand) {
        if (hand.get(0).sameRank(hand.get(1)) &&
                hand.get(1).sameRank(hand.get(2)) &&
                hand.get(2).sameRank(hand.get(3))) {
            return true ;
        }
        if (hand.get(1).sameRank(hand.get(2)) &&
                hand.get(2).sameRank(hand.get(3)) &&
                hand.get(3).sameRank(hand.get(4))) {
            return true ;
        }
        return false ;
    }

    private boolean straightFlush(ArrayList<Card> hand) {
        if (!sameSuit(hand)) {
            return false ;
        }
        return straight(hand) ;
    }

    private boolean royalFlush(ArrayList<Card> hand) {
        if (hand.get(0).isRank(1) && hand.get(1).isRank(10) &&
                hand.get(2).isRank(11) && hand.get(3).isRank(12) &&
                hand.get(4).isRank(13)) {
            return sameSuit(hand);
        }
        return false ;
    }

    private boolean sameSuit(ArrayList<Card> hand) {
        for (int i = 0 ; i < hand.size() - 1 ; ++ i) {
            if (!hand.get(i).sameSuit(hand.get(i + 1))) {
                return false ;
            }
        }
        return true ;
    }


    // you will likely want many more methods here
    // per discussion in class
}
