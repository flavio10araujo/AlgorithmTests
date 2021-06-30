package General.BinarySearch;

/**
 * Given an integer, find its square root without using built-in square root function. 
 * Only return the integer part (truncate the decimals).
 * Input: 16; Output: 4.
 * Input: 8; Output: 2.
 * Explanation: square root of 8 is 2.83..., return integer part 2.
 * 
 * Binary Search.
 */
public class SquareRoot {
	
	public static int squareRoot(int n) {
        int L = 0;
        int R = n;
        int M = -1;
        int square = -1;
		
		while(L <= R) {
			M = (L + R) / 2;
			
			System.out.println("L="+L+" M="+M+" R="+R);
			
			if (M * M == n) {
				square = M;
				R = M - 1;
				L = M + 1;
			} else if (M * M > n) {
				R = M - 1;
			} else {
				L = M + 1;
				square = M;
			}
		}
		
		return square;
    }
	
	public static void main(String[] args) {
        int n = 10;
        int res = squareRoot(n);
        System.out.println(res);
    }
}