package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string made up of integers 0 to 9, 
 * count the number of ways to split the string into prime numbers in the range of 2 to 1000 inclusive, 
 * using up all the characters in the string.
 * 
 * Each prime number, pn, must not contain leading 0s, and 2 <= pn <= 1000.
 * 
 * Constraints
 * The input string does not contain any leading 0s.
 * 
 * Example 1:
 * Input: "31173"
 * Output: 6
 * Explanation:
 * The string "31173" can be split into prime numbers in 6 ways:
 * [3, 11, 7, 3]; [3, 11, 73]; [31, 17, 3]; [31, 173]; [311, 7, 3]; [311, 73]
 */
public class SplitStringIntoUniquePrimes {

	static boolean[] primes = new boolean[1000001];
	static int maxn = 1000000;
	
	public static void main(String[] args) {
		String str = "31173";
	    sieve();
	    System.out.println(primeSplit(str));
	}

	// Sieve of Eratosthenes
	public static void sieve() {
	    Arrays.fill(primes, true);
	    
	    primes[0] = false;
	    primes[1] = false;
	     
	    for (int i = 2; i * i <= maxn; i++) {
	        if (primes[i]) {
	            for (int j = i * i; j <= maxn; j += i) {
	                primes[j] = false;
	            }
	        }
	    }
	}

	// Function Convert integer to binary string.
	public static String toBinary(int n) {
	    String r = "";
	     
	    while(n != 0) {
	        r = (n % 2 == 0 ? "0" : "1") + r;
	        n /= 2;
	    }
	    
	    return (r == "") ? "0" : r;
	}
	 
	// Function print all the all the ways to split the given string into Primes.
	public static int primeSplit(String str) {
	    
		String temp;
	     
	    // To store all possible strings.
	    List<String> ans = new ArrayList<>();
	    int bt = 1 << (str.length() - 1);
	    int n = str.length();

	    // Exponential complexity n * (2 ^ (n - 1)) for bit.
	    for (int i = 0; i < bt; i++) {
	        
	    	temp = toBinary(i) + "0";
	        int j = 0, x = n - temp.length(), y;
	         
	        while(j < x) {
	            temp = "0" + temp;
	            j++;
	        }
	        
	        j = 0;
	        x = 0;
	        y = -1;
	         
	        String sp = "", tp = "";
	        boolean flag = false;
	         
	        while(j < n) {
	            sp += str.charAt(j);
	             
	            if (temp.charAt(j) == '1') {
	                tp += sp + ',';
	                y = Integer.parseInt(sp);
	                 
	                // Pruning step
	                if (!primes[y]) {
	                    flag = true;
	                    break;
	                }
	                
	                sp = "";
	            }
	            
	            j++;
	        }
	        
	        tp += sp;
	         
	        if (sp != "") {
	            y = Integer.parseInt(sp);
	             
	            if (!primes[y])
	                flag = true;
	        }
	        
	        if (!flag)
	        	ans.add(tp);
	    }

	    if (ans.size() == 0) {
	        System.out.println(-1);
	    }

	    for(String i : ans) {
	        System.out.println(i);
	    }
	    
	    return ans.size();
	}
}