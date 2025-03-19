/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 퇴사_14501
 * Date: 2025.03.20
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

		int n = Integer.parseInt(br.readLine());
		int[] T = new int[n+1];
		int[] P = new int[n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			int end = i + T[i] - 1;
			for (int j = 1; j <= n; j++) {
				if (j < end) dp[i][j] = dp[i-1][j];
				else if (j == end) dp[i][j] = Math.max(dp[i-1][j], dp[i][j-T[i]] + P[i]);
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}

		bw.write(String.valueOf(dp[n][n]));
		bw.flush();
		bw.close();
		br.close();
	}
}