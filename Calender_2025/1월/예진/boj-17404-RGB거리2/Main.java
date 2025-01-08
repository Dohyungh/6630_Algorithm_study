/**
 * Author: yngbao97, Yuk Yejin
 * Problem: RGB거리 2_17404
 * Date: 2025.01.08
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
        int[][] street = new int[3][n+1];
        for (int j = 1; j <= n; j++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 3; i++) {
                street[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[3][n+1][3];
        for (int i = 0; i < 3; i++) dp[i][1][i] = street[i][1];

        for (int j = 2; j <= n; j++) {
            for (int i = 0; i < 3; i++) {
                for (int s = 0; s < 3; s++) {
                    int a = dp[(i+4)%3][j-1][s];
                    if (a == 0) a = 123456789;
                    int b = dp[(i+5)%3][j-1][s];
                    if (b == 0) b = 123456789;
                    dp[i][j][s] = Math.min(a, b) + street[i][j];
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, Math.min(dp[i][n][(i+4)%3], dp[i][n][(i+5)%3]));
        }

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}