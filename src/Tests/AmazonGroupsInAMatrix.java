package Tests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Temos uma matrix que representa os relacionamentos entre entidades.
 * 
 * Se o valor matrix[i][j] é 0, não há relacionamento entre as entidades.
 * Se o valor é 1, há relacionamento.
 * 
 * Retorne o número de grupos independentes existentes na matrix.
 * 
 * Examplo:
 * input: lignes={"110000", "110001", "001100", "001100", "000010", "010001"}
 * output: 3
 * explanation:
 * 		Baseado na matrix formada pelas lista de linhas, sabemos:
 * 			- existem 6 entidades (iremos chamá-las de 0, 1, 2, 3, 4, 5);
 * 			- os relacionamentos dessas entidades são:
 * 				{{0,1}; {1,0}; {1,5}; {2,3}; {3,2}; {5,1}}
 * 		Ou seja, temos os seguintes grupos independentes:
 * 			Grupo 1: 0,1,5
 * 			Grupo 2: 2,3
 * 			Grupo 3: 4
 */
public class AmazonGroupsInAMatrix {
	public static void main(String[] args) {
		List<String> lignes = List.of(
				"110000",
				"110001",
				"001100",
				"001100",
				"000010",
				"010001");
		
		System.out.println(solution01(lignes));
 	}
	
	public static int solution01(List<String> lignes) {
		int counter = 0;
		
		Set<Integer> used = new HashSet<>();
		
		for (int i = 0; i < lignes.size(); i++) {
			if (!used.contains(i)) {
				counter++;
				used.add(i);
				
				dfs(lignes, i, used);
			}
		}
		
		return counter;
	}
	
	private static void dfs(List<String> lignes, int ligne, Set<Integer> used) {
		for (int j = 0; j < lignes.get(ligne).length(); j++) {
			if (j == ligne || lignes.get(ligne).charAt(j) == '0' || used.contains(j)) {
				continue;
			}
			
			used.add(j);
			
			dfs(lignes, j, used);
		}
	}
}