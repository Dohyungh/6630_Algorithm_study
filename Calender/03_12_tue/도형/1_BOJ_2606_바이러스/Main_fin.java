package BOJ._2606_바이러스;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_fin {
	static int answer = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		boolean[][] adj = new boolean[N+1][N+1];
		
		for (int i = 0; i < K; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			adj[from][to] = true;
			adj[to][from] = true;
			
		}

		boolean[] visited = new boolean[N+1];
		int start = 1;

		BFS(start, visited, adj, N);
		System.out.println(answer);
		
		DFS(start, visited, adj, N);
		System.out.println(answer-1);
		
		
	}
	
	public static void BFS(int start, boolean[] visited, boolean[][] adj, int N) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(start);
		
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int nowNode = queue.poll();
			
			for (int i = 1; i < N+1; i++) {
				if (!visited[i] && adj[nowNode][i]) {
					queue.add(i);
					answer++;
					visited[i] = true;
				}
			}
		}
		
	}
	
	public static void DFS(int nowNode, boolean[] visited, boolean[][] adj, int N) {
		answer++;
		
		visited[nowNode] = true;
		
		for (int i = 1; i < N+1; i++) {
			if (!visited[i] && adj[nowNode][i]) {
				DFS(i, visited, adj, N);
			}
		}
		return;
	}

}
