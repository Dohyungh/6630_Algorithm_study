package boj_1260_DFS와BFS;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {

	static int N;	// 노드의 수: N
	static int M;	// 간선의 수: M
	static int start;	// 시작 노드
	static LinkedList<Integer>[] graph;	// 그래프의 연결을 연결 리스트로 표현

	static boolean[] visited;	// 해당 노드를 방문했는가?
	static Queue<Integer> queue;	// bfs를 위한 queue 구현

	
	
	public static void main(String[] args) {
		

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		start = sc.nextInt();

		// 노드 번호 N을 표현하기 위해 행렬의 사이즈는 (N+1) x (N+1)
		graph = new LinkedList[N+1];
		

		for (int i = 0; i < N+1; i++) {
			graph[i] = new LinkedList<Integer>();
		}
		
		
		for (int i = 0; i < M; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();
			graph[node1].add(node2);
			graph[node2].add(node1);
		}
		
		for (int i = 0; i < N+1; i++) {
		    Collections.sort(graph[i]);
		}

		visited = new boolean[N+1];
		dfs(start);

		System.out.println();
		
		visited = new boolean[N+1];
		bfs(start);
		
		sc.close();
		
	}
	
	
	static void dfs(int node) {
		
		visited[node] = true;
		System.out.print(node + " ");

		for (int i=0; i<graph[node].size(); i++) {
			if (!visited[graph[node].get(i)]) {
				dfs(graph[node].get(i));
			}
		}
		
	}
	
	
	
	
	
	static void bfs(int start) {
		
		queue = new LinkedList<>();
		
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll(); 
			System.out.print(node + " ");

			for(int next : graph[node]) {
				if(!visited[next]) { 
					queue.add(next);
					visited[next] = true;
				}
			}
		}		
	}
	
	
	
	

}
