/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 파일 합치기_11066
 * Date: 2025.03.01
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

			int n = Integer.parseInt(br.readLine());
			int[] preFiles = new int[n+1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= n; i++) preFiles[i] = Integer.parseInt(st.nextToken()) + preFiles[i-1];

			int[][] dp = new int[n+1][n+1];

			for (int gap = 1; gap < n; gap++) {
				for (int start = 1; start <= n - gap; start++) {
					int end = start + gap;
					dp[start][end] = Integer.MAX_VALUE;

					for (int via = start; via < end; via++) {
						dp[start][end] = Math.min(dp[start][end], dp[start][via] + dp[via+1][end]);
					}
					dp[start][end] += + preFiles[end] - preFiles[start-1];
				}
			}
			bw.write(String.valueOf(dp[1][n]) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}