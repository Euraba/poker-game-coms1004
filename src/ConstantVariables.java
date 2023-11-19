import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public interface ConstantVariables {
    public final ArrayList<String> suitsName =
            new ArrayList<>(Arrays.asList("", "diamonds",
                    "spades", "hearts", "clubs"));
    public final ArrayList<String> ranks =
            new ArrayList<>(Arrays.asList("Jack", "Queen", "King")) ;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";

    public final HashMap<Character, Integer> suits = new HashMap<>() {{
        put('c', 4);
        put('h', 3);
        put('s', 2);
        put('d', 1);
    }} ;

    public final HashMap<Integer, String> suitsChar = new HashMap<>() {{
        put(1, ANSI_RED + " ◆ " + ANSI_RESET + "diamonds") ;
        put(2, ANSI_BLACK + " ♠ " + ANSI_RESET + "spades");
        put(3, ANSI_RED + " ♥ " + ANSI_RESET + "hearts");
        put(4, ANSI_BLACK + " ♣ " + ANSI_RESET + "clubs") ;
    }};

    public final HashMap<String, Integer> multipliers = new HashMap<>() {{
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
}
