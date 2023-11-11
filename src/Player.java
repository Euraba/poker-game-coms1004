/*
 * EB3591 11/10
 * Balasescu Eusebiu-Rares
 */

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
            System.out.println("Card #" + (i + 1) + " is "
                    + hand.get(i).getName());
        }
    }

    public void loseCards() {
        hand.clear();
    }

    public int getIndexOfCard(Card card) {
        if (card.isRank(-1)) {
            return -1 ;
        }
        int index = -1 ;
        for (int i = 0 ; i < hand.size() ; ++ i) {
            if (hand.get(i).compareTo(card) == 0) {
                return i ;
            }
        }
        return -2 ;
    }

    public Card cardAtIndex(int index) {
        return hand.get(index) ;
    }

    public void arrangeCards() {
        Collections.sort(hand);
    }

    public ArrayList<Card> getHand() {
        return hand ;
    }

    // you may wish to use more methods here
}


