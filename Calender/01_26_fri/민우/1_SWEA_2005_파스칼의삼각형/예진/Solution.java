package 파스칼의삼각형_2005_240125;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc<=T;tc++) {
			int N = sc.nextInt();
			
			// 원본 배열 생성
			int[][] arr = new int[N+1][];  // 행 0번째줄 패딩할거
			for (int r = 0; r < N+1; r++) {
				arr[r] = new int[r+2];  // 열의 처음과 끝 패딩할거
				if (r != 0) { // 행 인덱스 0은 생성은 되고, 0으로 초기화 상태여야 함
					// 열 1번 인덱스와, 마지막-1 인덱스는 1
					arr[r][1] = 1;
					arr[r][r] = 1;
					for (int c = 1; c < r; c++) { // 생성된 1들을 기준으로 배열 완성
						arr[r][c] = arr[r-1][c-1]+arr[r-1][c];
					}
				}
			}
			
			// 출력
			System.out.println("#"+tc);
			// 패딩을 제외한 부분만 출력
			for (int r = 1; r < N+1; r++) {
				for (int c = 1; c <= r; c++) {
					System.out.print(arr[r][c]+" ");
				}
				System.out.println();
			}
		}
		sc.close();
	}

}
