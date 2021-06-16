package General;

/**
 * 
 * Two different solutions to find the factorial of a number.
 * For example: n = 7
 * Answer: 7 * 6 * 5 * 4 * 3 * 2 * 1 = 5040.
 *
 */
public class Factorial {

	public static void main(String[] args) {
		int n = 7;
		//System.out.println(Factorial.calculateFactorialSolution01(n));
		System.out.println(Factorial.calculateFactorialSolution02(n));
	}

	/**
	 * Time complexity linear: O(n).
	 * @param n
	 * @return
	 */
	@SuppressWarnings("unused")
	private static int calculateFactorialSolution01(int n) {
		int sum = 1;
		
		while(n > 0) {
			sum = sum * n;
			n--;
		}
		
		return sum;
	}
	
	/**
	 * With recursion.
	 * Time complexity linear: O(n).
	 * @param n
	 * @return
	 */
	private static int calculateFactorialSolution02(int n) {
		if (n <= 1) {
			return 1;
		}
		
		return n * calculateFactorialSolution02(n - 1);
	}
}