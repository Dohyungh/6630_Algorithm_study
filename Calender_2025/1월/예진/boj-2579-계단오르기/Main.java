/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 계단 오르기_2579
 * Date: 2025.01.28
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
		int[] stairs = new int[n];
		for (int i = 0; i < n; i++) stairs[i] = Integer.parseInt(br.readLine());

		if (n == 1) {
			bw.write(String.valueOf(stairs[0]));
		} else {
			int[][] dp = new int[n][2];
			dp[0][0] = stairs[0];
			dp[1][0] = stairs[0] + stairs[1];
			dp[1][1] = stairs[1];
			for (int i = 2; i < n; i++) {
				dp[i][0] = dp[i-1][1] + stairs[i];
				dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + stairs[i];
			}
			bw.write(String.valueOf(Math.max(dp[n-1][0], dp[n-1][1])));
		}

		bw.flush();
		bw.close();
		br.close();
	}
}