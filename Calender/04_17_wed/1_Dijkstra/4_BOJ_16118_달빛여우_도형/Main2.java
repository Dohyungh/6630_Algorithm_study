package BOJ._16118_달빛여우;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main2 {
	// 늑대 불쌍해
	
	// 다익스트라 돌릴 때 *2 /2 왔다갔다 하라는 거 같은데
	
	static double[] dfs;
	static double[][] adj;
	static int N;
	
	static int[] dist;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int M = sc.nextInt();
		
		adj = new double[N][N];
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt()-1;
			int to = sc.nextInt()-1;
			double d = sc.nextDouble();
			
			adj[from][to] = d;
			adj[to][from] = d;
		}
		
		// DFS + DP
		dfs = new double[N];
		Arrays.fill(dfs, Double.MAX_VALUE);
		dfs[0] = 0.0;
				
		DFS(0,0,false, 1);
		
		dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		visited = new boolean[N];
		Dijkstra();
		
//		System.out.println(Arrays.toString(dfs));
//		System.out.println(Arrays.toString(dist));
		
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (dist[i] < dfs[i]) answer++;
		}
		System.out.println(answer);
		
		
		
		
		
	}
	
	// 시간초과
	public static void DFS(int nowNode, double dist, boolean rest, int depth) {
		
		if (depth >= N) return;
		
		if (dfs[nowNode] > dist) dfs[nowNode] = dist;
		
		for (int i = 0; i < N; i++) {
			if (adj[nowNode][i] != 0) {
				if (!rest) {
					DFS(i,dist+(adj[nowNode][i])/2.0, !rest, depth+1);
				}
				if (rest ) {
					DFS(i,dist+(adj[nowNode][i]*2.0), !rest, depth+1);
				}
			}
		}
	}
	
	public static void Dijkstra() {
		for (int n = 0; n < N-1; n++) {
			
			int minIdx = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < dist.length; i++) {
				if (dist[i] < min && !visited[i]) {
					min = dist[i];
					minIdx = i;
				}
				
			}
			
			if (minIdx == -1) break;
			
			for (int i = 0; i < N; i++) {
				if (adj[minIdx][i] != 0 && !visited[i] && dist[minIdx] + adj[minIdx][i]  < dist[i]) {
					dist[i] = dist[minIdx] + (int) adj[minIdx][i];
				}
			}
			visited[minIdx] = true;
			
			
			
		}
		
		
		
	}
	
}