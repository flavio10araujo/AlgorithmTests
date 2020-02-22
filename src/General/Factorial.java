package General;

public class Factorial {

	public static void main(String[] args) {
		
		Factorial f = new Factorial();
		int n = 7;
		
		System.out.println(f.calculateFactorialSolution01(n));
		System.out.println(f.calculateFactorialSolution02(n));
	}

	/**
	 * Ex.: n = 4, 4 * 3 * 2 * 1 = 24.
	 * 
	 * @param n
	 * @return
	 */
	private static int calculateFactorialSolution01(int n) {
		int sum = 1;
		
		while(n > 0) {
			sum = sum * n;
			n--;
		}
		
		return sum;
	}
	
	/**
	 * Usando recursividade.
	 * @param n
	 * @return
	 */
	private static int calculateFactorialSolution02(int n) {
		if (n <= 1) {
			return 1;
		}
		
		return n * calculateFactorialSolution02(n-1);
	}
}