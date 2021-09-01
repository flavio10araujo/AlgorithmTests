package Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Testando04 {
	
	public static int contador = 0;

	public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
    
    public static void main(String[] args) {
        List<Integer> coins = splitWords("1 2 5").stream().map(Integer::parseInt).collect(Collectors.toList());
        int amount = Integer.parseInt("11");
        int res = coinChange(coins, amount);
        System.out.println(res);
    }
    
    public static int coinChange(List<Integer> coins, int amount) {
    	List<Integer> res = new ArrayList<Integer>();
    	
    	int qtdCoins = 0;
    	int qtdFinal = Integer.MAX_VALUE;
    	Set<String> myset = new HashSet<String>();
    	
    	Collections.reverse(coins);
    	
    	for (Integer i : coins) {
    		res.add(i);
    		
    		qtdCoins = dfs(coins, amount, res, i, myset, Integer.MAX_VALUE);
    		
    		if (qtdCoins > 0) {
    			qtdFinal = Math.min(qtdFinal, qtdCoins);
    		}
    		
    		res.clear();
    	}
    	
    	return qtdFinal;
    }
    
    public static String prepareList(List<Integer> res) {
    	StringBuilder s = new StringBuilder();
    	
    	List<Integer> lista = new ArrayList<Integer>(res);
    	
    	Collections.sort(lista);
    	
    	for (Integer i : lista) {
    		s.append(i+" ");
    	}
    	
    	return s.toString();
    }
    
    public static int dfs(List<Integer> coins, int amount, List<Integer> res, int sum, Set<String> myset, int qtdFinal) {
    	
    	System.out.println(++contador);
    	
    	if (sum >= amount) {
	    	if (sum == amount) {
	    		return res.size();
	    	} else if (sum > amount) {
	    		return -1;
	    	}
    	}
    	
    	int qtdCoins = 0;
    	
    	for (Integer i : coins) {
    		sum += i;
    		res.add(i);
    		
    		if (res.size() < qtdFinal && !myset.contains(prepareList(res))) {
    			if (sum >= amount) {
    				
    				myset.add(prepareList(res));
    				
    		    	if (sum == amount) {
    		    		qtdCoins = res.size();
    		    	} else if (sum > amount) {
    		    		qtdCoins = -1;
    		    	}
    	    	} else {
    	    		qtdCoins = dfs(coins, amount, res, sum, myset, qtdFinal);
    			}
    		}
    		
    		sum -= i;
			res.remove(res.size() - 1);
    		
    		if (qtdCoins > 0) {
    			qtdFinal = Math.min(qtdFinal, qtdCoins);
    		} else {
    			break;
    		}
    	}
    	
    	return qtdFinal;
    }
}