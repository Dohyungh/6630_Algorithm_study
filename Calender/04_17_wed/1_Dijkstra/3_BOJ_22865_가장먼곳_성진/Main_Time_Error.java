package boj_22865_가장먼곳;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// 인접리스트 & 반복문 사용
public class Main_Time_Error {

	static class Node {
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static int N;
	static int A;
	static int B;
	static int C;

	static List<Node>[] adjList;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();

		int M = sc.nextInt();

		adjList = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {

			int st = sc.nextInt();

			int ed = sc.nextInt();
			int w = sc.nextInt();

			adjList[st].add(new Node(ed, w));
			adjList[ed].add(new Node(st, w));
		}

		int[] distA = new int[N + 1];
		int[] distB = new int[N + 1];
		int[] distC = new int[N + 1];

		Arrays.fill(distA, Integer.MAX_VALUE);
		Arrays.fill(distB, Integer.MAX_VALUE);
		Arrays.fill(distC, Integer.MAX_VALUE);

		distA = dijkstra(A, distA);
		distB = dijkstra(B, distB);
		distC = dijkstra(C, distC);

		int maxMin = Integer.MIN_VALUE;
		int maxIdx = -1;

		for (int i = 1; i < N + 1; i++) {
			if (i == A || i == B || i == C) {
				continue;
			}
			int min = Math.min(Math.min(distA[i], distB[i]), distC[i]);
			if (min > maxMin) {
				maxMin = min;
				maxIdx = i;
			}
		}

		System.out.println(maxIdx);

	}

	public static int[] dijkstra(int start, int[] dist) {

		boolean[] visited = new boolean[N + 1];

		dist[start] = 0;

		for (int i = 0; i < N; i++) {

			int min = Integer.MAX_VALUE;
			int idx = -1;

			for (int j = 0; j < N + 1; j++) {
				if (!visited[j] && dist[j] < min) {
					min = dist[j];
					idx = j;
				}
			}

			if (idx == -1) {
				break;
			}

			visited[idx] = true;

			for (Node node : adjList[idx]) {
				if (!visited[node.v] && dist[idx] + node.w < dist[node.v]) {
					dist[node.v] = dist[idx] + node.w;
				}
			}

		}

		return dist;

	}

}
