package boj_9461_파도반수열;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			long[] dp = new long[N + 1];

			for (int i = 1; i <= N; i++) {
				if (i == 1) {
					dp[i] = 1;
				} else if (i == 2) {
					dp[i] = 1;
				} else {
					dp[i] = dp[i - 3] + dp[i - 2];
				}
			}

			sb.append(dp[N] + "\n");
		}

		System.out.println(sb);
	}
}
