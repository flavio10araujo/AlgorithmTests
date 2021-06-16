package General;

import java.util.HashMap;
import java.util.Hashtable;

/*
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output Fizz instead of the number and for the multiples of five output Buzz. 
For numbers which are multiples of both three and five output FizzBuzz.
 */
public class FizzBuzz {

	public static void main(String[] args) {
		int n = 15;
		//FizzBuzz.doFizzBuzzNaiveSolution(n);
		//FizzBuzz.doFizzBuzzConcatSolution(n);
		FizzBuzz.dofizzBuzzHashMapSolution(n);
	}

	/**
	 * Time complexity linear: O(n).
	 * @param n
	 */
	@SuppressWarnings("unused")
	private static void doFizzBuzzNaiveSolution(int n) {
		boolean fizz = false;
		boolean buzz = false;

		for (int i = 1; i <= n; i++) {

			fizz = false;
			buzz = false;

			if (i % 3 == 0) {
				fizz = true;
			}
			if (i % 5 == 0) {
				buzz = true;
			}

			if (fizz && buzz) {
				System.out.println("FizzBuzz");
			} else if (fizz) {
				System.out.println("Fizz");
			} else if (buzz) {
				System.out.println("Buzz");
			} else {
				System.out.println(i);
			}
		}
	}

	/**
	 * This solution is better than NaiveSolution because we have less ifs 
	 * and is simpler to change if in the future we want to add new conditions, for example, "jack" when the n is divisible by 7.
	 * Time complexity linear: O(n).
	 * @param n
	 */
	@SuppressWarnings("unused")
	private static void doFizzBuzzConcatSolution(int n) {
		String print = "";

		for (int i = 1; i <= n; i++) {

			if (i % 3 == 0) {
				print += "Fizz";
			}
			if (i % 5 == 0) {
				print += "Buzz";
			}

			System.out.println("".equals(print) ? i : print);
			print = "";
		}
	}

	/**
	 * This solution is an improvement for the ConcatSolution. 
	 * With the Hash Map, we can add future conditions without changing the code. For example, we can add 7=Jack.
	 * Time complexity linear: O(n).
	 */
	private static void dofizzBuzzHashMapSolution(int n) {

		// Hash map to store all fizzbuzz mappings.
		HashMap<Integer, String> fizzBizzDict = new HashMap<Integer, String>() {
			{
				put(3, "Fizz");
				put(5, "Buzz");
				put(7, "Jack");
			}
		};
		
		/*
		Hashtable<Integer, String> fizzBizzDict = new Hashtable<Integer, String>() {
			{
				put(3, "Fizz");
				put(5, "Buzz");
				put(7, "Jack");
			}
		};
		*/
		
		String print = "";

		for (int i = 1; i <= n; i++) {

			for (Integer key : fizzBizzDict.keySet()) {
				// If the num is divisible by key, then add the corresponding string mapping to current print.
				if (i % key == 0) {
					print += fizzBizzDict.get(key);
				}
			}

			if ("".equals(print)) {
				// Not divisible by any value in the HashMap, show the number.
				System.out.println(i);
			} else {
				System.out.println(print);
			}

			print = "";
		}
	}
}