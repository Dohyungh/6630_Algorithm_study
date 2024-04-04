package boj_11049_행렬곱셈순서;

import java.util.Scanner;

public class Main_Fin {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] pArr = new int[N + 1];

		for (int i = 0; i < N; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			// 각 행렬의 행의 값을 pArr에 저장
			pArr[i] = r;
			// 마지막 행렬의 열의 값을 pArr에 추가로 저장
			if (i == N - 1) {
				pArr[N] = c;
			}
		}

		// Minimum Multiplication M_ij를 담을 dp 테이블 선언
		int[][] dp = new int[N][N];

		
		// 여기서부터도 고민해야 한다.
		// 공식대로 i, j, k를 사용하기 위해서 어떻게 index를 갖고 놀아야 하는가...
		// d는 대각선의 위치를 조정하는 변수
		
		for (int d = 1; d < N; d++) {
			
			// M_ij의 i값을 먼저 생성
			// i는 0부터 전체 행렬 개수 N-(대각선의 개수) 전까지 생성
			// 즉, N=3이고 d=1일 때, i=0, i=1까지.
			
			for (int i = 0; i < N - d; i++) {
				
				// i를 기준으로 j를 생성할 것임
				// j는 i와 대각선의 위치만큼 차이나므로 j=i+d
				
				int j = i + d;

				
				// 이제 괄호 위치 k를 탐색하고, min값을 갱신하자.
				// 아래는 공식대로 작성하면 끝!
				
				int min = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					min = Math.min(min, dp[i][k] + dp[k + 1][j] + pArr[i] * pArr[k + 1] * pArr[j + 1]);
				}
				dp[i][j] = min;
//				printArr(dp);
			}
		}

		// 첫 행 마지막 열에 내가 원하는 전체 행렬의 최적의 곱 연산 개수가 저장되어 있음! 
		System.out.println(dp[0][N - 1]);

	}

	static void printArr(int[][] arr) {
		System.out.println("---start---");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}
