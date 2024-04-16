package BOJ._16118_달빛여우;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main3 {
	// 늑대 불쌍해
	
	// 다익스트라 돌릴 때 *2 /2 왔다갔다 하라는 거 같은데
	
	static int[][] distWolf;
	static int[][] adj;
	static int N;
	
	static int[] distFox;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int M = sc.nextInt();
		
		adj = new int[N][N];
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt()-1;
			int to = sc.nextInt()-1;
			int d = sc.nextInt() * 2;
			
			adj[from][to] = d;
			adj[to][from] = d;
		}
		
		
		distWolf = new int[2][N]; // 홀수번 도착 / 짝수번 도착
		for (int i = 0; i < 2; i++) {
			Arrays.fill(distWolf[i], Integer.MAX_VALUE);
		}
		distWolf[0][0] = 0;
		distWolf[1][0] = Integer.MAX_VALUE;
				
		switchingDijkstra();
		
		distFox = new int[N];
		Arrays.fill(distFox, Integer.MAX_VALUE);
		distFox[0] = 0;
		visited = new boolean[N];
		Dijkstra();		
		System.out.println(Arrays.toString(distWolf[0]));
		System.out.println(Arrays.toString(distWolf[1]));
		System.out.println(Arrays.toString(distFox));
//		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (distFox[i] < Math.min(distWolf[0][i], distWolf[1][i]) && distFox[i] != Integer.MAX_VALUE) answer++;
		}
		System.out.println(answer);
	}
	
	// 홀수번만에 왔냐 짝수번만에 왔냐를 구분해야해
	// 이거 생각하는데 왜 이렇게 오래 걸렸냐
	public static void switchingDijkstra() {
		
		// 홀수번만에 온 애 다음 애의 짝수번을 업데이트하고
		// 짝수번만에 온 애 다음 애의 홀수번을 업데이트하는 식으로.
		
		boolean[][] visited = new boolean[2][N];
		
//		for (int i = 1; i <= 2 * (N-1); i++) {// 로 했다가 틀림!
		for (int i = 1; i <= 2 * (N+1); i++) {
			
			int min = Integer.MAX_VALUE;
			int minIdx = -1;
			int lookUp = -1;
			
			for (int n = 0; n < 2; n++) {
				for (int j = 0; j < N; j++) {
					if (distWolf[n][j] < min && !visited[n][j]) {
						min = distWolf[n][j];
						minIdx = j;
						lookUp = n;
					}
					
				}
			}
			if (minIdx == -1) break;
			
			int update = 1 - lookUp;
			
//			System.out.println("minIdx : " + minIdx);
			for (int j = 0; j < N; j++) {
				if (update == 0) {
					if (adj[minIdx][j] != 0 && distWolf[update][j] > distWolf[lookUp][minIdx] + adj[minIdx][j]*2) {
						distWolf[update][j] = distWolf[lookUp][minIdx] + adj[minIdx][j]*2;
					}
				}
				if (update == 1) {
					if (adj[minIdx][j] != 0 && distWolf[update][j] > distWolf[lookUp][minIdx] + adj[minIdx][j]/2) {
						distWolf[update][j] = distWolf[lookUp][minIdx] + adj[minIdx][j]/2;
					}
				}
			}
//			System.out.println(Arrays.toString(distWolf[0]));
//			System.out.println(Arrays.toString(distWolf[1]));
			visited[lookUp][minIdx] = true;
		}
	}
	
	public static void Dijkstra() {
		for (int n = 0; n < N; n++) {
			
			int minIdx = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < distFox.length; i++) {
				if (distFox[i] < min && !visited[i]) {
					min = distFox[i];
					minIdx = i;
				}
			}
			
			if (minIdx == -1) continue;
			
			for (int i = 0; i < N; i++) {
				if (adj[minIdx][i] != 0 && !visited[i] && distFox[minIdx] + adj[minIdx][i]  < distFox[i]) {
					distFox[i] = distFox[minIdx] + (int) adj[minIdx][i];
				}
			}
			visited[minIdx] = true;
		}
	}
	
}