package ObjectedOrientedDesign.playingCards;

public abstract class Card implements Comparable<Card> {

	public abstract int getValue();

    @Override
    public int compareTo(Card o) {
        return Integer.compare(getValue(), o.getValue());
    }
}