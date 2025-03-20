/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 카드 구매하기_11052
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
        int[] cost = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) cost[i] = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                dp[j] = Math.max(dp[j], dp[j-i] + cost[i]);
            }
        }

        bw.write(String.valueOf(dp[n]));
		bw.flush();
		bw.close();
		br.close();
	}
}