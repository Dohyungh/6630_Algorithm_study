package boj_11727_2xn타일링2;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			if (i == 1) {
				dp[1] = 1;
			} else if (i == 2) {
				dp[2] = 3;
			} else {
				dp[i] = (dp[i - 1] + dp[i - 2] * 2);

				if (dp[i] >= 10007) {
					dp[i] = dp[i] % 10007;
				}
			}
		}

		System.out.println(dp[N]);
	}
}
