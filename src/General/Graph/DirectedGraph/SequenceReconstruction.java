package General.Graph.DirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Check whether the original sequence original can be uniquely reconstructed from the sequences in seqs.
 * The org sequence is a permutation of the integers from 1 to n.
 * Reconstruction means building a shortest common supersequence of the sequences in seqs 
 * (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
 * 
 * Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 * 
 * Parameters:
 * 	original: A list of integers of size n representing the original sequence.
 * 	seqs: A list of sequences of size m representing the sequences to be reconstructed.
 * Result
 * 	true or false, depending on whether the original sequence can be uniquely reconstructed.
 * 
 * Example 1:
 * Input: org: [1,2,3], seqs: [[1,2], [1,3]]
 * Output: false
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
 * 
 * Example 2:
 * Input: org: [1,2,3], seqs: [[1,2]]
 * Output: false
 * Explanation:
 * The reconstructed sequence can only be [1,2].
 * 
 * Example 3:
 * Input: org: [1,2,3], seqs: [[1,2], [1,3], [2,3]]
 * Output: true
 * Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 * 
 * Example 4:
 * Input: org: [4,1,5,2,6,3], seqs: [[5,2,6,3], [4,1,5,2]]
 * Output: true
 * 
 * Constraints
 * 1 <= n <= 10^4
 * 1 <= m <= 10^4
 * 1 <= len(seqs[i]) <= n
 */
public class SequenceReconstruction {

	public static boolean sequenceReconstruction(List<Integer> original, List<List<Integer>> seqs) {
        
		int n = original.size();
        List<Set<Integer>> postReqs = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            postReqs.add(new HashSet<>());
        }
        
        int[] preReqCount = new int[n + 1];
        
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size() - 1; i++) {
                int earlyNum = seq.get(i);
                int lateNum = seq.get(i + 1);
                
                if (earlyNum < 1 || earlyNum > n || lateNum < 1 || lateNum > n) {
                    return false;
                }
                
                if (!postReqs.get(earlyNum).contains(lateNum)) {
                    postReqs.get(earlyNum).add(lateNum);
                    preReqCount[lateNum]++;
                }
            }
        }
        
        int checkIndex = 0;
        int singleQueue = -1;
        
        for (int i = 1; i <= n; i++) {
            if (preReqCount[i] == 0) {
                if (singleQueue == -1) {
                    singleQueue = i;
                } else {
                    return false;
                }
            }
        }
        
        while (singleQueue != -1) {
            
        	int validEntry = singleQueue;
            singleQueue = -1;
            
            if (validEntry != original.get(checkIndex)) {
                return false;
            }
            
            for (int postReq : postReqs.get(validEntry)) {
                preReqCount[postReq]--;
                
                if (preReqCount[postReq] <= 0) {
                    if (singleQueue == -1) {
                        singleQueue = postReq;
                    } else {
                        return false;
                    }
                }
            }
            
            checkIndex++;
        }
        
        return checkIndex == n;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        List<Integer> original = splitWords("1 2 3").stream().map(Integer::parseInt).collect(Collectors.toList());
        List<List<Integer>> seqs = new ArrayList<>();
        
        seqs.add(splitWords("1 2").stream().map(Integer::parseInt).collect(Collectors.toList()));
        seqs.add(splitWords("1 3").stream().map(Integer::parseInt).collect(Collectors.toList()));
        
        boolean res = sequenceReconstruction(original, seqs);
        System.out.println(res);
    }
}