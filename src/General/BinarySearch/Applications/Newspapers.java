package General.BinarySearch.Applications;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You've begun working in the one and only Umbristan. 
 * As part of your job working for the government you are asked to organize newspapers. 
 * Each morning your fellow coworkers will dilligently read through the newspapers carefully examining its contents. 
 * It is your job to organize the newspapers into piles and hand them out to your coworkers to read through. 
 * Each newspaper is assigned a time based on how much time it would take to read through its contents. 
 * The newspapers are carefully layed out in a line in a particular order that must not be broken when assigning the newspapers. 
 * That is you cannot pick and choose newspapers to make a pile to assign to a co-worker to read through. 
 * Instead you must take a particular subsection of the line of newspapers, make a pile and give that to a co-worker.
 * 
 * What is the minimum amount of time it would take to have your coworkers go through all the newspapers?
 * 
 * Constraints
 * 1 <= newspapers.length <= 10^5
 * 1 <= newspaper <= 10^4
 * 1 <= coworkers <= 10^5
 * 
 * Example:
 * Input: newspapers = [7 2 5 10 8], coworkers = 2
 * Output: 18
 * Explanation:
 * Assign newspapers 1 - 3 to one coworker then assign the rest to another. 
 * The time it takes for the first 3 newspapers is 7 + 2 + 5 = 14 and for the last 2 is 10 + 8 = 18.
 */
public class Newspapers {
	
	/**
	 * Time complexity: O(log(N)).
	 * @param newspapers
	 * @param coworkers
	 * @return
	 */
	public static int newspapersSplit(List<Integer> newspapers, int coworkers) {
        
		int L = Collections.max(newspapers);
		int R = 0;
		
		for (Integer i : newspapers) {
			R += i;
		}
		
		int numOfHours = R;
		
		while (L <= R) {
			int M = (L + R) / 2;
			
			System.out.println("L="+L+" M="+M+" R="+R);
			
			if (calcNumOfWorkers(newspapers, M) <= coworkers) {
				R = M - 1;
				numOfHours = M;
			} else {
				L = M + 1;
			}
		}
		
        return numOfHours;
    }
	
	public static int calcNumOfWorkers(List<Integer> newspapers, int hours) {
		int numOfWorkers = 1;
		int index = 0;
		int sumHours = 0;
		
		while (index < newspapers.size()) {
			
			sumHours += newspapers.get(index); 
			
			if (sumHours > hours) {
				numOfWorkers++;
				sumHours = newspapers.get(index);
			}
			
			index++;
			
		}
		
		System.out.println("numOfWorkers="+ numOfWorkers);
		
		return numOfWorkers;
	}

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<Integer> newspapers = splitWords("7 2 5 10 8").stream().map(Integer::parseInt).collect(Collectors.toList());
        int coworkers = Integer.parseInt("2");
        int res = newspapersSplit(newspapers, coworkers);
        System.out.println(res);
    }
}