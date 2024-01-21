package SWEA._2001_파리퇴치;

import java.util.Scanner;

public class Solution_fin2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc<=T;tc++) {
			int N = sc.nextInt();
			int M =sc.nextInt();
			
			// arr 생성
			int[][] arr = new int[N][N];
			for (int i = 0; i<N;i++) {
				for (int j = 0; j<N;j++) {
					arr[i][j] = sc.nextInt();		
				}
			}
			
			// 4중 for 문
			int ans = 0;
			for (int i = 0; i<N-M+1;i++) {
				for (int j = 0; j<N-M+1;j++) {
					
					int temp = 0;
					for (int k = 0; k<M;k++) {
						for (int l = 0; l<M;l++) {
							temp += arr[i+k][j+l];
							
							
							
						}
					}
					if (ans<temp) {
						ans = temp;
					}
				}
			}
			
			System.out.printf("#%d %d%n",tc,ans);
			
		}
		
	}
}
