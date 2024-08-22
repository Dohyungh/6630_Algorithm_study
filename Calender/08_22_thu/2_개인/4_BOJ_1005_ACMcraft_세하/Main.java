package boj_1005_ACMCraft;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			int[] D = new int[N + 1]; // 각 인덱스 번호에 해당하는 건물을 짓는데 걸리는 시간

			for (int i = 1; i <= N; i++) {
				D[i] = Integer.parseInt(st.nextToken());
			}

			int[][] adj = new int[N + 1][N + 1];
			int[] degree = new int[N + 1];
			int[] dp = new int[N + 1];

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());

				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				adj[A][B] = 1;
				degree[B]++; // B의 진입 차수 증가
			}

			Queue<Integer> queue = new LinkedList<>();

			// 먼저 dp 테이블을 해당 건물을 짓는데 걸리는 시간으로 초기화
			for (int i = 1; i <= N; i++) {
				dp[i] = D[i];
				// 진입 차수가 0이면 queue에 삽입
				if (degree[i] == 0) {
					queue.add(i);
				}
			}

			while (!queue.isEmpty()) {
				int curr = queue.poll();
				
				for (int i = 1; i <= N; i++) {
					// curr 에서 진출하는 간선에 대해
					if (adj[curr][i] == 1) {
						// 다음 노드(i)의 dp[i] 값에 (dp[curr] (현재 노드의 dp값) + D[i] (다음 노드 건물을 짓는 시간의 합))과 비교하여
						// 최댓값 저장
						dp[i] = Math.max(dp[i], dp[curr] + D[i]);
						// 진입 차수 1 감소 및 0이면 queue에 삽입
						if (--degree[i] == 0) {
							queue.add(i);
						}
					}
				}
			}
			System.out.println(dp[Integer.parseInt(br.readLine())]);
		}
	}
}
