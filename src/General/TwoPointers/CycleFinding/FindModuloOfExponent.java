package General.TwoPointers.CycleFinding;

/**
 * Calculate the value of n^(2^k) % m. 
 * This question must be solved using constant memory.
 * 
 * Parameters
 * n: An integer representing the base of the exponent
 * k: An integer representing the exponent of the exponent of 2.
 * m: An integer representing the base of the modulo
 * Result: The result of the expression.
 * 
 * Example 1
 * Input: n = 2, k = 3, m = 10
 * Output: 6
 * Explanation: 2^(2^3) % 10 == 2^8 % 10 == 256 % 10 == 6.
 * 
 * Example 2
 * Input: n = 2, k = 34, m = 21
 * Output: 16
 * 
 * Constraints
 * 1 <= n, m <= 30000
 * 1 <= k <= 2^31 - 1
 * 
 * Solution:
 * 
 * The question is basically squaring n k times, modulo each time by m. 
 * However, a simple loop repeating k times is not feasible, as k reaches the integer limit. 
 * What can we do to simplify the calculation?
 * Notice that as long as two numbers have the same modulo, their square have the same modulo as well.
 * In addition, by the pigeonhole principle, squaring m + 1 times will guarantee at least two results have the same value, which forms a loop. 
 * Therefore, this question can be solved by finding the loop size, and modulo k to directly jump to the remainder. 
 * Since a loop is guaranteed to occur within the first m + 1 steps, we can find the loop under O(m) time, which is way better than O(k) in this scenario.
 */
public class FindModuloOfExponent {

}