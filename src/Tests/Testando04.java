package Tests;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Testando04 {
	
	public static void main(String[] args) {
        /*int[] arr = new int[10];
        
        Arrays.fill(arr, 8);
        
        String s = Arrays.toString(arr);
        
        System.out.println(s);
        
        int[] arr2 = {3, 7, 2, 9, 4};
        System.out.println(Arrays.toString(arr2));
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));
        
        Integer[] arr3 = {3, 7, 8, 2, 5, 7};
        List<Integer> myList = Arrays.asList(arr3);
        
        for (Integer i : myList) {
        	System.out.println(i);
        }
        
        System.out.println(myList);*/
		
		/*List<Integer> myList = List.of(1, 2);
		
		System.out.println(myList);
		
		String s2 = "abc$def";
		
		char[] cArr = s2.toCharArray();
		
		for (int i = 0; i < cArr.length; i++) {
			
			if (!Character.isLetterOrDigit(cArr[i])) {
				System.out.println(cArr[i] + " is not a valid character!");
			} else {
				System.out.println(Character.toUpperCase(cArr[i]));
			}
		}*/
		
		/*List<Integer> integers = List.of(1, 2, 3, 4, 5);
		
		int sum = integers.stream().mapToInt(lala -> lala).sum();
		
		System.out.println(sum);*/
		
		
		/*List<Integer> nums = List.of(2, 2, 5, 5, 5);
		Map<Integer, Integer> numCount1 = new HashMap<>();
		Map<Integer, Integer> numCount2 = new HashMap<>();
		
		for (int i : nums) { 
			numCount1.merge(i, 1, Integer::sum);
			numCount2.put(i, numCount2.getOrDefault(i, 0) + 1);
		}
		
		System.out.println(numCount1);
		System.out.println(numCount2);*/
		
		//Map<Integer, String> alphabet = Map.of(1, "A", 2, "B", 3, "C");
		
		/*Map<Integer, String> alphabet = new HashMap<>();
		
		alphabet.put(1, "A");
		alphabet.put(2, "B");
		alphabet.put(3, "C");
		
		System.out.println(alphabet);
		
		//System.out.println(alphabet.get(3));
		
		System.out.println(alphabet.getOrDefault(3, "Not found"));
		System.out.println(alphabet.getOrDefault(4, "Not found"));*/
		
		/*Map<Integer, String> myMap = Map.of(1, "A", 2, "B", 3, "C");
		
		for (Integer i : myMap.keySet()) {
			System.out.println("key = " + i + " value = " + myMap.get(i));
		}*/
		
		//Map<String, List<String>> graph = Map.of("A", List.of("A1", "A2"), "B", List.of("B1", "B2"));
		
		/*Map<Integer, String> myMap = Map.of(1, "A", 2, "B", 3, "C");
		
		for (Map.Entry<Integer, String> entry : myMap.entrySet()) {
			System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
		}*/
		
		/*Map<String, Boolean> mymap = Map.of("A", true, "B", false);
		
		if (mymap.containsKey("A")) {
			System.out.println("entrou");
		}
		
		if (mymap.containsKey("B")) {
			System.out.println("entrou");
		}
		
		if (mymap.containsKey("C")) {
			System.out.println("não entrou");
		}
		
		if (mymap.get("A")) {
			System.out.println("lalala");
		}*/
		
		/*Map<Integer, String> mymap = Map.of(1, "A", 2, "B", 3, "C");
		
		if (mymap.containsValue("A")) {
			System.out.println("entrou");
		}
		
		if (mymap.containsValue("D")) {
			System.out.println("não entrou");
		}*/
		
		/*Map<Integer, String> visited = Map.of(1, "A", 2, "B");
		if (visited.containsKey(1)) {}
		if (visited.containsValue("A")) {}*/
		
		/*int n = 3968;
		
		System.out.println("Parte 1: " + (n / 1000));
		System.out.println("Parte 1: " + (n % 1000));
		System.out.println("Parte 2: " + (n % 10));*/
		
		//List<String> lista = List.of("a", "b", "c", "d");
		/*List<String> lista = new LinkedList<>();
		
		lista.add("a");
		lista.add("b");
		lista.add("c");
		
		System.out.println(lista);
		
		lista.remove(lista.size() - 1);
		
		System.out.println(lista);*/
		
		/*String s = "01234 6789 ABCD";
		System.out.println(s.indexOf(" ")); // 5
		System.out.println(s.lastIndexOf(" ")); // 10*/
		
		/*List<String> list = new LinkedList<>();
		
		list.add("DDD");
		list.add("AAA");
		list.add("CCC");
		list.add("BBB");
		
		System.out.println(list);*/
		
		char[][] board = new char[10][20];
		
		int height = board.length;
	    int width = board[0].length;
	    
	    System.out.println(height);
	    System.out.println(width);
    }
}