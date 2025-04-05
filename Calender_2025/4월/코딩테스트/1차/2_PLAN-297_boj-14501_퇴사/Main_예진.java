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
		int[] dp = new int[n+1];
		for (int i = 1; i <= n; i++) {
			String[] input = br.readLine().split(" ");
			int t = Integer.parseInt(input[0]);
			int p = Integer.parseInt(input[1]);
			for (int day = i+(t-1); day <= n; day++) {
				dp[day] = Math.max(dp[i-1] + p, dp[day]);
			}
		}

		bw.write(String.valueOf(dp[n]));
		bw.flush();
		bw.close();
		br.close();
	}
}