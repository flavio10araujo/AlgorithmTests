package General.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

/**
 * Given a binary tree, write a serialize function that converts the tree into a string and a deserialize function that converts a string to a binary tree. 
 * You may serialize the tree into any string representation you want as long as it can be deseralized.
 */
public class SerializingDeserializingBinaryTree {

	public static String serialize(Node root) {
		StringJoiner res = new StringJoiner(" ");
        serializeDFS(root, res);
        return res.toString();
    }
	
	private static void serializeDFS(Node root, StringJoiner result) {
        if (root == null) {
            result.add("x");
            return;
        }
        
        result.add(Integer.toString(root.val));
        serializeDFS(root.left, result);
        serializeDFS(root.right, result);
    }

    public static Node deserialize(String root) {
    	// Create an iterator that returns a token each time we call `next`.
        return deserializeDFS(Arrays.stream(root.split(" ")).iterator());
    }
    
    private static Node deserializeDFS(Iterator<String> nodes) {
        String val = nodes.next();
        
        if (val.equals("x")) return null;
        
        Node cur = new Node(Integer.parseInt(val));
        cur.left = deserializeDFS(nodes);
        cur.right = deserializeDFS(nodes);
        return cur;
    }

    /** Driver class, do not change **/
    static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }

        public static Node buildTree(Iterator<String> iter) {
            String nxt = iter.next();
            if (nxt.equals("x")) return null;
            Node node = new Node(Integer.parseInt(nxt));
            node.left = buildTree(iter);
            node.right = buildTree(iter);
            return node;
        }

        public static void printTree(Node root, List<String> out) {
            if (root == null) {
                out.add("x");
            } else {
                out.add(String.valueOf(root.val));
                printTree(root.left, out);
                printTree(root.right, out);
            }
        }
    }

    public static void main(String[] args) {
        String entrada = "5 4 3 x x 8 x x 6 x x";
        Node root = Node.buildTree(Arrays.stream(entrada.split(" ")).iterator());
        
        Node newRoot = deserialize(serialize(root));
        ArrayList<String> out = new ArrayList<>();
        Node.printTree(newRoot, out);
        System.out.println(String.join(" ", out));
    }
}