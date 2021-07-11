package General.Backtracking.CombinatorialSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Given a phone number that contains 2-9, find all possible letter combinations the phone number could translate to.
 * 
 * Input: "56"
 * Output: ["jm","jn","jo","km","kn","ko","lm","ln","lo"]
 */
public class LetterCombinationsOfPhoneNumber {
	
	private static final Map<Character, char[]> KEYBOARD = Map.of(
        '2', "abc".toCharArray(),
        '3', "def".toCharArray(),
        '4', "ghi".toCharArray(),
        '5', "jkl".toCharArray(),
        '6', "mno".toCharArray(),
        '7', "pqrs".toCharArray(),
        '8', "tuv".toCharArray(),
        '9', "wxyz".toCharArray()
    );
	
	private static void dfs(StringBuilder path, List<String> res, char[] digits) {
        if (path.length() == digits.length) {
            res.add(path.toString());
            return;
        }
        
        char next_digit = digits[path.length()];
        for (char letter : KEYBOARD.get(next_digit)) {
            path.append(letter);
            dfs(path, res, digits);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static List<String> letterCombinationsOfPhoneNumber(String digits) {
        List<String> res = new ArrayList<>();
        dfs(new StringBuilder(), res, digits.toCharArray());
        return res;
    }
	
	public static void main(String[] args) {
        String digits = "568";
        List<String> res = letterCombinationsOfPhoneNumber(digits);
        System.out.println(String.join(" ", res));
    }
}