package ObjectedOrientedDesign.playingCards;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayingCard extends Card {

	private Suit suit;
    private int value;

    public static final Map<String, Suit> SUITS = Map.of(
        "Spades", Suit.SPADES,
        "Hearts", Suit.HEARTS,
        "Diamonds", Suit.DIAMONDS,
        "Clubs", Suit.CLUBS
    );
    
    // Inverts the above map to convert back to string.
    public static final Map<Suit, String> SUIT_NAMES = SUITS.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    // Map.of is limited to 10 entries, so we initialize a static map instead.
    public static final Map<String, Integer> VALUES = new HashMap<>();
    
    static {
        VALUES.put("A", 1);
        
        for (int i = 2; i <= 10; i++) {
            VALUES.put(String.valueOf(i), i);
        }
        
        VALUES.put("J", 11);
        VALUES.put("Q", 12);
        VALUES.put("K", 13);
    }
    
    // Inverts the above map to convert back to string.
    public static final Map<Integer, String> VALUE_NAMES = VALUES.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    public PlayingCard(String suit, String value) {
        this.suit = SUITS.get(suit);
        this.value = VALUES.get(value);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", VALUE_NAMES.get(value), SUIT_NAMES.get(suit));
    }
}