package General.TwoPointers.OppositeDirection;

/**
 * Determine whether a string is a palindrome, ignoring non-alphanumeric characters and ignore case. 
 * 
 * Examples:
 * Input: Do geese see God? Output: True
 * Input: Was it a car or a cat I saw? Output: True
 * Input: A brown fox jumping over Output: False
 */
public class ValidPalindrome {
	public static boolean isPalindrome(String s) {
        
		if (s == null || "".equals(s)) {
			return false;
		}
		
		char[] arr = s.toCharArray();
		int L = 0, R = arr.length - 1;
		
		while (L < R) {
			
			while (L < R && !Character.isLetterOrDigit(arr[L])) {
                L++;
            }
			
            while (L < R && !Character.isLetterOrDigit(arr[R])) {
                R--;
            }
			
			if (Character.toLowerCase(arr[L]) != Character.toLowerCase(arr[R])) {
				return false;
			}
			
			L++;
			R--;
		}
		
        return true;
    }

    public static void main(String[] args) {
        String s = "Do geese see God?";
        boolean res = isPalindrome(s);
        System.out.println(res);
    }
}