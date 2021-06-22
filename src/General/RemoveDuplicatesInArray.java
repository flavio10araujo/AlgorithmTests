package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * This code show some options to remove duplicates values in a list.
 * 
 */
public class RemoveDuplicatesInArray {

	public static void main(String[] args) {
		// ArrayList with duplicates.
		List<String> duplicateList = (List<String>) Arrays.asList("Android" , "Android", "iOS", "Windows mobile");
		
		// First solution : using HashSet.
		RemoveDuplicatesInArray.removeDuplicatesWithHashSet(duplicateList);
		
		// Second solution : using LinkedHashSet.
		RemoveDuplicatesInArray.removeDuplicatesWithLinkedHashSet(duplicateList);
	}
	
	/**
	 * The first approach to remove duplicates in a list is to convert the list to HashSet.
	 * HashSet doesn't insert the items in order. So you will end up with a list without duplicates but in a different order.
	 * 
	 * @param duplicateList
	 */
	private static void removeDuplicatesWithHashSet(List<String> duplicateList) {
		System.out.println("Original list: " + duplicateList);
		
		// Converting ArrayList to HashSet to remove duplicates.
		HashSet<String> myHashSet = new HashSet<String>(duplicateList);
		
		// Creating an ArrayList without duplicate values.
		List<String> listWithoutDuplicates = new ArrayList<String>(myHashSet);
		
		System.out.println("List without duplicates: " + listWithoutDuplicates);
	}
	
	/**
	 * The second approach to remove duplicates in a list is to convert the list to LinkedHashSet.
	 * The difference between using HashSet and LinkedHashSet is that the LinkedHashSet keeps the order of the items in the list.
	 * 
	 * @param duplicateList
	 */
	private static void removeDuplicatesWithLinkedHashSet(List<String> duplicateList) {
		System.out.println("Original list: " + duplicateList);
		
		// Converting ArrayList to HashSet to remove duplicates.
		LinkedHashSet<String> myLinkedHashSet = new LinkedHashSet<String>(duplicateList);
		
		// Creating an ArrayList without duplicate values.
		List<String> listWithoutDuplicates = new ArrayList<String>(myLinkedHashSet);
		
		System.out.println("List without duplicates and in order: " + listWithoutDuplicates);
	}
}