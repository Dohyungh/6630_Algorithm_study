package jol_1037_오류교정;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 행렬의 행, 열의 길이 N 입력
		int N = sc.nextInt();

		// 2차원 행렬 초기화
		int[][] arr = new int[N][N];

		// 2차원 행렬 값 입력받기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				arr[r][c] = sc.nextInt();
			}
		}

		// 행의 합, 열의 합을 저장할 배열을 선언
		int[] rowSum = new int[N];
		int[] colSum = new int[N];

		// 각 행의 합을 계산해 배열로 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rowSum[i] += arr[i][j];
			}

		}

		// 각 열의 합을 계산해 배열로 저장
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				colSum[j] += arr[i][j];
			}
		}

		// 행과 열에서 홀수값인 인덱스를 저장할 rowIdx, colIdx -1로 초기화
		int rowIdx = -1;
		int colIdx = -1;

		// 행의 합 중 홀수값이 발생하는 횟수 cnt를 계산
		int cnt1 = 0;
		for (int i = 0; i < N; i++) {
			if (rowSum[i] % 2 != 0) {
				cnt1++;
				rowIdx = i;
			}
		}

		// 만약 cnt1이 1보다 크다면 Corrupt를 출력
		if (cnt1 > 1) {
			System.out.println("Corrupt");
		}

		else {
			// 열의 합에 대해서도 행의 합과 같은 로직을 적용
			int cnt2 = 0;
			for (int j = 0; j < N; j++) {
				if (colSum[j] % 2 != 0) {
					cnt2++;
					colIdx = j;
				}
			}
			if (cnt2 > 1) {
				System.out.println("Corrupt");
			}

			// 각 열과 행의 합에서 홀수값이 한 번만 등장했다면 (i,j)를 출력
			else if (cnt1 == 1 && cnt2 == 1) {
				System.out.printf("Change bit (%d,%d)", rowIdx + 1, colIdx + 1);
			}

			// 그 외 경우 OK를 출력
			// 즉, 열과 행의 합에서 홀수가 등장하지 않은 패리티 행렬인 경우일 때
			else {
				System.out.println("OK");
			}

		}

		sc.close();

	}
}
