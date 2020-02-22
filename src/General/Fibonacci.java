package General;
// Fibonacci Series using Recursion.
// Ex.: 0 1 1 2 3 5 8 13 = the next will be 21.
class Fibonacci {
	public static void main(String args[]) {
		int n = 8;
		System.out.println(fibSolution01(n));
		//System.out.println(fibSolution02(n));
	}
	
	/**
	 * Solução usando recursividade.
	 */
	private static int fibSolution01(int n) {
		
		System.out.println("n = " + n);
		
		if (n <= 1) {
			System.out.println("entrou no if");
			return n;
		}
		
		return fibSolution01(n - 1) + fibSolution01(n - 2);
	}
	
	/**
	 * Solução usando Dynamic Programming.
	 * Essa solução é melhor que a 01 porque evita de refazer um cálculo que já foi feito.
	 */
	private static int fibSolution02(int n) {
	    int memoize[] = new int[n+1];
	    return CalculateFibonacciRecursive(memoize, n);
	  }
	
	private static int CalculateFibonacciRecursive(int[] memoize, int n) {
		
		System.out.println("n = " + n);
		
		if (n < 2) {
			System.out.println("entrou no if");
			return n;
		}

	    // if we have already solved this subproblem, simply return the result from the cache
	    if(memoize[n] != 0)
	      return memoize[n];

	    memoize[n] = CalculateFibonacciRecursive(memoize, n-1) + CalculateFibonacciRecursive(memoize, n-2);
	    return memoize[n];
	  }
}