package boj_2293_동전1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[] coins = new int[n];
		int[] dp = new int[k+1];
		
		for (int i = 0; i < n; i++) {
			coins[i] = sc.nextInt();
		}
		
		dp[0] = 1;
		
		for (int j = 0; j < n; j++) {
			for (int i = coins[j]; i <= k; i++) {
				dp[i] += dp[i-coins[j]];
			}
		}
		
		System.out.println(dp[k]);
		sc.close();
	}
}