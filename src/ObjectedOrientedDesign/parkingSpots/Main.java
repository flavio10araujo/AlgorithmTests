package ObjectedOrientedDesign.parkingSpots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * In this question, we ask you to design a system for a parking lot to keep track of the cars parked in the parking slot. 
 * You must design the system in a way that is easily expandable.
 * This question will come in parts, and you need to answer the previous part before moving on. 
 * 
 * Part One:
 * 
 * A car can be represented as <size> <color> <brand>. 
 * For example, "Small Silver BMW", "Large Black Nissan" are all valid car representations. 
 * All the allowed sizes are "Small", "Medium", and "Large".
 * In this parking lot you are given the number of parking slots available at the start, labelled from 0 to n - 1. 
 * Your system must support these following commands:
 * 
 * - "park [spot] [car]": 
 * 		Attempt to park the car into the given spot. 
 * 		If the given spot is unavailable (because a car cannot park there, or there is already a car), 
 * 		the park will try to park at the next spot in order until it finds an available slots, 
 * 		or there are no more slots left (in which case the car leaves the parking lot).
 * - "remove [spot]": 
 * 		Remove the car parked at that spot. 
 * 		Do nothing if there are no car there.
 * - "print [spot]": 
 * 		Print the representation of the car at that spot, or "Empty" if that spot is empty.
 * - "print_free_spots": 
 * 		Print the number of slots free in the parking lot.
 * 
 * Parameters:
 * 		n: The number of slots in the parking lot.
 * 		instructions: A string matrix representing the instructions.
 * 
 * Result: A list of strings representing the printed output.
 * 
 * Example 1
 * Input:
 * n = 5
 * instructions = [
 * 		["park", "1", "Small", "Silver", "BMW"],
 * 		["park", "1", "Large", "Black", "Nissan"],
 * 		["print", "1"],
 * 		["print", "2"],
 * 		["print", "3"],
 * ]
 * Output: [
 * 		"Small Silver BMW",
 * 		"Large Black Nissan",
 * 		"Empty",
 * ]
 * 
 * 
 * 
 * Part Two
 * 
 * In order to save space, there are new restrictions in place in terms of what cars are allowed to park at which spots. 
 * There is now a size restriction for each parking spots, from "Small", "Medium", or "Large". 
 * Only cars with less or equal size than the spot are allowed to park there. 
 * Remember if a car cannot park at a spot it will try to find the next available spot down the parking lot, if one exists.
 * 
 * Parameters
 * spots: A list of strings representing the size of the parking spots from spot 0 to n - 1.
 * instructions: A string matrix representing the instructions.
 * 
 * Result
 * A list of strings representing the printed output.
 * 
 * 
 * 
 * Part Three
 * 
 * The system gets popular, and it gets widely used in different types of parking lots. 
 * Unfortunately, this system only applies to one type of parking lot: parking lots with explicit parking spots drawn. 
 * There are parking lots where the cars can park wherever they want, as long as as the cars park at an unoccupied space.
 * This type of parking spot has a length of n. 
 * We may also safely assume that small cars occupies a length of a, 
 * medium cars occupies a length of b, 
 * and large cars a length of c.
 * 
 * To simplify code, the two parking lots uses the same system. 
 * That is, both parking lots must support the instructions from the previous parts. 
 * "Regular" parking lots, i.e. the parking lots from the previous questions, have instructions defined previously. 
 * For "Unbounded" parking lots, i.e. the one defined in this part, does these instructions differently:
 * 
 * - "park [spot] [car]" 
 * 	  instead attempts to park the car at the interval [i, i + k], where k is the length the car occupies, 
 *    and i is the smallest integer greater than or equal to spot such that the interval above is not occupied at all. 
 *    If this interval falls out of the parking lot (which occupies the interval [0, n]), the car leaves the parking lot.
 * - "remove [spot]" 
 *    instead removes the car that occupies the interval [spot, spot + 1], if able.
 * - "print [spot]" 
 *    instead prints the car that occupies the interval [spot, spot + 1], or "Empty" if no car occupies there.
 * - "print_free_spots" 
 *    instead print the maximum number of large cars that can park at the parking lot.
 * 
 * To differentiate the two types of parking lot, see the function parameters.
 * 
 * Parameters
 * 	  lot_type: 
 * 			The type of parking lot this is, whether it's "Regular" or "Unbounded".
 *    params: 
 *    		A list of strings. 
 *    		The content is different depending on the type of parking lot this is.
 *    		"Regular" parking lot: 
 *    			A list of strings representing the size of the parking spots from spot 0 to n - 1.
 *    		"Unbounded" parking lot: 
 *    			[n, a, b, c], integers defining the size of the parking lot, small car, medium car, and large car, respectively.
 *    		instructions: A string matrix representing the instructions.
 * 
 * Result
 * A list of strings representing the printed output.
 */
public class Main {

	/*
	 6
	 5
 	 park 1 Small Silver BMW
     park 1 Large Black Nissan
 	 print 1
 	 print 2
 	 print 3
	 */
	// Testing Part One
	
	/*
	 6
	 Small Medium Medium Medium Large
	 park 1 Small Silver BMW
     park 1 Large Black Nissan
	 print 1
	 print 2
	 print 4
	 */
	// Testing Part Two
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int instructionsLength = Integer.parseInt(scanner.nextLine());
		List<List<String>> instructions = new ArrayList<>();
		
		for (int i = 0; i < instructionsLength; i++) {
			instructions.add(splitWords(scanner.nextLine()));
		}
		
		scanner.close();
		List<String> res = simulateParkingSpots(instructions);
		
		for (String line : res) {
			System.out.println(line);
		}
	}

	public static List<String> simulateParkingSpots(List<List<String>> instructions) {
		
		List<String> parkingSpotsSize = new ArrayList<>();
		for (String instruction : instructions.get(0)) {
			parkingSpotsSize.add(instruction);
		}
		
		ParkingLot parking = new ParkingLot(parkingSpotsSize);
		List<String> output = new ArrayList<>();
		
		
		for (List<String> instruction : instructions) {
			String command = instruction.get(0);
			
			if (command.equals("park")) {
                parking.parkVehiculeByDescription(Integer.parseInt(instruction.get(1)), instruction.get(2), instruction.get(3), instruction.get(4));
            }
			else if (command.equals("remove")) {
				parking.removeCar(Integer.parseInt(instruction.get(1)));
            }
			else if (command.equals("print")) {
                output.add(parking.printSpot(Integer.parseInt(instruction.get(1))));
            }
			else if (command.equals("print_free_spots")) {
                output.add(parking.printFreeSpots());
            }
		}
		
		return output;
	}
	
	public static List<String> splitWords(String s) {
		return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
	}
}