package BOJ_1037_오류교정;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();							// 배열 사이즈
		int[][] message = new int[N][N];				// 메시지 정보 변수
		int[][] cnt = new int[2][N];					// 각 행렬의 참 개수
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				message[r][c] = sc.nextInt();			// 메시지 입력하면서
				cnt[0][r] += message[r][c];				// 행 참 개수 세기
			}
		}
		
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				cnt[1][c] += message[r][c];				// 열 참 개수 세기
			}
		}
		
		int answer = 0;									// 답을 1,2,3으로 구분
		int rowIdx = -1;								// 참이 홀수인 행 인덱스
		int colIdx = -1;								// 참이 홀수인 열 인덱스
		
		out: for (int r = 0; r < 2; r++) {
			for (int c = 0; c < N; c++) {
				
				if (cnt[r][c] % 2 != 0) {				// 홀수인걸 찾아
					if (r == 0 && rowIdx != -1) {		// 행인데 이미 홀수인 행이 있으면
						answer = 3;						// 수정도 불가하므로 3, 순회 종료
						break out;
						
					} else if (r == 0) {				// 홀수인 행이 처음이면
						rowIdx = c;						// 행인덱스 값 할당
						
					} else if (r == 1 && colIdx != -1) {	// 열인데 이미 홀수인 열이 있으면
						answer = 3;							// 수정도 불가하므로 3, 순회 종료
						break out;
						
					} else if (r == 1) {				// 홀수인 열이 처음이면
						colIdx = c;						// 열인덱스 값 할당
					}
				}
			}
		}
		
		if (rowIdx == -1 && colIdx == -1) answer = 1;			// 홀수인 행렬이 없으면 1
		else if (rowIdx != -1 && colIdx != -1) answer = 2;		// 홀수인 행렬이 각각 1개면 2
		else answer = 3;										// 나머지는 다 3
		
		if (answer == 1) {										// 상태에 따른 출력
			System.out.println("OK");
		} else if (answer == 2) {
			System.out.printf("Change bit (%d,%d)\n", rowIdx+1, colIdx+1);
		} else if(answer == 3) {
			System.out.println("Corrupt");
		}
		
		sc.close();												// 문단속
	}
}