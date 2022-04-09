package ObjectedOrientedDesign.playingCards;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private List<Card> cards;
    private List<Hand> hands;

    public Game() {
        cards = new ArrayList<>();
        hands = new ArrayList<>();
    }

    public void addCard(String suit, String value) {
        cards.add(new PlayingCard(suit, value));
    }

    public String cardString(int card) {
        return cards.get(card).toString();
    }

    public boolean cardBeats(int cardA, int cardB) {
        return cards.get(cardA).compareTo(cards.get(cardB)) > 0;
    }
    
    public void addJoker(String color) {
        cards.add(new Joker(color));
    }
    
    public void addHand(List<Integer> cardIndices) {
        List<Card> cardObjects = new ArrayList<>();
        
        for (int i : cardIndices) {
            cardObjects.add(cards.get(i));
        }
        
        hands.add(new Hand(cardObjects));
    }

    public String handString(int hand) {
        return hands.get(hand).toString();
    }

    public boolean handBeats(int handA, int handB) {
        return hands.get(handA).compareTo(hands.get(handB)) > 0;
    }
}