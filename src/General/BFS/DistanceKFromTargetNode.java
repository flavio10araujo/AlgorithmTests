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
 * Given a binary tree, a target node, and a integer K, find all nodes whose depth (level) is K away from the target node's depth. 
 * Order of returned nodes does not matter.
 * 
 * For example:
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \   \
 * 4   5   6
 *  \   \
 *   7   8
 * K=1, target=6
 * 
 * Output: 2, 3, 7, 8.
 */
public class DistanceKFromTargetNode {
	
	public static List<Node> getDistanceKNodes(Node root, Node target, int k) {
        List<Node> res = new ArrayList<>();
        if (root != null) {
            int targetLevel = findTarget(root, target);
            bfs(root, targetLevel, k, res);
        }
        return res;
    }

    private static void bfs(Node root, int targetLevel, int k, List<Node> res) {
        int level = 0;
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.size() > 0) {
            int n = queue.size();
            level++;
            for (int i = 0; i < n; i++) {
                Node node = queue.pop();
                // found nodes K away from target
                if (Math.abs(targetLevel - level) == k) res.add(node);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
    }

    private static int findTarget(Node root, Node target) {
        int level = 0;
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.size() > 0) {
            int n = queue.size();
            level++;
            for (int i = 0; i < n; i++) {
                Node node = queue.pop();
                // early exit if found target
                if (node.equals(target)) return level;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return level;
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

        public static Node findNode(Node root, int target) {
            if (root == null || root.val == target) return root;
            Node leftSearch = findNode(root.left, target);
            if (leftSearch != null) return leftSearch;
            return findNode(root.right, target);
        }
    }

    public static void main(String[] args) throws IOException {
        Node root = Node.buildTree(Arrays.stream("1 2 4 x 7 x x 5 x 8 x x 3 x 6 x x".split(" ")).iterator());
        Node target = Node.findNode(root, Integer.parseInt("6"));
        int k = Integer.parseInt("1");
        List<Node> res = getDistanceKNodes(root, target, k);
        System.out.println(res.stream().map(node -> Integer.toString(node.val)).collect(Collectors.joining(" ")));
    }
}