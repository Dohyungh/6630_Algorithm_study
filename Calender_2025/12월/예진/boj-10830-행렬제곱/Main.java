/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 행렬 제곱_10830
 * Date: 2024.12.26
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int n;
	static int[][] basic;
	static final int MOD = 1000;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		long b = Long.parseLong(input[1]);

		basic = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				basic[i][j] = Integer.parseInt(st.nextToken()) % MOD;
			}
		}

		int[][] answer = pow(b);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(answer[i][j]).append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static int[][] pow(long cnt) {
		if (cnt == 1) return basic;

		int[][] half = pow(cnt / 2);
		int[][] mult = matrixMult(half, half);
		if (cnt % 2 == 0) return mult;
		else return matrixMult(mult, basic);
	}

	private static int[][] matrixMult(int[][] a, int[][] b) {

		int[][] result = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int answer = 0;
				for (int k = 0; k < n; k++) {
					answer += (a[i][k] * b[k][j]) % MOD;
				}
				result[i][j] = answer % MOD;
			}
		}

		return result;
	}
}