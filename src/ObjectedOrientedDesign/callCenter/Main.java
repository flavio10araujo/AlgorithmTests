package ObjectedOrientedDesign.callCenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * For this question, you need to design a program for a call center. 
 * You must design the system in a way that is easily expandable.
 * This question will come in parts, and you need to answer the previous part before moving on. 
 * You are advised to copy the code from the previous section to be used as a starting point for the next.
 * 
 * Part One
 * 
 * The call center consists of many employees, who are able to handle incoming calls.
 * An employee can be represented by their name (one word, no space).
 * 
 * Your system must support these following commands:
 * - hire [employee]: 
 *   Hire a new employee, adding them to the system. 
 *   Input guarantees that the employee with the same name is not already in the database.
 * - end [phone]: 
 *   End the current conversation with the phone number phone, if phone is in a conversation. 
 *   Print "Call between and ended". 
 *   If phone is currently in the queue, or if it is not calling, do nothing.
 * - dispatch [phone]: 
 *   If phone is not already in a call and not already queued, assign the phone call to the first available employee (in the order they are inserted into the database). 
 *   If all employees are unavailable, queue the call until an employee becomes available (in which case assign this phone call to the first available employee). 
 *   When the call is connected, print "Connecting to ".
 *   
 * Input: instructions: A list of instructions.
 * Output: The list of strings as outputs.
 *   
 * Example:
 *   
 * Input:
 *   
 * instructions = [
 * 	 ["hire", "James"],
 *   ["hire", "Angie"],
 *   ["dispatch", "303-1142"],
 *   ["dispatch", "583-9045"],
 *   ["dispatch", "711-4375"],
 *   ["end", "583-9045"],
 *   ["end", "303-1142"],
 *   ["end", "711-4375"]
 * ]
 * 
 * Output: [
 * 	 "Connecting 303-1142 to James",
 *   "Connecting 583-9045 to Angie",
 *   "Call between 583-9045 and Angie ended",
 *   "Connecting 711-4375 to Angie",
 *   "Call between 303-1142 and James ended",
 *   "Call between 711-4375 and Angie ended",
 * ]
 * 
 * Solution
 * 
 * For the solution, we create the following classes: CallInstance, Employee, and CallCenter. 
 * CallInstance represents an incoming call, and has a number associated with it and the employee it is currently calling (or null if they are not currently in a conversation). 
 * Employee represents an employee in the call center, with a name and the call they are addressing. 
 * The call center stores all incoming call instances by number, a queue for calls, and the employees.
 * 
 * When a call comes, it is stored in the queue, and the call center tries to resolve the queue by assigning each item in the queue to a free employee until it can't. 
 * The same is true for when a call ends and a new employee is hired.
 * 
 * 
 * 
 * Part Two
 * 
 * As the call center gets bigger and bigger, there are more and more types of employees. 
 * There are now two new types of employees: "Manager" and "Director". 
 * The previous type of employee, which handles basic incoming calls, is now known as "Respondent". 
 * "Manager" is one rank higher than "Respondent", and "Director" is one rank higher than "Manager".
 * Because of this change, each employee is represented by <Role> <Name>, where <Role> is the type of employee from "Respondent", "Manager", or "Director", and <Name> is the name of the employee, like before.
 * Each incoming call must first be responded by a "Respondent" (that's, like, the definition of the word). 
 * However, if the respondent is unable to answer the questions asked by the call, then the call must "escalate".
 * To escalate the call, end the current call with an employee (printing the message), and increase the rank required to answer that call by one.
 * For example, if a call with a respondent is escalated, the call will be assigned to the first available manager (if none is available, wait in the manager queue until one becomes free like before). 
 * The same is true for managers and directors. 
 * Output the new connection message if a call is successfully connected to an employee.
 * 
 * Note: 
 * Escalating a call can cause an employee to free up. 
 * In that case, output the connection of the escalated call first, then the connection with that employee.
 * 
 * In that case, your system must also handle the following command:
 * - escalate [phone]: 
 *   If the phone number phone is currently in a call with a "Respondent" or "Manager", escalate the call according to the rules above. 
 *   If it is in a call with a "Director", in a queue, or not calling, do nothing.
 * 
 * Example:
 * 
 * Input:
 * 
 * instructions = [
 * 		["hire", "Respondent", "James"],
 * 		["hire", "Manager", "Angie"],
 * 		["dispatch", "303-1142"],
 * 		["dispatch", "583-9045"],
 * 		["escalate", "303-1142"],
 * 		["end", "303-1142"],
 * 		["end", "583-9045"]
 * ]
 * 
 * Output: [
 * 		"Connecting 303-1142 to Respondent James",
 * 		"Call between 303-1142 and Respondent James ended",
 * 		"Connecting 303-1142 to Manager Angie",
 * 		"Connecting 583-9045 to Respondent James",
 * 		"Call between 303-1142 and Manager Angie ended",
 * 		"Call between 583-9045 and Respondent James ended",
 * ]
 * 
 * Solution
 * 
 * Now that there are multiple types of employees, it is time to create new class. 
 * Respondent, Manager, and Director class now inherits the base Employee class, as they share a lot of common properties such as having names and being able to answer calls. 
 * Each Employee now also has a method for getting their rank (different languages have different tools, so see the solution for details).
 * Additionally, the CallCenter class now has three employee lists representing the employees of different types, and three different queues storing call queues of different ranks.
 * When a call is escalated, if it can be escalated, we first remove the call with the previous person that it is talking to, and increase its rank by one. 
 * Then, we add it to the corresponding queue.
 * When we resolve the call queue, we instead resolve all queues one by one, starting from the one with the highest rank. 
 * This is done to preserve the correct output order in case an escalation causes an empty spot and having to resolve two calls at once.
 * 
 * 
 * 
 * Part Three
 * 
 * Employees at the call center don't just answer calls. 
 * When they are not actively answering calls, they can do some other things.
 * 
 * Because of this, the following command is available:
 * - work [employee]: 
 *   If employee is not actively answering a call, they work, depending on their role:
 *   	- When a "Respondent" works, increase their performance rating by 1.
 *      - When a "Manager" works, promote the "Respondent" with the highest performance rating who is not in an active call to a "Manager" (they are no longer a respondent, and must be referred to as a "Manager"). 
 *        When this happens, print "Respondent is promoted to Manager under the authority of Manager ".
 *      - When a "Director" works, print " holds a meeting".
 *      - If the employee does not exist, their title is incorrect, or if they are currently in a call, do nothing.
 * 
 * Note: 
 * Respondents can be promoted to a manager if their performance rating is high. 
 * Each respondent has a performance rating of 0 when they are hired, and whenever they "work", or answer a call, their performance rating increases by 1. 
 * Only respondents can have a performance rating.
 * 
 * Example:
 * 
 * Input:
 * 
 * instructions = [
 * 		["hire", "Respondent", "James"],
 * 		["hire", "Respondent", "Terry"],
 * 		["hire", "Manager", "Angie"],
 * 		["hire", "Director", "Kelly"],
 * 		["work", "Respondent", "Terry"],
 * 		["work", "Manager", "Angie"],
 * 		["work", "Director", "Kelly"]
 * ]
 * 
 * 
 * Output: [
 * 		"Respondent Terry is promoted to Manager under the authority of Manager Angie",
 * 		"Director Kelly holds a meeting"
 * ]
 * 
 * Solution
 * 
 * For this part, each employee can do different work, so we add an abstract method to the Employee class that tells an employee to work. 
 * An "abstract" method means that the method exists and can be referred to and called, but it is not implemented in the base class and must be implemented in a subclass.
 * Respondents also now has a performance rating score that we need to keep track of, for promotions. 
 * To promote a person, we remove them from their current job, and create a new instance of that person with the new role.
 */
public class Main {
	
	/*
	 8
	 hire James
	 hire Angie
    dispatch 303-1142
    dispatch 583-9045
    dispatch 711-4375
    end 583-9045
    end 303-1142
    end 711-4375
	 */
	// Testing Part One.
		
	/*
	 7
	 hire Respondent James
	 hire Manager Angie
    dispatch 303-1142
    dispatch 583-9045
    escalate 303-1142
    end 303-1142
    end 583-9045
	 */
	// Testing Part Two.
		
	/*
	 7
	 hire Respondent James
	 hire Respondent Terry
	 hire Manager Angie
	 hire Director Kelly
	 work Respondent Terry
	 work Manager Angie
	 work Director Kelly
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
		List<String> res = simulateCallCenter(instructions);
		
		for (String line : res) {
			System.out.println(line);
		}
	}
	
	public static List<String> simulateCallCenter(List<List<String>> instructions) {
		CallCenter callCenter = new CallCenter();
		List<String> output = new ArrayList<>();
		
		for (List<String> instruction : instructions) {
			String command = instruction.get(0);
			
			if (command.equals("hire")) {
                callCenter.hire(Employee.createEmployee(instruction.get(1), instruction.get(2)));
                callCenter.resolveQueue(output);
            }
			else if (command.equals("end")) {
                String number = instruction.get(1);
                
                if (callCenter.callMap.containsKey(number)) {
                    CallInstance currentCall = callCenter.callMap.get(number);
                    
                    if (currentCall.endCall(output)) {
                        callCenter.callMap.remove(number);
                        callCenter.resolveQueue(output);
                    }
                }
            }
			else if (command.equals("dispatch")) {
                String number = instruction.get(1);
                callCenter.addNumberToQueue(number);
                callCenter.resolveQueue(output);
            }
			else if (command.equals("escalate")) {
                String number = instruction.get(1);
                callCenter.escalate(number, output);
                callCenter.resolveQueue(output);
            }
			else if (command.equals("work")) {
                String workerId = instruction.get(1).concat(" ").concat(instruction.get(2));
                
                if (callCenter.employeeMap.containsKey(workerId)) {
                    Employee worker = callCenter.employeeMap.get(workerId);
                    
                    if (worker.talkingTo == null) {
                        worker.work(callCenter, output);
                    }
                }
            }
		}
		
		return output;
	}

	public static List<String> splitWords(String s) {
		return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
	}
}