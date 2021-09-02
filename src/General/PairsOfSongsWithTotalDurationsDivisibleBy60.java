package General;

/**
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60. 
 * Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 * 
 * Example 1:
 * Input: time = [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * 
 * Example 2:
 * Input: time = [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 * 
 * Explanation
 * 
 * This question is basically Two Sum with modulo. 
 * Essentially, we convert every number to its modulo 60 and then do Two sum.
 */
public class PairsOfSongsWithTotalDurationsDivisibleBy60 {

	public static void main(String[] args) {
		int[] time = {30,20,150,100,40};
		System.out.println(numPairsDivisibleBy60(time));
	}
	
	/**
	 * Time complexity: O(n).
	 * 
	 * @param time
	 * @return
	 */
	public static int numPairsDivisibleBy60(int[] time) {
		/*int[] count = new int[60]; // All number % 60 will be between 0 and 59.
        
        int res = 0;
        
        for (int i = 0; i < time.length; i++) {
            res += count[(600 - time[i]) % 60];
            count[time[i] % 60] += 1;
        }
        
        return res;*/
		
		int res = 0;
	    int[] count = new int[60];

	    for (int t : time) {
	      t %= 60;
	      res += count[(60 - t) % 60];
	      ++count[t];
	    }

	    return res;
    }
}