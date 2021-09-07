package General;

/**
 * Given a binary tree and a key(node) value, find the floor and ceil value for that particular key value.
 * 
 * Floor Value Node: Node with the greatest data lesser than or equal to the key value. 
 * Ceil Value Node: Node with the smallest data larger than or equal to the key value.
 * 
 * Example:
 * 
 *         8
 *       /   \
 *      4     12
 *     / \    / \
 *    2   6  10 14
 *    
 * Key: 1   Floor: -1  Ceil: 2
 * Key: 6   Floor: 6   Ceil: 6
 * Key: 11  Floor: 10  Ceil: 12
 * Key: 15  Floor: 14  Ceil: -1
 * 
 * There are numerous applications where we need to find the floor/ceil value of a key in a binary search tree or sorted array. 
 * For example, consider designing a memory management system in which free nodes are arranged in BST. 
 * Find the best fit for the input request.
 */
public class FloorAndCeilFromABST {

	Node root;
	
	public static void main(String[] args) {
		FloorAndCeilFromABST tree = new FloorAndCeilFromABST();
        
        tree.root = new Node(8);
        tree.root.left = new Node(4);
        tree.root.right = new Node(12);
        tree.root.left.left = new Node(2);
        tree.root.left.right = new Node(6);
        tree.root.right.left = new Node(10);
        tree.root.right.right = new Node(14);
        
        for (int i = 0; i < 16; i++) { 
        	FloorAndCeilFromABST.floorCeilBST(tree.root, i);
        }
    }

	public static void floorCeilBST(Node root, int key) {
		int floor = (root.data >= key ? key : root.data);
		int ceil = (root.data <= key ? key : root.data);
		
		if (floor == ceil) {
			System.out.println("K=" + key + " Floor=" + floor + " Ceil=" + ceil);
		}
		
		findFloorCeil(root, key, floor, ceil);
	}
	
	public static void findFloorCeil(Node root, int key, int floor, int ceil) {
		if (root == null) {
			if (floor == key) {
				floor = -1;
			} else if (ceil == key) {
				ceil = -1;
			}
			
			System.out.println("K=" + key + " Floor=" + floor + " Ceil=" + ceil);
			return;
		}
		
		if (key == root.data) {
			System.out.println("K=" + key + " Floor=" + root.data + " Ceil=" + root.data);
			return;
		} else if (key < root.data) {
			ceil = root.data;
			findFloorCeil(root.left, key, floor, ceil);
		} else if (key > root.data) {
			floor = root.data;
			findFloorCeil(root.right, key, floor, ceil);
		}
		
		return;
	}
}

class Node {
    int data;
    Node left, right;
 
    Node(int d) {
        data = d;
        left = right = null;
    }
}