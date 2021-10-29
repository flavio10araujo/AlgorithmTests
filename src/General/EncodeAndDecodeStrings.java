package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.lintcode.com/problem/659/
 * 
 * Design an algorithm to encode a list of strings to a string. 
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 * Please implement encode and decode
 * 
 * Example1
 * Input: ["lint","code","love","you"]
 * Output: ["lint","code","love","you"]
 * Explanation: One possible encode method is: "lint:;code:;love:;you"
 * 
 * Example2
 * Input: ["we", "say", ":", "yes"]
 * Output: ["we", "say", ":", "yes"]
 * Explanation:
 * One possible encode method is: "we:;say:;:::;yes"
 */
public class EncodeAndDecodeStrings {

	static final char DELIMITER = '#';
	
	public static void main(String[] args) {
		List<String> mylist = new ArrayList<>();
		mylist.add("we");
		mylist.add("s4#ay");
		mylist.add(":");
		mylist.add("yes");
		
		String resStr = encode(mylist);
		
		System.out.println(resStr);
		
		List<String> resList = decode(resStr);
		
		System.out.println(resList);
	}
	
	/*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        
        for (String s : strs) {
        	sb.append(s.length());
        	sb.append(DELIMITER);
        	sb.append(s);
        }
        
        return sb.toString();
    }

    /*
     * @param str: A string
     * @return: decodes a single string to a list of strings
     */
    public static List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        char[] arr =  str.toCharArray();
        
        for (int i = 0; i < arr.length; i++) {
        	// Find the number of characters.
        	StringBuilder sb = new StringBuilder();
        	
        	while (arr[i] != DELIMITER) {
        		sb.append(arr[i++]);
        	}
        	
        	i++;
        	
        	// Iteration the enter string to add to the res list.
        	int numOfChars = Integer.valueOf(sb.toString());
        	int end = i + numOfChars;
        	sb = new StringBuilder();
        	
        	while (i < end) {
        		sb.append(arr[i++]);
        	}
        	
        	i--;
        	res.add(sb.toString());
        }
        
        return res;
    }
}