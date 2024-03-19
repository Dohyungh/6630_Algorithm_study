package boj_11725_트리의부모찾기;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		
		List<Integer>[] nodes = new List[N+1];
		
		
		boolean[] visited = new boolean[N+1];
		
		
		int[] parentMap = new int[N+1];
		
		
		for (int i = 0; i < N-1; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			
			
			if (nodes[num1] == null) {
				nodes[num1] = new ArrayList<>();
			}
			if (nodes[num2] == null) {
				nodes[num2] = new ArrayList<>();
			}
			nodes[num1].add(num2);
			nodes[num2].add(num1);
		}
		
		
		Queue<Integer> queue = new LinkedList<>();
		
		
		queue.add(1);
		visited[1] = true;
		
		
		while (!queue.isEmpty()) {
			
			
			int nodeNum = queue.poll();
			
			
			if (nodes[nodeNum] != null) {
				for (int i : nodes[nodeNum]) {
					if (!visited[i]) {
						queue.add(i);
						visited[i] = true;
						
						
						parentMap[i] = nodeNum;
					}
				}
			}
		}
		
		
		for (int i = 2; i <= N; i++) {
			System.out.println(parentMap[i]);
		}
		
		
		sc.close();
	}

}
