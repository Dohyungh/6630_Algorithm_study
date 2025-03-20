/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 파도반 수열_9461
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
		
		long[] dp = new long[101];
		dp[1] = dp[2] = dp[3] = 1;
		dp[4] = 2;
		for (int i = 5; i <= 100; i++) dp[i] = dp[i-1] + dp[i-5];

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) bw.write(String.valueOf(dp[Integer.parseInt(br.readLine())]) + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}