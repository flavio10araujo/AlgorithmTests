package General.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Given a ternary tree (each node of the tree has at most three children), find all root-to-leaf paths.
 * 
 * Input:
 * //        1
 * //       /|\
 * //      2 4 6
 * //     / 
 * //    3   
 * Output: 
 * [(1, 2, 3), (1, 4), (1, 6)]
 */
public class TernaryTreePaths {

	static int index2 = 0;
	
	public static String[] ternaryTreePaths(Node root) {
        ArrayList<String> res = new ArrayList<>();
        
        if (root != null) {
        	dfs(root, new ArrayList<>(), res);
        }
        
        return res.toArray(new String[res.size()]);
    }
	
	private static void dfs(Node root, ArrayList<String> path, ArrayList<String> res) {
        // exit condition, reached leaf node, append paths to results
        if (root.children[0] == null && root.children[1] == null && root.children[2] == null) {
            path.add(Integer.toString(root.val));
            res.add(String.join("->", path));
            path.remove(path.size() - 1);
            return;
        }

        // dfs on each non-null child
        for (Node child : root.children) {
            if (child != null) {
                path.add(Integer.toString(root.val));
                dfs(child, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    /** Driver class, do not change **/
    static class Node {
        int val;
        Node[] children;

        public Node(int val, Node[] children) {
            this.val = val;
            this.children = children;
        }

        public static Node buildTree(Iterator<String> nodes) {
            String nxt = nodes.next();
            if (nxt.equals("x")) return null;
            Node node = new Node(Integer.parseInt(nxt), new Node[3]);
            for (int i = 0; i < 3; i++) {
                node.children[i] = buildTree(nodes);
            }
            return node;
        }
    }
    
    public static void main(String[] args) {
        //Node root = Node.buildTree(Arrays.stream("1 2 3 x x x x x 4 x x x 6 x x x".split(" ")).iterator());
    	//Node root = Node.buildTree(Arrays.stream("1 2 3 x x x x x 4 x x x 6 7 x x x 8 x x x x".split(" ")).iterator());
    	//Node root = Node.buildTree(Arrays.stream("x".split(" ")).iterator());
    	Node root = Node.buildTree(Arrays.stream("1 2 3 x x x 4 x x x 7 x x x 5 x x x 6 x x x".split(" ")).iterator());
        
        String[] paths = ternaryTreePaths(root);
        for (String path : paths) {
            System.out.println(path);
        }
    }
}