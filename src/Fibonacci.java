// Fibonacci Series using Recursion.
// Ex.: 0 1 1 2 3 5 8 13 = the next will be 21.
class Fibonacci {
	static int fib(int n) {
		if (n <= 1) {
			return n;
		}
		
		return fib(n - 1) + fib(n - 2);
	}

	public static void main(String args[]) {
		int n = 8;
		System.out.println(fib(n));
	}
}