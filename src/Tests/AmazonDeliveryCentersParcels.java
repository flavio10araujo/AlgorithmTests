package Tests;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Temos n delivery centers.
 * Cada um com parcels[i] produtos.
 * Cada dia podemos retirar uma mesma quantidade de produtos de cada delivery center.
 * 
 * Retorne o mínimo de dias necessário para esvaziar todos os delivery centers.
 * 
 * Exemplo:
 * input: parcels[2,3,4,3,3]
 * ouput: 3
 * explanation:
 * 		Dia 01: retiramos 2 produtos de cada delivery center.
 * 				Ficamos com parcels[0,1,2,1,1]
 * 		Dia 02: retiramos 1 produto de cada delivery center.
 * 				Ficamos com parcels[0,0,1,0,0].
 * 		Dia 03: retiramos 1 produto de cada delivery center.
 * 				Ficamos com parcels[0,0,0,0,0].
 */
public class AmazonDeliveryCentersParcels {


	public static void main(String[] args) {
		System.out.println(solution01(List.of(2,3,4,3,3)));
		System.out.println(solution01(List.of(3,3,3,3,3)));
		System.out.println(solution01(null));
		System.out.println(solution01(new ArrayList<>()));
		System.out.println(solution01(List.of(8)));
		System.out.println(solution01(List.of(1,2,3,4,5)));
		System.out.println(solution01(List.of(0)));
	}

	public static int solution01(List<Integer> parcels) {
		if (parcels == null) {
			return 0;
		}

		int minDays = 0;
		int minQtd = 0;
		Queue<Integer> heap = new PriorityQueue<>();

		for (Integer i : parcels) {
			heap.add(i);
		}

		while(!heap.isEmpty()) {
			minQtd = heap.peek();

			if (minQtd == 0) {
				heap.poll();
				continue;
			}

			minDays++;

			while(!heap.isEmpty() && heap.peek() == minQtd) {
				heap.poll();
			}
		}

		return minDays;
	}
}