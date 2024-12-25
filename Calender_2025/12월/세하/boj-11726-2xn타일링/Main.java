package boj_11726_2xn타일링;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			if (i == 1) {
				dp[i] = 1;
			} else if (i == 2) {
				dp[i] = 2;
			} else {
				dp[i] = (dp[i - 2] + dp[i - 1]);
				
				// 10007보다 커지면 나누기 
				if (dp[i] >= 10007) {
					dp[i] = dp[i] % 10007;
				}
			}
		}

		System.out.println(dp[N]);
	}
}
