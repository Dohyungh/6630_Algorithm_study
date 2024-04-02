package boj_11057_오르막수;

import java.util.Scanner;

public class Main {

	static int[][] dp;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		dp = new int[11][N+1];
		
		int answer = comb(10, N);
		
		System.out.println(answer);
		sc.close();
	}
	
	private static int comb(int m, int n) {
		if (m == 1 || n == 1) return dp[m][n] = m;
		if (dp[m][n] != 0) return dp[m][n];
		
		dp[m][n] = (comb(m, n-1) + comb(m-1, n)) % 10_007;
		
		return dp[m][n];
	}
}
