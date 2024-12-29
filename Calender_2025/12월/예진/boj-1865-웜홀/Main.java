/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 웜홀_1865
 * Date: 2024.12.29
 * Time: 1828 ms
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {

			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);		// 지점의 개수
			int m = Integer.parseInt(input[1]);		// 도로의 개수
			int w = Integer.parseInt(input[2]);		// 웜홀의 개수

			// 인접행렬 초기화
			int[][] adj = new int[n+1][n+1];
			for (int i = 0; i <= n; i++) {
				Arrays.fill(adj[i], 1_000_000_000);
			}

			// 도로 입력
			for (int i = 0; i < m; i++) {
				String[] info = br.readLine().split(" ");
				int a = Integer.parseInt(info[0]);
				int b = Integer.parseInt(info[1]);
				int t = Integer.parseInt(info[2]);

				adj[a][b] = Math.min(adj[a][b], t);
				adj[b][a] = Math.min(adj[b][a], t);
			}

			// 웜홀 입력
			for (int i = 0; i < w; i++) {
				String[] info = br.readLine().split(" ");
				int start = Integer.parseInt(info[0]);
				int end = Integer.parseInt(info[1]);
				int t = Integer.parseInt(info[2]);

				adj[start][end] = Math.min(adj[start][end], -t);
			}

			boolean flag = false;
			out: for (int v = 1; v <= n; v++) {
				for (int s = 1; s <= n; s++) {
					if (v == s) continue;
					for (int e = 1; e <= n; e++) {
						if (v == e) continue;
						adj[s][e] = Math.min(adj[s][e], adj[s][v] + adj[v][e]);
						if (s == e && adj[s][e] < 0) {
							flag = true;
							break out;
						}
					}
				}
			}

			if (flag) bw.write("YES\n");
			else bw.write("NO\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}