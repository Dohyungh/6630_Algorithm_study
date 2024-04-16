package boj_22865_가장먼곳;

import java.util.Scanner;


// 인접 행렬 & 반복문 사용
public class Main_memory_problem {

	static int N;
	static int[] friends;
	static int M;
	static int[][] adj;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		friends = new int[3];

		friends[0] = sc.nextInt();
		friends[1] = sc.nextInt();
		friends[2] = sc.nextInt();

		M = sc.nextInt();

		adj = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {

			int st = sc.nextInt();
			int ed = sc.nextInt();

			int cost = sc.nextInt();

			adj[st][ed] = cost;
			adj[ed][st] = cost;

		}

		int[] distA = new int[N + 1];
		int[] distB = new int[N + 1];
		int[] distC = new int[N + 1];

		for (int i = 0; i < N + 1; i++) {
			distA[i] = Integer.MAX_VALUE;
			distB[i] = Integer.MAX_VALUE;
			distC[i] = Integer.MAX_VALUE;
		}

		distA = dijkstra(friends[0], distA);
		distB = dijkstra(friends[1], distB);
		distC = dijkstra(friends[2], distC);

		int maxMin = Integer.MIN_VALUE;
		int idx = -1;

		for (int i = 1; i < N + 1; i++) {
			if (i == friends[0] || i == friends[1] || i == friends[2]) {
				continue;
			}

			int min = Math.min(Math.min(distA[i], distB[i]), distC[i]);

			if (min > maxMin) {
				maxMin = min;
				idx = i;
			}

		}
		System.out.println(idx);

	}

	public static int[] dijkstra(int start, int[] dist) {

		boolean[] visited = new boolean[N + 1];

		dist[start] = 0;

		for (int i = 0; i < N + 1; i++) {

			int min = Integer.MAX_VALUE;
			int idx = -1;

			for (int j = 0; j < N + 1; j++) {
				if (!visited[j] && min > dist[j]) {
					min = dist[j];
					idx = j;
				}
			}

			if (idx == -1) {
				break;
			}

			visited[idx] = true;

			for (int c = 0; c < N + 1; c++) {
				if (!visited[c] && adj[idx][c] > 0 && dist[idx] + adj[idx][c] < dist[c]) {
					dist[c] = dist[idx] + adj[idx][c];
				}
			}

		}

		return dist;

	}

}
