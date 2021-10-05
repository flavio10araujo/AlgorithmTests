package General;

import java.util.Stack;

public class SortStack {

	public static void main(String args[]) {
        Stack<Integer> input = new Stack<Integer>();
        
        input.add(34);
        input.add(3);
        input.add(31);
        input.add(98);
        input.add(92);
        input.add(23);
     
        // This is the temporary stack
        Stack<Integer> tmpStack = sortstack(input);
        System.out.println("Sorted numbers are:");
     
        while (!tmpStack.empty()) {
            System.out.print(tmpStack.pop()+" ");
        }
    }
	
	public static Stack<Integer> sortstack(Stack<Integer> input) {
		
		Stack<Integer> output = new Stack<>();
		
		while(!input.isEmpty()) {
			Integer n = input.pop();
			
			if (output.isEmpty() || n >= output.peek()) {
				output.push(n);
			} else {
				while(!output.isEmpty() && n < output.peek()) {
					input.push(output.pop());
				}
				
				output.push(n);
			}
		}
		
		return output;
	}
}