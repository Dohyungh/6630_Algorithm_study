package SWEA._1979_어디에단어가들어갈수있을까;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_fin {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int T = sc.nextInt();

	for (int tc =1; tc<=T; tc++) {
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[][] map = new int[n][n];

		
		// 맵 입력
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<n; j++) {
				int num = sc.nextInt();
				map[i][j] = num;
			}
		}
		
		long N = (long) n*n;
		int ans = 0;
	
		// 가로 체크
		int i = 0;
		while (i <= (N-1)) {
			int r = i/n;
			int c = i%n;
			int temp = 0;
			if (map[r][c] == 1 && (c == 0 ||map[r][c-1] == 0)) { //순서중요!! , 왼쪽 끝이거나, 전칸이 0이거나
				temp++;
				c++;
				i++;
				//검사
				while (c <= (n-1)) {
					if (c == n-1) { // 끝에 도달
						if (map[r][c] == 1) {
							temp++;
							i++;
							break;
						} else if (map[r][c] == 0) {
							i++;
							break;
						}
					} else { // 중간 진행
						if (map[r][c] == 1) {
							temp++;
							c++;
							i++;
						} else if (map[r][c] == 0) {
							i++;
							break;
						}
					}	
				}
				
				if (temp == k) { // 찾았으면 업데이트
					ans++;
				}
				
			} else { // 0이면 그냥 다음칸 진행
				i++;
			}
			
		}

		
		// 세로 체크
		i = 0; //i 재사용
		while (i <= (N-1)) {
			int c = i/n;
			int r = i%n;
			int temp = 0;
			if (map[r][c] == 1 && (r == 0 ||map[r-1][c] == 0)) { //순서중요!! , 왼쪽 끝이거나, 전칸이 0이거나
				temp++;
				r++;
				i++;
				//검사
				while (r <= (n-1)) {
					if (r == n-1) {
						if (map[r][c] == 1) {
							temp++;
							i++;
							break;
						} else if (map[r][c] == 0) {
							i++;
							break;
						}
					} else {
						if (map[r][c] == 1) {
							temp++;
							r++;
							i++;
						} else if (map[r][c] == 0) {
							i++;
							break;
						}
					}	
				}
				
				if (temp == k) {
					ans++;
				}
				
			} else {
				i++;
			}
			
		}
		
		System.out.printf("#%d %d%n",tc,ans);
		
	}

}
}
