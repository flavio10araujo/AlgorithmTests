package Recursion;

public class LogarithmBase2 {

	public static void main(String[] args) {
		int n = 32;
		System.out.println("Log of " + n + " in base 2 is " + LogarithmBase2.calcLogarithmBase2(n) + ".");
	}

	public static int calcLogarithmBase2(int n) {
		if (n == 1) {
	        return 0;
		}
	    else {
	        return 1 + calcLogarithmBase2(n / 2);
	    }
	}
}