package BOJ._9465_스티커;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[2][n];
			
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[3][n];			
			
			dp[0][0] = arr[0][0];
			dp[1][0] = arr[1][0];
			dp[2][0] = 0;
			
			for (int i = 1; i < n; i++) {
				dp[0][i] = Math.max(dp[1][i-1], dp[2][i-1]) + arr[0][i];
				dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + arr[1][i];
				dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]);
				dp[2][i] = Math.max(dp[2][i], dp[2][i-1]);
				
			}
			
			int max = Math.max(dp[0][n-1], dp[1][n-1]);
			max = Math.max(max, dp[2][n-1]);
			
			System.out.println(max);
		}
		
		br.close();
		
	}

}
