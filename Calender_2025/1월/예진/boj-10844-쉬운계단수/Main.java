/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 쉬운 계단 수_10844
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
        int[][] dp = new int[10][n];
        for (int i = 1; i < 10; i++) dp[i][0] = 1;

        for (int c = 1; c < n; c++) {
            dp[0][c] = dp[1][c-1] % 1_000_000_000;
            dp[9][c] = dp[8][c-1] % 1_000_000_000;
            for (int r = 1; r < 9; r++) {
                dp[r][c] = (dp[r-1][c-1] + dp[r+1][c-1]) % 1_000_000_000;
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) answer = (answer + dp[i][n-1]) % 1_000_000_000;

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}