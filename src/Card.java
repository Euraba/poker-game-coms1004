
// add your own banner here

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Card implements Comparable<Card> {

    private final int suit; // use integers 1-4 to encode the suit
    private final int rank; // use integers 1-13 to encode the rank
    private final ArrayList<String> Suits =
            new ArrayList<>(Arrays.asList("", "diamonds",
                    "spades", "hearts", "clubs"));
    private final ArrayList<String> Ranks =
            new ArrayList<>(Arrays.asList("Jack", "Queen", "King")) ;

    private final HashMap<Character, Integer> suits = new HashMap<>() {{
        put('c', 4);
        put('h', 3);
        put('s', 2);
        put('d', 1);
    }} ;

    private String name ;

    /*
    suit 4 = clubs
    suit 3 = hearts
    suit 2 = spades
    suit 1 = diamonds
     */

    public Card(int s, int r) {
        //make a card with suit s and value v
        suit = s ;
        rank = r ;

        name = createName();
    }

    private String createName() {
        final String name;
        if (rank > 10) {
            name = Ranks.get(rank - 11) + "(" + rank + ")" + " of "
                    + Suits.get(suit) + "(" + suit + ")";
        } else if (rank == 1) {
            name = "Ace" + "(" + rank + ")" + " of " +
                    Suits.get(suit) + "(" + suit + ")" ;
        } else {
            name = rank + " of " + Suits.get(suit) + "(" + suit + ")" ;
        }
        return name;
    }

    public Card(String s) {
        if (s.length() == 2){
            if (!('a' <= s.charAt(0) && s.charAt(0) <= 'z') ||
                    !('0' <= s.charAt(1) && s.charAt(1) <= '9')) {
                suit = rank = -1 ;
                return;
            }
            suit = suits.get(s.charAt(0)) ;
            rank = s.charAt(1) - '0';
        } else if (s.length() == 3) {
            if (!('a' <= s.charAt(0) && s.charAt(0) <= 'z')
                    || !('0' <= s.charAt(1) && s.charAt(1) <= '9') ||
                    !('0' <= s.charAt(2) && s.charAt(2) <= '9')) {
                suit = rank = -1 ;
                return;
            }
            suit = suits.get(s.charAt(0)) ;
            int rank = (s.charAt(1) - '0') * 10 + (s.charAt(2) - '0');
            if (rank > 13) {
                rank = -1 ;
            }
            this.rank = rank ;
        } else {
            suit = -1 ;
            rank = -1 ;
        }

        name = createName();
    }

    @Override  public int compareTo(Card c) {
        // use this method to compare cards so they
        // may be easily sorted
        if (rank == c.rank) {
            return Integer.compare(this.suit, c.suit);
        }
        if (rank == 1) {
            return 1;
        }
        return Integer.compare(this.rank, c.rank) ;

    }

    public String toString() {
        // use this method to easily print a Card object
        String article = "a " ;
        if (name.charAt(0) == '8' || name.charAt(0) == 'A') {
            article = "an " ;
        }
        return article + name;
    }
    // add some more methods here if needed

    public boolean isRank(int rank) {
        return this.rank == rank ;
    }

    public boolean sameSuit(Card card) {
        return this.suit == card.suit ;
    }

    public boolean sameRank(Card card) {
        return this.rank == card.rank ;
    }

    public boolean oneLess(Card card) {
        return this.rank == card.rank - 1 ;
    }

    public String getName() {
        String ret = "" ;
        if (rank <= 10) {
            ret += String.valueOf(rank);
        } else {
            ret += Ranks.get(rank - 11) ;
        }
        if (suit == 1) {
            ret += " ◆ diamonds" ;
        } else if (suit == 2) {
            ret += " ♠ spades" ;
        } else if (suit == 3) {
            ret += " ♥ hearts" ;
        } else {
            ret += " ♣ clubs" ;
        }
        return ret ;
    }

}
