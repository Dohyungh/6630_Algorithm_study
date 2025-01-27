/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 포도주 시식_2156
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
        int[] wine = new int[n+1];
        for (int i = 1; i <= n; i++) wine[i] = Integer.parseInt(br.readLine());

        if (n == 1) bw.write(String.valueOf(wine[1]));
        else {
            int[][] dp = new int[n+1][3];
            dp[1][0] = wine[1];
            dp[2] = new int[] {wine[1] + wine[2], wine[2], 0};

            for (int i = 3; i <= n; i++) {
                int second = Math.max(Math.max(dp[i-2][0], dp[i-2][1]), dp[i-2][2]);
                int third = Math.max(Math.max(dp[i-3][0], dp[i-3][1]), dp[i-3][2]);
                dp[i] = new int[] {Math.max(dp[i-1][1], dp[i-1][2]) + wine[i], second + wine[i], third + wine[i]};
            }

            int answer = 0;
            for (int i = n-1; i <= n; i++) {
                for (int j = 0; j < 3; j++) answer = Math.max(answer, dp[i][j]);
            }
            bw.write(String.valueOf(answer));
        }

		bw.flush();
		bw.close();
		br.close();
	}
}