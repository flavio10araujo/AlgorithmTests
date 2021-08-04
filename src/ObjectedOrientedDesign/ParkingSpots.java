package ObjectedOrientedDesign;

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
 */
public class ParkingSpots {

	
}