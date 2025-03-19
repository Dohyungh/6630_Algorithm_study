/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 이친수_2193
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
		long[] dp = new long[n+1];
		dp[1] = 1;
		for (int i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];

		bw.write(String.valueOf(dp[n]));
		bw.flush();
		bw.close();
		br.close();
	}
}