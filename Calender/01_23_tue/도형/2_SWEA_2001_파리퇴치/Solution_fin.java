package SWEA._2001_파리퇴치;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_fin {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc<=T;tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			
			// arr 생성
			int[][] arr = new int[N][N];
			for (int i = 0; i<N;i++) {
				for (int j = 0; j<N;j++) {
					arr[i][j] = sc.nextInt();		
				}
			}
			
			// 누적합 배열 생성
			int[][] prefixSum = new int[N+1][N+1];
			
			for (int i = 1; i<N+1; i++) {
				for (int j = 1; j<N+1; j++) {
					prefixSum[i][j] = arr[i-1][j-1] + prefixSum[i][j-1] + prefixSum[i-1][j] -prefixSum[i-1][j-1];
				}
			}
			
			// ans update
			int ans = 0;
			for (int i = 1; i<=N-M+1; i++) {
				for (int j =1; j<=N-M+1; j++) {
					int temp = 0;
					temp = prefixSum[i+M-1][j+M-1] - prefixSum[i+M-1][j-1] - prefixSum[i-1][j+M-1] + prefixSum[i-1][j-1];
					if (temp>ans) {
						ans = temp;
					}
				}
			}
			System.out.printf("#%d %d%n",tc,ans);
		}

	}

}
