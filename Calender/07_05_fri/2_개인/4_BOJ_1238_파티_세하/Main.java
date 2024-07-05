package boj_1238_파티;

import java.io.*;
import java.util.*;

public class Main {
	static class Info {
		int node, time;

		public Info(int node, int time) {
			this.node = node;
			this.time = time;
		}
	}

	static int N, M, X, min, max;
	static int[] t, sum;
	static boolean[] visited;
	static List<Info>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];

		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<Info>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());

			adj[A].add(new Info(B, T));
		}

		// n번째 학생이 오고가는 시간을 저장할 배열
		sum = new int[N + 1];

		// 1~N번까지 dijkstra 실행
		for (int i = 1; i < N + 1; i++) {
			dijkstra(i);
		}

		max = Integer.MIN_VALUE;

		// 소요 시간이 가장 긴 값 출력
		for (int i = 1; i < N + 1; i++) {
			max = Math.max(sum[i], max);
		}

		System.out.println(max);
	}

	public static void dijkstra(int v) {
		visited = new boolean[N + 1];

		t = new int[N + 1];
		min = Integer.MAX_VALUE;
		Arrays.fill(t, min);
		t[v] = 0;

		// dijkstra
		for (int i = 0; i < N; i++) {
			min = Integer.MAX_VALUE;
			int idx = -1;

			for (int j = 1; j < N + 1; j++) {
				if (!visited[j]) {
					min = Math.min(min, t[j]);
					if (t[j] == min)
						idx = j;
				}
			}

			if (idx == -1)
				break;

			visited[idx] = true;

			// 시작 지점이 X가 아니고, X를 방문했으면 v->X의 최단 시간이 계산된 것이므로 종료
			if (v != X && visited[X])
				break;

			for (Info curr : adj[idx]) {
				if (!visited[curr.node] && t[curr.node] > t[idx] + curr.time) {
					t[curr.node] = t[idx] + curr.time;
				}
			}
		}

		// 시작 지점이 X이면 X->v 까지의 최단 소요시간이므로 배열을 순회하며 sum에 더한다.
		if (v == X) {
			for (int i = 1; i < N + 1; i++) {
				sum[i] += t[i];
			}
		}
		// 시작 지점이 X가 아니면 v->X까지의 최단 소요시간만 더한다.
		else {
			sum[v] += t[X];
		}
	}
}