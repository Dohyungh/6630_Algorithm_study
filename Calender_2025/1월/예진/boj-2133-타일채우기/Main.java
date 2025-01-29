/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 타일 채우기_2133
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
		if (n % 2 != 0) {
			bw.write("0");
		} else {
			long[] dp = new long[n+1];
			dp[2] = 3;
			for (int i = 4; i <= n; i += 2) {
				dp[i] = (dp[i-2] * 3) + 2;
				for (int j = i-4; j > 0; j--) {
					dp[i] += dp[j] * 2;
				}
			}

			bw.write(String.valueOf(dp[n]));
		}
		bw.flush();
		bw.close();
		br.close();
	}
}