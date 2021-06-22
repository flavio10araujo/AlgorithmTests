package General;

/**
 * Program to change two ints without creating a new variable.
 */
public class ChangeTwoIntsWithousCreatingNewVariable {
	
	public static void main(String[] args) {
		
		int a = 18;
		int b = 3;
		
		System.out.printf("a = %d b = %d %n", a, b);
		
		a = a + b;
		b = a - b;
		a = a - b;
		
		System.out.printf("a = %d b = %d %n", a, b);
	}
}