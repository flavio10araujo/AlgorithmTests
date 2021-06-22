package Recursion;

public class Factorial {

	public static void main(String[] args) {
		int n = 5;
		Factorial.solution01(n);
	}

	public static void solution01(int n) {
		System.out.println(solution01P2(n));
	}
	
	public static int solution01P2(int n) {
		
		if (n <= 1) {
			return 1;
		}
		
		return solution01P2(n - 1) * n;
	}
}