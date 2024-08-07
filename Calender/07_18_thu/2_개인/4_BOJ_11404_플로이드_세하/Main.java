package boj_11404_플로이드;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int INF = 987654321;

		int n = Integer.parseInt(st.nextToken());
		int[][] graph = new int[n + 1][n + 1];

		for (int i = 1; i < n + 1; i++) {
			// 그래프의 모든 간선을 무한대로 초기화
			Arrays.fill(graph[i], INF);
			// 자기 자신은 0으로 초기화
			graph[i][i] = 0;
		}

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			// 간선이 여러개인 경우 최소값으로 입력
			graph[a][b] = Math.min(graph[a][b], w);
		}

		// k번 노드를 거쳐 가는 경우를
		for (int k = 1; k < n + 1; k++) {
			// 모든 노드에 대해 비교 실행
			for (int r = 1; r < n + 1; r++) {
				for (int c = 1; c < n + 1; c++) {
					graph[r][c] = Math.min(graph[r][c], graph[r][k] + graph[k][c]);
				}
			}
		}

		for (int r = 1; r < n + 1; r++) {
			for (int c = 1; c < n + 1; c++) {
				// r -> c 로 갈 수 없는 경우 0 출력
				if (graph[r][c] == INF)
					System.out.print("0 ");
				else
					System.out.print(graph[r][c] + " ");
			}
			System.out.println();
		}
	}
}
