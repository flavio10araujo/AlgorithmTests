package General.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
        
		List<String> lista = new ArrayList<String>();
		
		if (root != null) {
			lista.add(root.val+ " ");
			lista = printLala(root, lista, 0, "");
		}
		
		//System.out.println(lista.toString());
		
		String[] retorno = new String[lista.size()];
		
		for (int i = 0; i < retorno.length; i++) {
			retorno[i] = lista.get(i);
		}
		
		return retorno;
    }
	
	public static List<String> printLala(Node node, List<String> lista, int index, String s) {
		if (node == null) {
			return lista;
		}
		
		String s1 = "";
		
		if ("".equals(s)) {
			s1 = node.val + "";
		} else {
			s1 = s + "->" + node.val;
		}
		
		System.out.println("s1="+s1+" index="+index+" index2="+index2);
		
		lista.set(index2, s1);
		lista = printLala(node.children[0], lista, index, s1);
		
		if (node.children[1] != null) {
			lista.add(s1);
			index2++;
			lista = printLala(node.children[1], lista, index++, s1);
		}
		
		if (node.children[2] != null) {
			lista.add(s1);
			index2++;
			lista = printLala(node.children[2], lista, index++, s1);
		}
		
		return lista;
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