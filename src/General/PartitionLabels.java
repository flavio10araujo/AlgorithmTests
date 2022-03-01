package General;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/
 * 
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 * Return a list of integers representing the size of these parts.
 * 
 * Example 1:
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 * 
 * Example 2:
 * Input: s = "eccbbbbdec"
 * Output: [10]
 * 
 * Constraints:
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 */
public class PartitionLabels {

	public static void main(String[] args) {
		//String s = "ababcbacadefegdehijhklij";
		
		String s = "eaaaabaaec";
		
		System.out.println(partitionLabels(s));
	}
	
	public static List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        
        int[] chars = new int[123];
        
        for (int i = s.length() - 1; i >= 0; i--) {
            if (chars[s.charAt(i)] > 0) {
                continue;
            }
            
            chars[s.charAt(i)] = i;
        }
        
        int n = 0;
        
        while(n < s.length()) {
            
        	int minIndex = n;
            int maxIndex = chars[s.charAt(n)];
            int maxIndexN = 0;
            
            while(maxIndexN <= maxIndex && n < s.length() -  1) {
                maxIndexN = chars[s.charAt(n)];
                
                if (n < maxIndex && maxIndexN > maxIndex) {
                	maxIndex = maxIndexN;
                }
                
                n++;
            }
            
            ans.add(maxIndex - minIndex + 1);
            
            n = maxIndex + 1;
        }
        
        return ans;
    }
}