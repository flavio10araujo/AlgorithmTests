package Recursion;

public class PrintBinaryNumber {

	public static void main(String[] args) {
		PrintBinaryNumber.printBinary(1);
		System.out.println("");
		
		PrintBinaryNumber.printBinary(2);
		System.out.println("");
		
		PrintBinaryNumber.printBinary(3);
		System.out.println("");
		
		PrintBinaryNumber.printBinary(4);
		System.out.println("");
		
		PrintBinaryNumber.printBinary(5);
		System.out.println("");
		
		PrintBinaryNumber.printBinary(6);
		System.out.println("");
		
		PrintBinaryNumber.printBinary(7);
		System.out.println("");
		
		PrintBinaryNumber.printBinary(8);
		System.out.println("");
	}

	/**
	 * Print the binary number of n.
	 * @param n must be >= 1.
	 */
	public static void printBinary(int n) {
		if (n == 0) {
			return;
		}

		printBinary(n/2);
		
		System.out.print(n % 2);
	}
}