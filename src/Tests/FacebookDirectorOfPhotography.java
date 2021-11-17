package Tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A photography set consists of N cells in a row, numbered from 1 to N in order, and can be represented by a string C of length N. 
 * Each cell i is one of the following types (indicated by Ci, the ith character of C):
 * 		If Ci = “P”, it is allowed to contain a photographer.
 * 		If Ci = “A”, it is allowed to contain an actor.
 * 		If Ci = “B”, it is allowed to contain a backdrop.
 * 		If Ci = “.”, it must be left empty.
 * 
 * A photograph consists of a photographer, an actor, and a backdrop, 
 * such that each of them is placed in a valid cell, and such that the actor is between the photographer and the backdrop. 
 * Such a photograph is considered artistic if the distance between the photographer and the actor 
 * is between X and Y cells (inclusive), and the distance between the actor and the backdrop is also between X and Y cells (inclusive). 
 * The distance between cells i and j is |i - j| (the absolute value of the difference between their indices).
 * 
 * Determine the number of different artistic photographs which could potentially be taken at the set. 
 * Two photographs are considered different if they involve a different photographer cell, actor cell, and/or backdrop cell.
 * 
 * Constraints
 * 1 <= N <= 2001
 * 1 <= X <= Y <= N
 * 
 * Example 01:
 * N = 5; C = APABA; X = 1; Y = 2;
 * Output: 1
 * 
 * Example 02:
 * N = 5; C = APABA; X = 2; Y = 3;
 * Output: 0
 * 
 * Example 03:
 * N = 8; C = .PBAAP.B; X = 1; Y = 3;
 * Output: 3
 */
public class FacebookDirectorOfPhotography {

	public static void main(String[] args) {
		int N = 8; String C = ".PBAAP.B"; int X = 1; int Y = 3;
		
		
		System.out.println(getArtisticPhotographCount(N, C, X, Y));
	}

	public static int getArtisticPhotographCount(int N, String C, int X, int Y) {
		// Write your code here

		Map<Character, List<Integer>> structure = new HashMap<>();

		for (int i = 0; i < N; i++) {  
			if (C.charAt(i) == '.') {
				continue;
			}

			if (structure.containsKey(C.charAt(i))) {
				List<Integer> positions = structure.get(C.charAt(i));
				positions.add(i);
				structure.put(C.charAt(i), positions);  
			} else {
				List<Integer> positions = new ArrayList<>();
				positions.add(i);
				structure.put(C.charAt(i), positions);
			}
		}

		int count = 0;
		List<Integer> photographers = structure.get('P');

		for (Integer photographer : photographers) {
			List<Integer> actors = structure.get('A');

			for (Integer actor : actors) {
				boolean rightSide = true;
				int remainder = actor - photographer;

				if (actor < photographer) {
					rightSide = false;
					remainder = remainder * -1;
				}

				if (remainder < X || remainder > Y) {
					continue;
				}

				List<Integer> backdrops = structure.get('B');

				for (Integer backdrop : backdrops) {
					int remainderBackdrop = backdrop - actor;

					if (rightSide && (backdrop < actor)) {
						continue;
					}
					
					if (!rightSide && (backdrop > actor)) {
						continue;
					}

					if (remainderBackdrop < 0) {
						remainderBackdrop = remainderBackdrop * -1;
					}

					if (remainderBackdrop < X || remainderBackdrop > Y) {
						continue;
					}

					count++;
				}
			}
		}

		return count;
	}
}