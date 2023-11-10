
// add your own banner here

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player {


    private final ArrayList<Card> hand; // the player's cards
    private double bankroll;
    private double bet;

    // you may choose to use more instance variables

    public Player() {
        // create a player here
        hand = new ArrayList<>() ;
        bankroll = 10 ;

    }

    public void addCard(Card c) {
        // add the card c to the player's hand
        hand.add(c);
    }

    public void removeCard(Card c) {
        // remove the card c from the player's hand
        hand.remove(c) ;
    }

    public void bets(double amt) {
        // player makes a bet
        bankroll -= amt ;
        bet = amt ;
    }

    public void winnings(double odds) {
        //	adjust bankroll if player wins
        bankroll += odds * bet ;
    }

    public double getBankroll() {
        // return current balance of bankroll
        return bankroll;
    }

    public void showCards() {
        for (int i = 0 ; i < hand.size() ; ++ i) {
            System.out.println("Card #" + (i + 1) + " is " + hand.get(i).toString());
        }
    }

    public void loseCards() {
        hand.clear();
    }

    public int getIndexOfCard(Card card) {
        int index = -1 ;
        for (int i = 0 ; i < hand.size() ; ++ i) {
            if (hand.get(i).compareTo(card) == 0) {
                return i ;
            }
        }
        return index ;
    }

    public Card cardAtIndex(int index) {
        return hand.get(index) ;
    }

    public void arrangeCards() {
        Collections.sort(hand);
    }

    public String checkHand() {
        boolean isRoyalFlush = royalFlush() ;
        if (isRoyalFlush) {
            return "Royal Flush" ;
        }

        boolean isStraightFlush = straightFlush() ;
        if (isStraightFlush) {
            return "Straight Flush" ;
        }

        boolean isFourOfAKind = fourOfAKind() ;
        if (isFourOfAKind) {
            return "Four of a kind" ;
        }

        boolean isFullHouse= fullHouse() ;
        if (isFullHouse) {
            return "Full House" ;
        }

        boolean isFlush = flush() ;
        if (isFlush) {
            return "Flush" ;
        }

        boolean isStraight = straight() ;
        if (isStraight) {
            return "Straight" ;
        }

        boolean isThreeOfAKinds = threeOfAKind() ;
        if (isThreeOfAKinds) {
            return "Three of a kind" ;
        }

        boolean isTwoPairs = twoPairs() ;
        if (isTwoPairs) {
            return "Two pairs" ;
        }

        boolean isPair = pair() ;
        if (isPair) {
            return "Pair" ;
        }

        return "No pair" ;
    }

    private boolean pair() {
        for (int i = 0 ; i + 1 < hand.size() ; ++ i) {
            if (hand.get(i).sameRank(hand.get(i + 1))) {
                return true ;
            }
        }
        return false ;
    }

    private boolean twoPairs() {
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
    private boolean threeOfAKind() {
        for (int i = 0 ; i + 2 < hand.size() ; ++ i) {
            if (hand.get(i).sameRank(hand.get(i + 1)) &&
                    hand.get(i + 1).sameRank(hand.get(i + 2))) {
                return true ;
            }
        }
        return false ;
    }
    private boolean straight() {
        if (hand.get(0).isRank(1) && hand.get(1).isRank(10) &&
                hand.get(0).isRank(11) && hand.get(0).isRank(12) &&
                hand.get(0).isRank(13)) {
            return true ;
        }
        for (int i = 0 ; i < hand.size() - 1 ; ++ i) {
            if (!hand.get(i).oneLess(hand.get(i + 1))) {
                return false ;
            }
        }
        return true ;
    }

    private boolean flush() {
        return sameSuit() ;
    }

    private boolean fullHouse() {
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

    private boolean fourOfAKind() {
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

    private boolean straightFlush() {
        if (!sameSuit()) {
            return false ;
        }
        return straight() ;
    }

    private boolean royalFlush() {
        if (hand.get(0).isRank(1) && hand.get(1).isRank(10) &&
                hand.get(0).isRank(11) && hand.get(0).isRank(12) &&
                hand.get(0).isRank(13)) {
            return sameSuit();
        }
        return false ;
    }

    private boolean sameSuit() {
        for (int i = 0 ; i < hand.size() - 1 ; ++ i) {
            if (!hand.get(i).sameSuit(hand.get(i + 1))) {
                return false ;
            }
        }
        return true ;
    }

    // you may wish to use more methods here
}


