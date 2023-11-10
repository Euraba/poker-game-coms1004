import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        ArrayList<Card> list = new ArrayList<>() ;
        list.add(new Card(3, 5));
        list.add(new Card(2, 9));
        list.add(new Card(1, 12));

        System.out.println(list.indexOf(new Card(2, 9)));
    }
}