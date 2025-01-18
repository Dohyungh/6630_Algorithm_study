/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 행렬 곱셈 순서_11049
 * Date: 2025.01.18
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
        int[][] matrix = new int[n][2];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int l = 1; l < n; l++) {
            for (int start = 0; start < n-l; start++) {
                int end = start + l;
                dp[start][end] = Integer.MAX_VALUE;
                for (int mid = start; mid < end; mid++) {
                    dp[start][end] = Math.min(dp[start][end],
                                        dp[start][mid] + dp[mid+1][end] + matrix[start][0] * matrix[mid][1] * matrix[end][1]);
                }
            }
        }

        bw.write(String.valueOf(dp[0][n-1]));
		bw.flush();
		bw.close();
		br.close();
	}
}