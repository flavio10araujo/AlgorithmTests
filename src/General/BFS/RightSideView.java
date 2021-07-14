package General.BFS;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return the rightmost node of each level.
 * 
 * For example:
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \   \
 * 4   5   6
 *  \
 *   7
 * Output:
 * [1, 3, 6, 7]
 * 
 */
public class RightSideView {
	
	public static List<Node> getRightSideView(Node root) {
    
        List<Node> res = new ArrayList<Node>();
        
        if (root ==  null) {
            return res;
        }
        
        Deque<Node> queue = new ArrayDeque<Node>();
        
        queue.add(root);
        
        while (queue.size() > 0) {
            
            int n = queue.size();
            Node lastElement = null;
            
            for (int i = 0; i < n; i++) {
                
                Node node = queue.pop();
                
                if (node.left != null) {
                    queue.add(node.left);
                }
                
                if (node.right != null) {
                    queue.add(node.right);
                }
                
                lastElement = node;
            }
            
            res.add(lastElement);
        }
        
        return res;
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
    }
    
    public static void main(String[] args) throws IOException {
        Node root = Node.buildTree(Arrays.stream("1 2 4 x 7 x x 5 x x 3 x 6 x x".split(" ")).iterator());
        List<Node> res = getRightSideView(root); 
        System.out.println(res.stream().map(node -> Integer.toString(node.val)).collect(Collectors.joining(" ")));
    }
}