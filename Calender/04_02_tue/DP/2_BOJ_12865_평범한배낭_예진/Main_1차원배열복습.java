package boj_12865_평범한배낭;

import java.util.Scanner;

public class Main_1차원배열복습 {
	
	static int N; 
	static int M;
	static int[][] items;
	static int[] dp;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();		// 물건 개수
		M = sc.nextInt();		// 무게 제한
		
		items = new int[N][2];
		dp = new int[M+1];
		
		for (int i = 0; i < N; i++) {
			items[i] = new int[] {sc.nextInt(), sc.nextInt()};
		}
		
		for (int i = 0; i < N; i++){
			for (int j = M; j >= items[i][0]; j--) {
				dp[j] = Math.max(dp[j], items[i][1]+dp[j-items[i][0]]);
			}
		}
		
		System.out.println(dp[M]);
		sc.close();
	}
}
