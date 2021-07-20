package General.TwoPointers.CycleFinding;

/**
 * A "Happy Number" is defined as a number that after finite number of "steps" - where we sum the square of each digit each time - the result is a 1. 
 * 
 * Given a number n, determine whether it is a happy number.
 * As a challenge, complete this question under constant space.
 * 
 * Parameters
 * n: The number to check.
 * Result: true or false, depending on whether this number is a happy number.
 * 
 * Example 1
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * 
 * Example 2
 * Input: n = 2
 * Output: false
 * Explanation:
 * 2^2 = 4
 * 4^2 = 16
 * 1^2 + 6^2 = 37
 * 3^2 + 7^2 = 58
 * 5^2 + 8^2 = 89
 * 8^2 + 9^2 = 145
 * 1^2 + 4^2 + 5^2 = 42
 * 4^2 + 2^2 = 18
 * 1^2 + 8^2 = 65
 * 6^2 + 5^2 = 61
 * 6^2 + 1^2 = 37
 * ...
 * 
 * Constraints
 * 1 <= n < 2^31
 * 
 * Solution:
 * 
 * If a number is not happy, then we can simply detect whether there is a loop during the steps. 
 * This is because there is only a finite number of numbers whose next step is larger than itself, so the sequence is always bounded. 
 * Since the sequence is infinite, there must be a repeated number, which causes the loop.
 * If there is no constant space restriction, we can simply use a set. 
 * However, because of the constant space restriction clause, we need to think a bit cleverly.
 * Introducing Floyd's cycle-finding algorithm, otherwise known as "tortoise and hare algorithm". 
 * It utilizes two pointers, one moving at double speed as the other. 
 * The reasoning is that if there is a cycle, the faster pointer will eventually catch up with the slower pointer inside the loop, 
 * at which point we can stop the algorithm. The algorithm also ends when a pointer reaches 1. 
 * Either way, at the end of the algorithm, we can simply check whether the faster pointer is at 1.
 * This algorithm can also be used to detect the cycle size and the starting point of the cycle, but this question does not use either of them.

Time Complexity: O(n)

Same as before, technically O(n/2) but we factor out the constant.
 */
public class HappyNumber {

	public static int step(int n) {
        int res = 0;
        
        while (n > 0) {
            res += (n % 10) * (n % 10);
            n = n / 10;
        }
        
        return res;
    }

    public static boolean isHappyNumber(int n) {
        int tortoise = step(n);
        int hare = step(step(n));
        
        while (tortoise != hare && hare != 1) {
            tortoise = step(tortoise);
            hare = step(step(hare));
        }
        
        return hare == 1;
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt("19");
        boolean res = isHappyNumber(n);
        System.out.println(res);
    }
}