/**
 * Author: yngbao97, Yuk Yejin
 * Problem: LCS_9251
 * Date: 2024.12.20
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
		
		char[] str_1 = input();
		char[] str_2 = input();
		int[][] dp = new int[str_1.length][str_2.length];

		for (int i = 1; i < str_1.length; i++) {
			for (int j = 1; j < str_2.length; j++) {
				if (str_1[i] == str_2[j]) dp[i][j] = dp[i-1][j-1] + 1;
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}

		bw.write(String.valueOf(dp[str_1.length-1][str_2.length-1]));
		bw.flush();
		bw.close();
		br.close();
	}

	public static char[] input() throws IOException {
		char[] input = br.readLine().toCharArray();
		char[] result = new char[input.length + 1];
		int idx = 1;
		for (char c : input) {
			result[idx++] = c;
		}
		return result;
	}
}