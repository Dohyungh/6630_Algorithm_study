package sudokuVerification_1974_240120;

import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) {
		// 삼중배열 이용
		Scanner sc = new Scanner(System.in);
		
		int[][] sudoku = new int[9][9]; // 배열 생성
		int[][][] smalls = new int[9][3][3];
		int answer = 1; // 정답이 기본값
		
		// 9*9 2차원 배열 입력
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				sudoku[r][c] = sc.nextInt();
			}
		}
		
		// 3*3 작은 격자 배열을 9개 순차 입력
		int n = 0; // 작은 격자 배열의 개수
		for (int r = 0; r < 9; r+=3) {
			for (int c = 0; c < 9; c+=3) {
				for (int sR = 0; sR < 3; sR++) {
					for (int sC = 0; sC < 3; sC++) {
						smalls[n][sR][sC] = sudoku[r+sR][c+sC];
					}
				}
				n++; // 작은격자 하나가 채워질 때마다 배열 개수 증가
			}
		}
		
		// 행 우선 순회 - 같은 행 9개 숫자 비교
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				int me = sudoku[r][c];  // 기준값
				int cnt = 0;
				for (int nc = 0; nc < 9; nc++) { // 비교할 값
					if (me == sudoku[r][nc]) cnt++; // 일치하는 값이 있으면 +1
				}
				if (cnt > 1) { // 자신을 제외하고 같은 값이 더 있으면 오답으로 바꾸고 순회 종료
					answer = 0;
					break;
				}
				if (answer == 0) break; // 오답 판정이 나면 순회 종료
			}
			if (answer == 0) break; // 오답 판정이 나면 순회 종료
		}
		
		// 열 우선 순회 - 같은 열 9개 숫자 비교
		for (int c = 0; c < 9; c++) {
			for (int r = 0; r < 9; r++) {
				int me = sudoku[r][c];  // 기준값
				int cnt = 0;
				for (int nr = 0; nr < 9; nr++) { // 비교할 값
					if (me == sudoku[nr][c]) cnt++; // 일치하는 값이 있으면 +1
				}
				if (cnt > 1) { // 자신을 제외하고 같은 값이 더 있으면 오답으로 바꾸고 순회 종료
					answer = 0;
					break;
				}
				if (answer == 0) break; // 오답 판정이 나면 순회 종료
			}
			if (answer == 0) break; // 오답 판정이 나면 순회 종료
		}
		
		// 3*3 작은 격자 배열 9개 내부 숫자들 비교
		for (int i = 0; i < 9; i++) {
			for (int sR = 0; sR < 3; sR++) {
				for (int sC = 0; sC < 3; sC++) {
					int me = smalls[i][sR][sC];  // 기준값
					int cnt = 0;
					
					for (int nr = 0; nr < 3; nr++) {
						for (int nc = 0; nc < 3; nc++) {
							if (me == smalls[i][nr][nc]) cnt++;  // 기준값과 비교값이 같으면 카운트 +1
						}
					}
					if (cnt > 1) { // 자신을 제외하고 중복값이 더 있으면 오답처리 후 순회 종료
						answer = 0;
						break;
					}
				}
				if (answer == 0) break; // 오답 판정이 나면 순회 종료
			}
			if (answer == 0) break; // 오답 판정이 나면 순회 종료
		}
		System.out.printf("#%d %d\n", 1, answer);
		sc.close();
	}
}
