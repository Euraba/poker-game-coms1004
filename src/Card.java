
// add your own banner here

public class Card implements Comparable<Card>, ConstantVariables {

    private final int suit; // use integers 1-4 to encode the suit
    private final int rank; // use integers 1-13 to encode the rank
    private final String name ;

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
            name = ranks.get(rank - 11) + "(" + rank + ")" + " of "
                    + suitsName.get(suit) + "(" + suit + ")";
        } else if (rank == 1) {
            name = "Ace" + "(" + rank + ")" + " of " +
                    suitsName.get(suit) + "(" + suit + ")" ;
        } else {
            name = rank + " of " + suitsName.get(suit) + "(" + suit + ")" ;
        }
        return name;
    }

    public Card(String s) {

        this.suit = suits.get(s.charAt(0)) ;
        this.rank = Integer.parseInt(s.substring(1));

        name = createName();
    }

    @Override  public int compareTo(Card c) {
        // use this method to compare cards so they
        // may be easily sorted
        if (rank == c.rank) {
            return Integer.compare(this.suit, c.suit);
        }
        /*if (rank == 1) {
            return 1;
        }
        return Integer.compare(this.rank, c.rank) ;*/
        if (rank < c.rank) {
            return -1 ;
        }
        return 1 ;

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
        if (rank == 1) {
            ret += "Ace" ;
        } else if (rank <= 10) {
            ret += String.valueOf(rank);
        } else {
            ret += ranks.get(rank - 11) ;
        }

        ret += suitsChar.get(suit) ;

        return ret ;
    }

}
