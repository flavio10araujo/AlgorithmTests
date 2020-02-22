package General;
// Fibonacci Series using Recursion.
// Ex.: 0 1 1 2 3 5 8 13 = the next will be 21.
class Fibonacci {
	static int fib(int n) {
		
		System.out.println("n = " + n);
		
		if (n <= 1) {
			System.out.println("entrou no if");
			return n;
		}
		
		return fib(n - 1) + fib(n - 2);
	}

	public static void main(String args[]) {
		int n = 8;
		System.out.println(fib(n));
	}
}