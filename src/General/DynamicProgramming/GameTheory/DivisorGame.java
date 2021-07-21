package General.DynamicProgramming.GameTheory;

/**
 * James and Oliver take turns playing a game, with James starting first.
 * Initially, there is a number N on the chalkboard. 
 * 
 * On each player's turn, that player makes a move consisting of:
 * 	* Choosing any x with 0 < x < N 
 * 		and N % x == 0 
 *  	where 1 <= N <= 1000.
 *  * Replacing the number N on the chalkboard with N - x.
 * 
 * Also, if a player cannot make a move, they lose the game.
 * 
 * Return True if and only if James wins the game, assuming both players play optimally.
 * 
 * Example 1:
 * Input: 2
 * Output: true
 * Explanation: James chooses 1, and Oliver has no more moves.
 * 
 * Example 2:
 * Input: 3
 * Output: false
 * Explanation: James chooses 1, and Oliver chooses 1, and James has no more moves.
 * 
 * Solution
 * 
 * Winning and losing states
 * A winning state is a state where the player will win the game if they play optimally, 
 * and a losing state is a state where the player will lose the game if the opponent plays optimally. 
 * We can classify all states of a game so that each state is either a winning state or a losing state.
 * State 1 is clearly a losing state, because the player cannot make any moves. 
 * States 2 is winning state, because we can remove one stick and force the opponent into the losing state of 1 and win the game. 
 * State 3, in turn, is a losing state, because any move leads to a state that is a winning state for the opponent.
 * More generally, if there is a move that leads from the current state to a losing state, 
 * the current state is a winning state, and otherwise the current state is a losing state. 
 * Using this observation, we can classify all states of a game starting with losing states where there are no possible moves.
 * State 1 is a losing state. 
 * For each states from 2..N, we try all the possible x we can choose and see if it results in a losing state for the opponent. 
 * The state transitions for the first 8 numbers are illustrated here:
 * 8 => {7, 6, 4}
 * 7 => {6}
 * 6 => {5, 4}
 * 5 => {4}
 * 4 => {3, 2}
 * 3 => {2}
 * 2 => {1}
 */
public class DivisorGame {

	public static boolean divisorGame(int n) {
        boolean[] dp = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            
        	for (int x = 1; x < i; x++) {
            	
            	System.out.println("valores: i="+i+" x="+x);
            	
                if (i % x == 0 && !dp[i - x]) {
                	System.out.println("entrou no if");
                	dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }

    public static void main(String[] args) {
        int n = Integer.parseInt("8");
        boolean res = divisorGame(n);
        System.out.println(res);
    }
}