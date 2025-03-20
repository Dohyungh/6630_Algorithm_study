/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 동물원_1309
 * Date: 2025.03.20
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static final int MOD = 9901;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][3];
        dp[0][0] = dp[0][1] = dp[0][2] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % MOD;
        }

        int answer = (dp[n-1][0] + dp[n-1][1] + dp[n-1][2]) % MOD;
        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}