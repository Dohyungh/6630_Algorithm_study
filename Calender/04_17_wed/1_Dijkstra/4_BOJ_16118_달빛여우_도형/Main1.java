package BOJ._16118_달빛여우;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main1 {
	// 늑대 불쌍해
	
	// 다익스트라 돌릴 때 *2 /2 왔다갔다 하라는 거 같은데
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		double[][] adj = new double[N][N];
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt()-1;
			int to = sc.nextInt()-1;
			double d = sc.nextDouble();
			
			adj[from][to] = d;
			adj[to][from] = d;
		}
		
		// 최단거리 찾는 거임
		// 삥 돌아간게 더 빠를 수도 있네
		
		
		double[] distWolf = new double[N];
		Arrays.fill(distWolf, Double.MAX_VALUE);
		distWolf[0] = 0;
		
		boolean rest = false;
		int sz = 1;
		int nowNode = 0;
		boolean[] visited = new boolean[N];
		
		visited[0] = true;
		int cnt = 1;
		
	
		// 2개씩 묶어볼까?!
		// 홀수번만에 들어가는 건 어떻게??
		
		while(true) {
			
			List<Integer> adjNodes = new ArrayList<Integer> ();
			
			for (int i = 0; i < N; i++) {
				if (!visited[i] && adj[nowNode][i] != 0) {
					adjNodes.add(i);
				}
			}
			
			for (int node : adjNodes) {
				if (!rest) {
					for (int i = 0; i < N; i++) {
						if (adj[node][i] != 0 && !visited[i] && distWolf[i] > (adj[node][i] / 2.0 + distWolf[node])) {
							distWolf[i] = (adj[node][i] / 2.0) + distWolf[node];
						}
					}
					
				}
				if (rest) {
					for (int i = 0; i < N; i++) {
						if (adj[node][i] != 0 && !visited[i] && distWolf[i] > (adj[node][i] * 2.0 + distWolf[node])) {
							distWolf[i] = (adj[node][i] * 2.0) + distWolf[node];
						}
					}
				}
			}
			
			for (int node : adjNodes) {
				visited[node] = true;
				cnt++;
			}
			System.out.println(cnt);
			if (cnt == N-1) break;
			rest = !rest;
		}
		System.out.println(Arrays.toString(distWolf));
		
		
		
		
		 
	}

}
