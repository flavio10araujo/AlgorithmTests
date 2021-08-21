package Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Testando03 {

	public static boolean exist(List<List<String>> board, String word) {
        
        if (board == null || board.size() == 0) {
            return false;
        }
        
        if (word == null || "".equals(word)) {
            return false;
        }
        
        String[] letters = word.split("");
        int boardWidth = board.get(0).size();
        int boardHeight = board.size();
        
        
        return wordSearchStart(board, letters, boardWidth, boardHeight, new HashSet<String>(), 0);
        
    }
    
    public static boolean wordSearchStart(List<List<String>> board, String[] letters, 
                                     int boardWidth, int boardHeight,
                                    Set<String> coordinatesUsed, int charIndex) {
        
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                if (board.get(y).get(x).equals(letters[charIndex])) {
                    coordinatesUsed.add(x + "," + y);
                    
                    if (wordSearch(board, letters, boardWidth, boardHeight, coordinatesUsed, charIndex + 1, x, y)) {
                        return true;
                    }
                    
                    System.out.println("Limpando a matrix coordinatesUsed...");
                    coordinatesUsed = new HashSet<String>();
                }
            }
        }
        
        return false;
    }
    
    public static boolean wordSearch(List<List<String>> board, String[] letters, 
                                     int boardWidth, int boardHeight,
                                    Set<String> coordinatesUsed, int charIndex,
                                         int x, int y) {
    
        if (charIndex >= letters.length) {
            return true;
        }
        
        boolean ok = false;
        
        // (x - 1, y) : left
        if (!ok && x - 1 >= 0) {
        	System.out.println("LEFT : " + (x - 1) + "," + y);
        	if (!coordinatesUsed.contains((x - 1) + "," + y)) {
               if (board.get(y).get(x - 1).equals(letters[charIndex])) {
                   coordinatesUsed.add((x - 1) + "," + y);
                   ok = wordSearch(board, letters, boardWidth, boardHeight, coordinatesUsed, charIndex + 1, (x - 1), y);
               } else {System.out.println("Não é a letra procurada.");}
           } else {System.out.println("Ja foi usado.");}
        }
        
        // (x, y - 1) : up
        if (!ok && y - 1 >= 0) {
        	System.out.println("UP : " + (x) + "," + (y - 1));
        	if (!coordinatesUsed.contains(x + "," + (y - 1))) {
               if (board.get(y - 1).get(x).equals(letters[charIndex])) {
                   coordinatesUsed.add((x) + "," + (y - 1));
                   ok = wordSearch(board, letters, boardWidth, boardHeight, coordinatesUsed, charIndex + 1, x, (y - 1));
               } else {System.out.println("Não é a letra procurada.");}
            } else {System.out.println("Ja foi usado.");}
        }
        
        // (x + 1, y) : right
        if (!ok && x + 1 < boardWidth) {
        	System.out.println("RIGHT : " + (x + 1) + "," + y);
        	if (!coordinatesUsed.contains((x + 1) + "," + y)) {
            	if (board.get(y).get(x + 1).equals(letters[charIndex])) {
                    coordinatesUsed.add((x + 1) + "," + y);
                    ok = wordSearch(board, letters, boardWidth, boardHeight, coordinatesUsed, charIndex + 1, (x + 1), y);
            	} else {System.out.println("Não é a letra procurada.");}
            } else {System.out.println("Ja foi usado.");}
        }
        
        // (x, y + 1) : down
        if (!ok && y + 1 < boardHeight) {
        	System.out.println("DOWN : " + x + "," + (y + 1));
        	if (!coordinatesUsed.contains(x + "," + (y + 1))) {
                if (board.get(y + 1).get(x).equals(letters[charIndex])) {
                    coordinatesUsed.add(x + "," + (y + 1));
                    ok = wordSearch(board, letters, boardWidth, boardHeight, coordinatesUsed, charIndex + 1, x, (y + 1));
                } else {System.out.println("Não é a letra procurada.");}
            } else {System.out.println("Ja foi usado.");}
        }
        
        return ok; 
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    /**
     * 
     * 3
A B C E
S F C S
A D E E
ABCCED
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boardLength = Integer.parseInt(scanner.nextLine());
        List<List<String>> board = new ArrayList<>();
        for (int i = 0; i < boardLength; i++) {
            board.add(splitWords(scanner.nextLine()));
        }
        String word = scanner.nextLine();
        scanner.close();
        boolean res = exist(board, word);
        System.out.println(res);
    }
}