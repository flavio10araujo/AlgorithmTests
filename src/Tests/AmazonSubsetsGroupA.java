package Tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Precisa dividir o array nums em duas partes.
 * 
 * Chamaremos de parte "A" e parte "B".
 * 
 * A parte "A" deve:
 * - possui sempre menos números do que a parte "B";
 * - a soma dos números da parte "A" deve ser maior do que a soma dos números da parte "B".
 * 
 * Não podemos utilizar o mesmo número do array nums mais do que uma vez.
 * 
 * Devemos retornar no método os números que fazem parte do grupo "A".
 * 
 * Se houver mais de uma possibilidade de dividir o array nums em dois grupos que seguem as regras descritas,
 * retorne o grupo "A" onde os números somados são o maior possível.
 * 
 * Exemplo:
 * input: nums={4,2,5,1,6}
 * output: {5,6}
 * explanation: 
 * 		Grupo B={4,2,1}. Soma=7. Possui 3 elementos.
 * 		Grupo A={5,6}. Soma=11. Possui 2 elementos.
 */
public class AmazonSubsetsGroupA {
	
	static int maxSum = 0;
	static int minSum = 0;
	static int maxN = 0;

	public static void main(String[] args) {
		List<Integer> nums = List.of(4,2,5,1,6);
		//List<Integer> nums = List.of(3,7,5,6,2);
		//List<Integer> nums = List.of(2,3,4,4,9,10,10,10,10);
		List<Integer> res = solution(nums);
		System.out.println(res);
	}
	
	private static List<Integer> solution(List<Integer> arr) {
		List<List<Integer>> ret = new ArrayList<>();
		
		minSum = arr.stream().mapToInt(i -> i).sum() / 2;
		maxN = arr.size() / 2;
		
		dfs(arr, ret, 0, new ArrayList<>(), 0);
		
		return ret.get(ret.size() - 1);
	}
	
	private static void dfs(List<Integer> arr, List<List<Integer>> ret, int start, List<Integer> path, int sum) {
		if (path.size() > maxN) {
			return;
		}
		
		if (sum > minSum) {
			if (sum > maxSum) {
				maxSum = sum;
				List<Integer> pathAdd = new ArrayList<>(path);
				Collections.sort(pathAdd);
				ret.add(pathAdd);
			}
			
			return;
		}
		
		if (path.size() == maxN) {
			return;
		}
		
		for (int i = start; i < arr.size(); i++) {
			path.add(arr.get(i));
			sum += arr.get(i);
			
			dfs(arr, ret, i + 1, path, sum);
			
			sum -= arr.get(i);
			path.remove(path.size() - 1);
		}
	}
}