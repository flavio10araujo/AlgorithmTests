package General.Backtracking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a list of unique letters, find all of its distinct permutations.
 * Input:
 *   ['a', 'b', 'c']
 * Output:
 *   [['a', 'b', 'c'], ['a', 'c', 'b'], ['b', 'a', 'c'], ['b', 'c', 'a'], ['c', 'a', 'b'], ['c', 'b', 'a']]
 */
public class Permutations {

	public static List<List<Character>> permute(char[] letters) {
        List<List<Character>> res = new ArrayList<>();
        dfs(new ArrayList<>(), new boolean[letters.length], res, letters);
        return res;
    }
	
	private static void dfs(List<Character> path, boolean[] used, List<List<Character>> res, char[] letters) {
        if (path.size() == used.length) {
            // make a deep copy since otherwise we'd be append the same list over and over
            res.add(new ArrayList<Character>(path));
            System.out.println("ENTROU NO PRIMEIRO IF");
            return;
        }
        
        System.out.println("ANTES DO FOR ");

        for (int i = 0; i < used.length; i++) {
            // skip used letters
            if (used[i]) continue;
            // add letter to permutation, mark letter as used
            path.add(letters[i]);
            used[i] = true;
            
            System.out.println("i antes="+i+" path="+path.toString());
            dfs(path, used, res, letters);
            
            
            // remove letter from permutation, mark letter as unused
            path.remove(path.size() - 1);
            used[i] = false;
            
            System.out.println("i depois="+i+" path="+path.toString());
        }
        
        System.out.println("DEPOIS DO FOR");
    }

    public static void main(String[] args) throws IOException {
        char[] input = {'a', 'b', 'c'};
        List<List<Character>> permutations = permute(input);
        
        System.out.println(
            permutations.stream()
                .map(chars -> chars.stream().map(String::valueOf).collect(Collectors.joining()))
                .collect(Collectors.joining(" "))
        );
    }
}