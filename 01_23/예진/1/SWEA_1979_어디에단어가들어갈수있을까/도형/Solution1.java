package SWEA._1979_어디에단어가들어갈수있을까;

import java.util.Scanner;

public class Solution1 {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int T = sc.nextInt();
	for (int tc =1; tc<=T;tc++) {
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[][] map = new int[n][n];
		int[][] check = new int[n][n];
		
		// 맵 입력
		for (int i = 0; i<n;i++) {
			for (int j = 0; j<n;j++) {
				int num = sc.nextInt();
				map[i][j] = num;
				check[i][j] = num;
				
			}
		}
		int ans = 0;
		
		for (int i = 0; i<n; i++) {
			for (int j =0; j<n; j++) {
				if (map[i][j] == 1&&check[i][j] ==1) {
					
					
				}
			}
		}
		
		
		
		System.out.printf("#%d %d",tc,ans);
		
		
	}
	

	
			
}
}
// check map 만들어주기 포기! 메모리는 메모리대로, 시간은 시간대로 오래걸릴 듯.