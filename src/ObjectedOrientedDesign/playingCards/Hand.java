package ObjectedOrientedDesign.playingCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {

	public List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    @Override
    public String toString() {
        return cards.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    @Override
    public int compareTo(Hand o) {
        List<Card> selfCards = new ArrayList<>(cards);
        List<Card> otherCards = new ArrayList<>(o.cards);
        
        Collections.sort(selfCards);
        Collections.sort(otherCards);
        
        for (int i = selfCards.size() - 1, j = otherCards.size() - 1; i >= 0 && j >= 0; i--, j--) {
            int res = selfCards.get(i).compareTo(otherCards.get(j));
            
            if (res != 0) {
                return res;
            }
        }
        
        return 0;
    }
}