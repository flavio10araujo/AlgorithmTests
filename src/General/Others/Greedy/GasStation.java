package General.Others.Greedy;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/gas-station/
 * 
 * There are a total of n gas stations along a clockwise, circular route, 
 * and for the ith gas station (starting from 0), there are gas[i] litre of gas stored.
 * The distance between the ith gas station and the i + 1th gas station is dist[i].
 * You have a car, and you would like to travel clockwise starting from any gas station and return to the same gas station. 
 * The car has a sufficiently large gas tank, and for each unit of gas, your car can travel a unit distance. 
 * Initially, the fuel tank of your car is empty, but you can add fuel from any fuel station for as long as that fuel station has fuel left.
 * You wonder whether you can successfully travel this way. 
 * If so, output the index representing the starting gas station. 
 * Otherwise, output -1. 
 * The solution is guaranteed to be unique, if one exists.
 * 
 * Input
 * gas: a list of integers representing the gas available for each gas station.
 * dist: a list of integers representing the distance between each two adjacent gas stations.
 * Output The unique starting gas station to be able to travel once, or -1 if no such station exists.
 * 
 * Example 1:
 * Input: gas = [1, 2, 3, 4, 5] dist = [3, 4, 5, 1, 2]
 * Output: 3
 * Explanation:
 * Starting from station 3, the car has 4 gas. It travels to the next station spending 1 gas, leaving 3 gas. 
 * Pick up 5 gas, travel to the next station, and leaving 6 gas. 
 * For the next three station, there is enough fuel to travel to each one and return to the starting point.
 * 
 * Solution
 * 
 * This question can be solved with greedy. 
 * The reason for this is as follows: if a car can reach a location from a previous location, there is no reason for the car to start at the current location. 
 * If a car has leftover fuel, when it picks up fuel from the current station, it can go further than if the car started at the current station. 
 * On the other hand, if the car has no fuel, it can travel the same distance as starting at the current station, but it will require less distance travelled before finishing the circle.
 * In that case, the solution can be solved like this: 
 * Starting from station 0, for each cycle, we restock from the station and attempt to travel to the next station (by subtracting the fuel remaining by the distance to the next station). 
 * If the fuel is negative, we can't travel to the next station, so we restart the trip at the next station. 
 * Otherwise, we continue our trip, as there is no reason for us to stop the trip.
 * If the stations travelled is equal to the number of stations, we have finished the round trip, and we can return the starting station index as our answer. 
 * If there exists a solution, it will be found before finishing two passes of the cycle. 
 * This is because that we take less than n steps to reach the solution starting point, and n steps to verify that a point is indeed a solution. 
 * The number of steps taken, if there is a solution, is less than 2 * n.
 * Therefore, if after two passes, there are still no solution, we can return -1 to indicate that it is impossible to travel in a circle.
 * The time complexity is O(n), where n is the number of stations.
 */
public class GasStation {

	public static int startingStation(List<Integer> gas, List<Integer> dist) {
        int n = gas.size();
        int currentStart = -1, currentGas = 0, currentLocation = 0;
        
        while (currentLocation < n * 2) {
            
        	System.out.println("currentLocation="+currentLocation+" n="+n);
        	
        	if (currentStart == -1) {
                currentStart = currentLocation;
            }
            
            currentGas += gas.get(currentLocation % n);
            System.out.println("currentGas="+currentGas);
            
            currentGas -= dist.get(currentLocation % n);
            System.out.println("currentGas="+currentGas);
            
            if (currentGas < 0) {
                currentStart = -1;
                currentGas = 0;
            }
            
            currentLocation++;
            
            System.out.println("currentLocation="+currentLocation+" currentStart="+currentStart);
            
            if (currentStart != -1 && currentLocation - currentStart == n) {
                return currentStart % n;
            }
        }
        
        return -1;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    // 1 2 3 4 5
    // 3 4 5 1 2
    // output: 3
    
    // 2 3 4
    // 3 4 3
    // output: -1
    
    // 2 1 3 4 10
    // 1 7 2 6 3
    // output: 4
    
    // 1 5 5 4 1 9 1 2
    // 8 2 2 2 10 1 2 1
    // output: 5
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> gas = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> dist = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int res = startingStation(gas, dist);
        System.out.println(res);
    }
}