package sudokuVerification_1974_240120;

import java.util.Scanner;

public class Solution1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] sudoku = new int[9][9]; // 배열 생성
		int answer = 1; // 정답이 기본값
		
		//배열 입력
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				sudoku[r][c] = sc.nextInt();
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
		
		// 3*3 작은 격자 안 9개 숫자 비교
		for (int r = 0; r < 9; r+=3) {
			for (int c = 0; c < 9; c+=3) {
				for (int dr = 0; dr < 3; dr++) {
					for (int dc = 0; dc < 3; dc++) {
						int me = sudoku[r+dr][c+dc];  // 기준값
						int cnt = 0;
						
						for (int nr = 0; nr < 3; nr++) { // 비교할 값
							for (int nc = 0; nc < 3; nc++) {
								if (me == sudoku[r+nr][c+nc]) cnt++; // 일치하는 값이 있으면 +1
							}
						}
						if (cnt > 1) {  // 자신을 제외하고 같은 값이 더 있으면 오답으로 바꾸고 순회 종료
							answer = 0;
							break;
						}
					}
					if (answer == 0) break; // 오답 판정이 나면 순회 종료
				}
				if (answer == 0) break; // 오답 판정이 나면 순회 종료
			}
			if (answer == 0) break; // 오답 판정이 나면 순회 종료
		}
		System.out.printf("#%d %d\n", 1, answer);
		sc.close();
	}
}
