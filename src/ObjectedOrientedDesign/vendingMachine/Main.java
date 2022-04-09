package ObjectedOrientedDesign.vendingMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * For this question, we ask you to design a vending machine. 
 * We divide this question in to three parts, so you can complete them in order.
 * 
 * 
 * Part One
 * 
 * For the first part, you must design a Machine class representing the vending machine. 
 * Your system must support these following commands:
 * - new_product <name> <price>: 
 *   Creates a new product object with name which is a string, and price which is a non-negative integer.
 * - print_products: 
 *   Prints all products, each product in a line in format <name> <price>, sorted by price from lowest to highest.
 * - insert_coin <value>: 
 *   Adds money to internal state.
 * - purchase <name>: 
 *   Checks if the user inserted enough money and return a boolean.
 * - checkout: 
 *   Prints the money left by the previous user, and clears internal state for the next user.
 *   
 * You may implement these however you like. 
 * However, preferably this should be easily expandable to accommodate new requirements.
 * 
 * Example:
 * 
 * Input:
 * 
 * instructions = [
 * 		["new_product", "apple", "4"],
 * 		["insert_coin", "5"],
 * 		["purchase", "apple"],
 * 		["purchase", "apple"],
 * 		["checkout"],
 * ]
 * 
 * Output: [
 * 		"true",
 * 		"false",
 * 		"1"
 * ]
 * 
 * Solution
 * 
 * There are numerous approaches we can take to design this problem. 
 * A simple solution use two classes: Machine and Product. 
 * A Machine object contains a map products which maps the name of a product to the product object, and an int balance which represents the money inserted by the current user.
 * 
 * Part Two
 * 
 * Now we need additional functionality in the form of a limited supply of products. 
 * After adding new products to the vending machine, we also need to restock them. 
 * 
 * Thus we need to support the following instruction:
 * - restock <name> <quantity>: 
 *   Now purchase also needs to check if the vending machine has enough supply. 
 *   And print_products should print in format <name> <price> <quantity>.
 *   
 * Solution
 * 
 * We can add a new field quantity to the Product class, restock simply adds the product's quantity. 
 * We also need to update the string representation of the Product class and modify the logic for purchase.
 * 
 * Part Three
 * 
 * In real world we don't have coins of arbitrary value. 
 * Let's implement real coins. 
 * We have 4 different kind of coins: penny, nickel, dime, and quarter, with values 1, 5, 10, 25 respectively.
 * 
 * - insert_coin <name>: 
 * 	 Now instead of using the value directly, the vending machine use the name of a coin. 
 *   Print accepted if the name is valid, otherwise print rejected.
 * - checkout: 
 *   Instead of printing the value directly, the vending machine prints a list of coins in format <quantity> <name>, ordered from the highest value to the lowest.
 *   
 * Example:
 * 
 * Input:
 * 
 * instructions = [
 * 		["new_product", "apple", "4"],
 * 		["restock", "apple", "5"],
 * 		["insert_coin", "dime"],
 * 		["purchase", "apple"],
 * 		["purchase", "apple"],
 * 		["checkout"],
 * ]
 * 
 * Output: [
 * 		"accepted",
 * 		"true",
 * 		"false",
 * 		"1 nickel"
 * 		"1 penny"
 * ]
 * 
 * Solution
 * 
 * Enum class is a simple way to implement real coins. The Coin class contains only one field value, and we need to use the value to sort coins for checkout.
 */
public class Main {

	/*
    5
    new_product apple 4
	 insert_coin 5
	 purchase apple
	 purchase apple
	 checkout
	 */
	// Testing Part One.
	
	// Testing Part Two.
	
	/*
	 6
	 new_product apple 4
    restock apple 5
    insert_coin dime
    purchase apple
    purchase apple
    checkout
	 */
	// Testing Part Three.
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int instructionsLength = Integer.parseInt(scanner.nextLine());
		List<List<String>> instructions = new ArrayList<>();

		for (int i = 0; i < instructionsLength; i++) {
			instructions.add(splitWords(scanner.nextLine()));
		}

		scanner.close();
		List<String> res = vendingMachine(instructions);

		for (String line : res) {
			System.out.println(line);
		}
	}
	
	public static List<String> vendingMachine(List<List<String>> instructions) {
		List<String> out = new ArrayList<String>();
		VendingMachine machine = new VendingMachine();
		
		for (List<String> instruction : instructions) {
			if (instruction.get(0).equals("new_product")) {
                machine.newProduct(instruction.get(1), Integer.parseInt(instruction.get(2)));
            }
			else if (instruction.get(0).equals("print_products")) {
                out.addAll(machine.printProducts());
            }
			else if (instruction.get(0).equals("restock")) {
                machine.restock(instruction.get(1), Integer.parseInt(instruction.get(2)));
            }
			else if (instruction.get(0).equals("insert_coin")) {
                out.add(machine.insertCoin(instruction.get(1)) ? "accepted" : "rejected");
            }
			else if (instruction.get(0).equals("purchase")) {
                out.add(machine.purchase(instruction.get(1)) ? "true" : "false");
            }
			else if (instruction.get(0).equals("checkout")) {
                out.addAll(machine.checkout());
            }
		}
		
		return out;
	}

	public static List<String> splitWords(String s) {
		return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
	}
}