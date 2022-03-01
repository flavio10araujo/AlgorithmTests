package General;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/
 * 
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 * 
 * Example 1:
 * Input: n = 1
 * Output: 1
 * 
 * Example 2:
 * Input: n = 2
 * Output: 2
 * 
 * Example 3:
 * Input: n = 3
 * Output: 5
 * 
 * Example 4:
 * Input: n = 4
 * Output: 14
 * 
 * Example 5:
 * Input: n = 5
 * Output: 42
 */
public class UniqueBinarySearchTrees {

	public static void main(String[] args) {
		//int n = 1;
		//int n = 2;
		//int n = 3;
		int n = 4;
		//int n = 5;
		
		System.out.println(numTrees(n));
	}

	public static int numTrees(int n) {
		// G[i] := # of unique BST's that store values 1..i
		int[] G = new int[n + 1];
		G[0] = 1;
		G[1] = 1;

		for (int i = 2; i <= n; ++i)
			for (int j = 0; j < i; ++j)
				G[i] += G[j] * G[i - j - 1];

		return G[n];
	}
}