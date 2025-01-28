package BOJ._1311_할일정하기1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N! 의 알고리즘만 생각하다가
 * 
 * N*2 * 2^N 알고리즘을 알게되었다.. 어렵다
 */

public class Main {
	static int N;
	static int[][] D;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		D = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				D[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[N+1][(int)Math.pow(2, N)];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		
		dp[0][0] = 0;
		
		solve(1);
		
		System.out.println(dp[N][dp[0].length - 1]);
	}

	static void solve(int idx) {
		
		if (idx > N) return;
		
		for (int i = 0; i < dp[idx].length; i++) {
			
			if (dp[idx- 1][i] == Integer.MAX_VALUE) continue;
			
			for (int j = 0; j < N; j++) {
				if ((i & (1<<j)) > 0) continue;
				
				dp[idx][(i | 1<<j)] = Math.min(dp[idx][(i | 1<<j)], dp[idx - 1][i] + D[idx-1][j]);
			}
			
		}
		solve(idx+1);
		
	}
		
}
