/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 스티커_9465
 * Date: 2024.12.17
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
		
		int t = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] sticker = new int[2][n];
			int[][] dp = new int[2][n];

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp[0][0] = sticker[0][0];
			dp[1][0] = sticker[1][0];
			for (int i = 1; i < n; i++) {
				dp[0][i] = dp[1][i-1] + sticker[0][i];
				dp[1][i] = dp[0][i-1] + sticker[1][i];

				if (i >= 2) {
					int max = Math.max(dp[0][i-2], dp[1][i-2]);
					dp[0][i] = Math.max(dp[0][i], max + sticker[0][i]);
					dp[1][i] = Math.max(dp[1][i], max + sticker[1][i]);
				}
			}

			int answer = Math.max(dp[0][n-1], dp[1][n-1]);
			bw.write(String.valueOf(answer) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}