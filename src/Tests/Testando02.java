package Tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// Tentativa de fazer o Integer Break
public class Testando02 {
	
	static int contagem = 1;
	
	public static void main(String[] args) {
		int n = 20;
		List<Integer> numeros = getNumeros(n);
		System.out.println(numeros.toString());
    }
	
	public static List<Integer> getNumeros(int n) {
		List<Integer> res = new ArrayList<Integer>();
		
		dfs(n, res, new ArrayList<Integer>(), new HashSet<String>());
		
		return res;
	}
	
	public static void dfs(int n, List<Integer> res, List<Integer> path, Set<String> memo) {
	
		if (sumValues(path, 0) == n) {
			
			memo.add(pathOrdered(path));
			
			//System.out.println("MEMO = " + memo.toString());
			
			if (multiplyValues(path) > multiplyValues(res)) {
				res.clear();
				
				for (Integer l : path) {
					res.add(l);
				}
				
				return;
			}
		}
		
		for (int i = 1; i <= n; i++) {
			
			System.out.println("contagem: " + ++contagem);
			
			if (sumValues(path, i) <= n) {
				path.add(i);
				
				//System.out.println(path.toString());
				
				if (memo.contains(pathOrdered(path))) {
					
					//System.out.println("ja existe");
					
					path.remove(path.size() - 1);
					continue;
				} else {
					dfs(n, res, path, memo);
					path.remove(path.size() - 1);
				}
			} else {
				i = n + 1;
			}
		}
		
	}
	
	public static int sumValues(List<Integer> path, int i) {
		int sum = i;
		
		for (Integer j : path) {
			sum += j;
		}
		
		return sum;
	}
	
	public static int multiplyValues(List<Integer> path) {
		int mult = 1;
		
		if (path.size() == 0) {
			return 0;
		}
		
		for (Integer i : path) {
			mult *= i;
		}
		
		return mult;
	}
	
	public static List<Integer> receiveValues(List<Integer> path) {
		List<Integer> res = new ArrayList<Integer>();
		
		for (Integer i : path) {
			res.add(i);
		}
		
		System.out.println("new res = " + res.toString());
		
		return res;
	}
	
	public static String pathOrdered(List<Integer> path) {
		List<Integer> ordered = new ArrayList<Integer>(path);
		
		Collections.sort(ordered);
		
		//System.out.println("ORDERED : " + ordered.toString());
		
		return ordered.toString();
	}
}