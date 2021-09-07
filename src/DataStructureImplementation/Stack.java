package DataStructureImplementation;

/**
 * Implement a class for a stack that supports all the regular functions (push, pop) 
 * and an additional function of max() which returns the maximum element in the stack (return None if the stack is empty). 
 * Each method should run in constant time.
 *
 */
public class Stack {

	int[] stack;
	int pointer = -1;
	
	public Stack(int n) {
		this.stack = new int[n];
	}
	
	public void push(int e) {
		if (pointer + 1 >= stack.length) {
			System.out.println("Resizing...");
			
			int[] temp = stack;
			
			this.stack = new int[2 * this.stack.length];
			
			for (int i = 0; i < temp.length; i++) {
				this.stack[i] = temp[i];
			}
		}
		
		stack[++pointer] = e;
	}
	
	public int pop() {
		return stack[pointer--];
	}
	
	public int max() {
		return pointer + 1;
	}
}