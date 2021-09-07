package DataStructureImplementation;

public class StackTests {

	public static void main(String[] args) {
		Stack stack = new Stack(5);
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(2);
		
		System.out.println(stack.max());
		
		System.out.println("Deleting element of the stack: " + stack.pop());
		System.out.println("Deleting element of the stack: " + stack.pop());
		
		System.out.println(stack.max());
		
		stack.push(2);
		stack.push(2);
		stack.push(2);
		
		System.out.println(stack.max());
		
		stack.push(8);
		
		System.out.println(stack.max());
		
		System.out.println("Deleting element of the stack: " + stack.pop());
	}
}