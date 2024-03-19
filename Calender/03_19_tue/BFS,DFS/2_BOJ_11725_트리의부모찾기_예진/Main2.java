package boj_11725_트리의부모찾기;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 硫붾え由� 珥덇낵
public class Main2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();		// �끂�뱶�쓽 媛쒖닔
		
		boolean[] visited = new boolean[N+1];
		boolean[][] connection = new boolean[N+1][N+1];
		int[] parentMap = new int[N+1];
		
		for (int i = 0; i < N-1; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			
			connection[num1][num2] = true;
			connection[num2][num1] = true;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		visited[1] = true;
		
		while (!queue.isEmpty()) {
			int nodeNum = queue.poll();

			for (int i = 1; i <= N; i++) {
				if (connection[nodeNum][i] && !visited[i]) {
					queue.add(i);
					visited[i] = true;
					parentMap[i] = nodeNum;
				}
			}
		}
		
		for (int i = 2; i <= N; i++) {
			System.out.println(parentMap[i]);
		}
		sc.close();
	}
}
