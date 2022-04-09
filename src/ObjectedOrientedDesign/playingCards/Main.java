package ObjectedOrientedDesign.playingCards;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * For this question, we ask you to design a card game using the traditional 52-card deck. 
 * We divide this question in to three parts, so you can complete them in order.
 * 
 * Part One:
 * 
 * For the first part, you must design a Game class representing the game, and these following functions associated with the class.
 * 
 * - add_card(suit, value): 
 * 		Creates a new card object with a suit from one of the following strings: 
 * 		Hearts, Spades, Clubs, Diamonds, and a value from one of the following strings: A, 2~10, J, Q, K. 
 * 		This card is represented by i, where i is an integer indicating how many cards have been created before.
 * - card_string(card): 
 * 		Returns the string representation of the card represented by i. 
 * 		It follows the format <value> of <suit>. 
 * 		For example, a card created by add_card("Spades", "3") should have a string representation of 3 of Spades.
 * - card_beats(card_a, card_b): 
 * 		Check if the card represented by card_a beats the one represented by card_b. 
 * 		A card beats another card if and only if it has a greater value. 
 * 		The value of the cards are ordered from A to K.
 * 
 * Part Two:
 * 
 * For this part, we ask you to implement the Jokers into the system.
 * 
 * - add_joker(color): Creates a Joker card of with color of either Red or Black.
 * 		- Joker beats everything else except other jokers. 
 * 		  This card is represented by i, where i is an integer indicating how many cards have been created before, 
 * 		  including both normal cards and jokers.
 * 		- A joker's string representation is Red Joker or Black Joker, depending on the color.
 * 
 * Part Three:
 * 
 * This game also involve a concept of a Hand and comparing the size of the two hands. 
 * For this part, add these following functions to the Game class:
 * 
 * - add_hand(card_indices): Create a new Hand with cards represented by the list of integer representation of cards card_indices. 
 * 		The hand can be represented by i, where i is the number of hands added before.
 * - hand_string(hand): Return the string representation of the hand represented by hand. 
 * 		It is a list of string representation of cards by their insertion order, separated by ", ". 
 * 		For example, if hand has a 9 of Clubs, K of Hearts, and a Black Joker, the string representation is "9 of Clubs, K of Hearts, Black Joker".
 * - beats_hand(hand_a, hand_b): Check if the hand represented by hand_a beats the hand represented by hand_b according to the following rules:
 * 		- Starting from the largest card in each hand, compare them. 
 * 		  If a card beats another, that hand beats the other hand. 
 * 		  Otherwise, compare the next largest card.
 * 		- Repeat this process until one hand beats the other, or one hand runs out of cards. 
 * 		  If a hand runs out of cards, neither hand beat each other.
 * 
 * You may implement these however you like. However, preferably this should be easily expandable to accommodate new requirements.
 * 
 * Solution Part One:
 * 
 * There are numerous approach we can take to design this problem. 
 * The sample solution will provide an object-oriented approach, since it allows us to easily add new types of cards to accommodate new requirements.
 * Different languages have different tools, but the most basic concept in object oriented programming is inheritance, which is a class deriving from a superclass and inheriting its methods. 
 * In this situation, a playing card from the 52 is a card. 
 * The reason for this design is that we can easily add other types of cards if we want.
 * 
 * Solution Part Two:
 * 
 * We add a Joker class that inherits the base Card. 
 * For the purpose of this question, its value is 14, which is greater than other cards. 
 * We do not need to write extra logic for comparing Jokers with other cards, since that logic is already there under Card.
 * 
 * Solution Part Three:
 * 
 * For this part, we implement the Hand class by having it contain a list of cards. 
 * When we compare two hands, because we defined a comparison function between two cards, we can sort them using the default sorting algorithm.
 */
public class Main {

	/*
     Spades 3
	 Hearts K
	 Output:
	 3 of Spades
	 K of Hearts
	 false
    */
   /*
     Diamonds 8
	 Clubs 6
	 Output:
	 8 of Diamonds
	 6 of Clubs
	 true
    */
   /*
     Clubs J
	 Spades A
	 Output:
	 J of Clubs
	 A of Spades
	 true
    */
    /*
     Hearts 10
	 Diamonds 10
	 Output:
	 10 of Hearts
	 10 of Diamonds
	 false
     */
    // Testing Part One.
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        String[] segs = scanner.nextLine().split(" ");
        game.addCard(segs[0], segs[1]);
        System.out.println(game.cardString(0));
        segs = scanner.nextLine().split(" ");
        game.addCard(segs[0], segs[1]);
        System.out.println(game.cardString(1));
        System.out.println(game.cardBeats(0, 1));
        scanner.close();
    }*/
    
    /*
     Joker Red
	 Spades K
	 Output:
	 Red Joker
	 K of Spades
	 true
    */
    /*
     Joker Black
	 Joker Red
	 Output:
	 Black Joker
	 Red Joker
	 false
     */
    // Testing Part Two.
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        String[] segs = scanner.nextLine().split(" ");
        if (segs[0].equals("Joker"))
            game.addJoker(segs[1]);
        else
            game.addCard(segs[0], segs[1]);
        System.out.println(game.cardString(0));
        segs = scanner.nextLine().split(" ");
        if (segs[0].equals("Joker"))
            game.addJoker(segs[1]);
        else
            game.addCard(segs[0], segs[1]);
        System.out.println(game.cardString(1));
        System.out.println(game.cardBeats(0, 1));
        scanner.close();
    }*/
    
    /*
     3
	 Spades K
	 Clubs 6
	 Diamonds Q
	 3
	 Hearts K
	 Diamonds 9
	 Spades 7
	 Output:
	 K of Spades, 6 of Clubs, Q of Diamonds
	 K of Hearts, 9 of Diamonds, 7 of Spades
	 true
     */
    /*
     5
	 Clubs 9
	 Joker Red
	 Diamonds 7
	 Spades 3
	 Diamonds A
	 3
	 Hearts K
	 Diamonds K
	 Spades J
	 Output:
	 9 of Clubs, Red Joker, 7 of Diamonds, 3 of Spades, A of Diamonds
	 K of Hearts, K of Diamonds, J of Spades
	 true
     */
    /*
     2
	 Spades J
	 Diamonds 7
	 3
	 Hearts A
	 Clubs J
	 Spades 7
	 Output:
	 J of Spades, 7 of Diamonds
	 A of Hearts, J of Clubs, 7 of Spades
	 false
     */
    // Testing Part Three.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        ArrayList<Integer> handAList = new ArrayList<>();
        int listALength = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < listALength; i++) {
            String[] segs = scanner.nextLine().split(" ");
            
            if (segs[0].equals("Joker"))
                game.addJoker(segs[1]);
            else
                game.addCard(segs[0], segs[1]);
            
            handAList.add(i);
        }
        
        game.addHand(handAList);
        
        System.out.println(game.handString(0));
        
        ArrayList<Integer> handBList = new ArrayList<>();
        int listBLength = Integer.parseInt(scanner.nextLine());
        
        for (int i = listALength; i < listALength + listBLength; i++) {
            String[] segs = scanner.nextLine().split(" ");
            
            if (segs[0].equals("Joker"))
                game.addJoker(segs[1]);
            else
                game.addCard(segs[0], segs[1]);
            
            handBList.add(i);
        }
        
        game.addHand(handBList);
        
        System.out.println(game.handString(1));
        
        System.out.println(game.handBeats(0, 1));
        
        scanner.close();
    }
}