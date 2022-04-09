package ObjectedOrientedDesign.playingCards;

import java.util.Map;
import java.util.stream.Collectors;

public class Joker extends Card {

	private JokerColor color;

    public static final Map<String, JokerColor> COLORS = Map.of(
        "Red", JokerColor.RED,
        "Black", JokerColor.BLACK
    );
    
    // Inverts the above map to convert back to string.
    public static final Map<JokerColor, String> COLOR_NAMES = COLORS.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    public Joker(String color) {
        this.color = COLORS.get(color);
    }

    @Override
    public int getValue() {
        return 14;
    }

    @Override
    public String toString() {
        return String.format("%s Joker", COLOR_NAMES.get(color));
    }
}