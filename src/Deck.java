
// add your own banner here
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random ;
public class Deck {

    private Card[] cards;
    private int top; // the index of the top of the deck
    private final Random randomGenerator;

    // add more instance variables if needed

    public Deck() {
        // make a 52 card deck here
        cards = new Card[52] ;
        top = 0 ;
        randomGenerator = new Random() ;

        for (int suit = 1 ; suit <= 4 ; ++ suit) {
            for (int rank = 1 ; rank <= 13 ; ++ rank) {
                cards[top ++] = new Card(suit, rank);
            }
        }
        top -- ;
        // top is now 51
    }

    public void shuffle() {
        // shuffle the deck here
        for (int index = 0; index < cards.length; ++ index) {
            int newIndex = randomGenerator.nextInt(cards.length - index)
                    + index ;

            Card intermadiar = cards[index] ;
            cards[index] = cards[newIndex] ;
            cards[newIndex] = intermadiar ;
        }
    }

    public Card deal() {
        // deal the top card in the deck
        if (top == -1) {
            return null ;
        }
        return cards[top --] ;
    }

    public void removeCard(Card card) {
        ArrayList<Card> temp = new ArrayList<>(Arrays.asList(cards));
        temp.remove(card) ;
        cards = (Card[]) temp.toArray();
        if (true) {
            int x ;
        }
    }

    // add more methods here if needed

    public int howManyCardLeft() {
        return top ;
    }


    public void showDeck() {
        for (int i = 0 ; i < cards.length ; ++ i) {
            System.out.println(cards[i].toString()) ;
        }
    }

}
