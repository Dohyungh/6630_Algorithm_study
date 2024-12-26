package 코테_boj_9465_스티커;

/**
 * Author: yngbao97, Yuk Yejin
 * Date: 2024.12.26
 */

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new InputStreamReader(new FileInputStream("src/"+Main.class.getPackage().getName()+"/input.txt")));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			
			int n = Integer.parseInt(br.readLine());
			int[][] sticker = new int[2][n];
			int[][] dp = new int[2][n];
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][0] = sticker[0][0];
			dp[1][0] = sticker[1][0];
			
			for (int i = 1; i < n; i++) {
				dp[0][i] = dp[1][i-1] + sticker[0][i];
				dp[1][i] = dp[0][i-1] + sticker[1][i];
				
				if (i > 1) {
					int front = Math.max(dp[0][i-2], dp[1][i-2]);
					dp[0][i] = Math.max(dp[0][i], front + sticker[0][i]);
					dp[1][i] = Math.max(dp[1][i], front + sticker[1][i]);
				}
			}
			
			int answer = Math.max(dp[0][n-1], dp[1][n-1]);
			bw.write(String.valueOf(answer) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}
}