package General;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-average-pass-ratio/
 * 
 * There is a school that has classes of students and each class will be having a final exam. 
 * You are given a 2D integer array classes, where classes[i] = [passi, totali]. 
 * You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.
 * 
 * You are also given an integer extraStudents. 
 * There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. 
 * You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.
 * 
 * The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. 
 * The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.
 * 
 * Return the maximum possible average pass ratio after assigning the extraStudents students. 
 * Answers within 10-5 of the actual answer will be accepted.
 * 
 * Example 1:
 * Input: classes = {{1,2},{3,5},{2,2}}, extraStudents = 2
 * Output: 0.78333
 * Explanation: 
 * 		You can assign the two extra students to the first class. 
 * 		The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
 * 
 * Input: classes = {{2,4},{3,9},{4,5},{2,10}}, extraStudents = 4
 * Output: 0.53485
 * 
 * Solution and Explanation:
 * 
 * Which class should an extra student go to?
 * We want to add an extra student to the class where he can make the most impact. 
 * Impact is measured as how much the average score the class can gain, 
 * i.e. average score after adding the student - average score before adding the student. 
 * We call this the "gain" by adding the student.
 * 
 * "Gain" has diminishing returns:
 * Imagine a class with two students: one passing the other one failing. 
 * The passing ratio is 50%. By adding another passing student the ratio becomes 2/3 = 66%. The gain is 66%-50%=16%. 
 * However, adding one more student the ratio is 3/4=75% with a 75%-16%=11% gain. 
 * Therefore the gain reduces as more students are added.
 * 
 * Since the gain of a class changes when we add a student to it, we have to keep track of the max gain as we add students. 
 * A good data structure for this is a max heap.
 * 
 * Greedy + Heap
 * To summarize, we use a max heap to keep track of the gains. 
 * While we have extra students, we pop the class with highest gain out of the heap, 
 * add the extra student to it and push it back into the max heap.
 * 
 * Complexity
 * Each time we pop from the heap it's O(log(n)) 
 * and with k students and a final loop to find the sum, the total is O(k log(n) + n).
 */
public class MaximumAveragePassRatio {

	static class OneClass implements Comparable<OneClass> {
        int studentsPass;
        int studentTotal;
        
        public OneClass(int studentsPass, int studentTotal) {
            this.studentsPass = studentsPass;
            this.studentTotal = studentTotal;
        }
        
        public double passRatio() {
            return (double) studentsPass / studentTotal;
        }
        
        public double gain() {
            return futureClass().passRatio() - passRatio();
        }
        
        public OneClass futureClass() {
            return new OneClass(studentsPass + 1, studentTotal + 1);
        }
        
        public int compareTo(OneClass other) {
            return -Double.compare(gain(), other.gain());
        }
    }
	
	public static double maxAverageRatio(int[][] classes, int extraStudents) {
        
		double newAveragePassRatio = 0;
		
		// Priority queue ordered by gain descending.
		Queue<OneClass> pq = new PriorityQueue<>();
		
		for (int i = 0; i < classes.length; i++) {
			pq.add(new OneClass(classes[i][0], classes[i][1]));
		}
		
		for (int i = 0; i < extraStudents; i++) {
			OneClass o = pq.poll();
			pq.add(new OneClass(o.studentsPass + 1, o.studentTotal + 1));
		}
		
		while (!pq.isEmpty()) {
			OneClass o = pq.poll();
			newAveragePassRatio += o.passRatio();
		}
		
		return newAveragePassRatio / classes.length;
    }
	
	public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		
		int[][] classes = {{1,2},{2,2},{3,5}};
		int extraStudents = 2;
		double res = maxAverageRatio(classes, extraStudents);
        System.out.println(res);
        
        long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}