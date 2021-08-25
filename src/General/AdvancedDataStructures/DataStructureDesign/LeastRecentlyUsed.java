package General.AdvancedDataStructures.DataStructureDesign;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * LRU Cache
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * 
 * It should support get and put operations.
 * - get(key): Get the value (which will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * - put(key, value): Set or insert the value if the key is not already present. 
 *   When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *   
 * The cache is initialized with a positive capacity.
 * Can you do both operations in O(1) time complexity?
 * 
 * Input:
 * operations = ['LRUCache', 'put', 'put', 'get', 'put', 'get', 'put', 'get', 'get', 'get']
 * input =      [       [2], [1,1], [2,2],   [1], [3,3],   [2], [4,4],   [1],   [3],   [4]]
 * Output:      [      null,  null,  null,     1,  null,    -1,  null,    -1,     3,     4]
 * 
 * Explanation
 * 
 * Intuition
 * 
 * This question is quite easy to solve without the constraint on the efficiency of get and put operations. 
 * To get O(1) for both operations we have to think creatively.
 * Since the O(1) get data structure we are all very familiar with is hash map, we will use that as a starting point. 
 * We get O(1) get and put for free with a hashmap. 
 * But when the capacity of the LRU cache runs out, we have no way of deciding which element is the least recently used to kick out.
 * 
 * Linked List
 * 
 * How about we try using some other data structure to store this least recently used information? 
 * The requirement here is 
 * 1) maintain order 
 * 2) move elements around with O(1) cost. 
 * A great data structure that comes to mind is a linked list which can maintain order and grow and shrink in O(1) runtime. 
 * To move element around (i.e. delete and then insert) we need a doubly linked list because we can delete a node with a reference to the node itself.
 * In a singly linked list, we need the node and its previous node to delete to remove the current node.
 * The detail implementation of this LRU cache data structure starts to come together. 
 * We know that we will need to leverage both the hash map and the doubly linked list to achieve what we want. 
 * How do we do it? 
 * We can use the hash map to store keys that are stored in the LRU cache for easy access, 
 * but instead of storing the value as hash map's value, we store a pointer to a node in the doubly linked list in the hash map's value instead.
 * 
 * Get operation
 * 
 * In the get operation, we first check whether the key we want to find is in the hash map. 
 * If it is, then we locate the doubly linked list element in O(1) time and promote to the front of the doubly linked list, 
 * thus making it the most recently used element. 
 * We also return the value stored in the linked list element. 
 * If it is not, then we will return -1.
 * Therefore, the get operation will only take O(1) run time for each access.
 * 
 * Put operation
 * 
 * The put operation is more complicated than the get operation.
 * We first check whether the key we want to put into LRU cache is already in the data structure. 
 * If it is, then we only need to update its value and promote its priority. 
 * This can be done by calling the get operation. 
 * If it is not, we proceed to the next step.
 * Next up, we increment the capacity count and check whether the current capacity is greater than the overall capacity of the LRU cache. 
 * If it is, then we have to remove the least recently used element. 
 * How do we find which element is the least recently used element? 
 * That's where having the doubly linked list comes in. 
 * Since the doubly linked list's order represent the priority of usage, the last element in the doubly linked list is the least recently used one. 
 * We thus remove the last element in the doubly linked list and also remove it from the hash map by looking up 
 * the key attribute in the doubly linked list data structure.
 * Lastly, we need to create the new doubly linked list element and place it in the front of the doubly linked list and update our hash map.
 * All the operations done above will take O(1) run time for each access.
 */
public class LeastRecentlyUsed {

	public static class LRUCache {
		
        private static class DLL {
            public int key;
            public int val;
            public DLL next;
            public DLL prev;

            public DLL(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        public int capacity;
        public Map<Integer, DLL> cache;
        public DLL head;
        public DLL tail;
        public int size;

        public LRUCache(int capacity) {
            this.cache = new HashMap<>(capacity);
            this.head = new DLL(0, 0);
            this.tail = new DLL(0, 0);
            this.head.next = tail;
            this.tail.prev = head;
            this.size = 0;
            this.capacity = capacity;
        }

        public int get(int key) {
            if (this.cache.containsKey(key)) {
                DLL loc = this.cache.get(key);
                loc.prev.next = loc.next;
                loc.next.prev = loc.prev;
                this.head.next.prev = loc;
                loc.next = this.head.next;
                this.head.next = loc;
                loc.prev = this.head;
                return loc.val;
            }
            
            return -1;
        }

        public void put(int key, int value) {
            if (this.cache.containsKey(key)) {
                get(key);
                this.cache.get(key).val = value;
                return;
            }
            
            this.size++;
            
            if (this.size > this.capacity) {
                DLL lru = this.tail.prev;
                cache.remove(lru.key);
                this.tail.prev.val = this.tail.val;
                this.tail.prev.next = null;
                this.tail = this.tail.prev;
                this.size--;
            }
            
            DLL newHead = new DLL(key, value);
            this.head.next.prev = newHead;
            newHead.next = this.head.next;
            this.head.next = newHead;
            newHead.prev = this.head;
            cache.put(key, newHead);
        }
    }

    // 10
	// LRUCache put put get put get put get get get
	// 2
	// 1,1
	// 2,2
	// 1
	// 3,3
	// 2
	// 4,4
	// 1
	// 3
	// 4
	public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] operations = scanner.nextLine().split(" ");
        int[][] arr = new int[n][];
        
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }
        
        scanner.close();
        LRUCache lru = null;
        String[] res = new String[n];
        
        for (int i = 0; i < n; i++) {
            switch (operations[i]) {
                case "LRUCache":
                    lru = new LRUCache(arr[i][0]);
                    res[i] = "null";
                    break;
                case "get":
                    res[i] = Integer.toString(lru.get(arr[i][0]));
                    break;
                case "put":
                    lru.put(arr[i][0], arr[i][1]);
                    res[i] = "null";
                    break;
            }
        }
        
        System.out.println(String.join(" ", res));
    }
}