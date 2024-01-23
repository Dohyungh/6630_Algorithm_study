package wordInPuzzle_1979_240120;

import java.util.Scanner;

public class Solution1 {

	public static void main(String[] args) {
		// 1회용
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();  // 퍼즐 크기 조건 저장
		int K = sc.nextInt();  // 단어 길이 저장
		int[][] arr = new int[N][N];  // N * N 크기만큼 2차원 배열 생성
		int cnt = 0;
		
		for (int r = 0; r < N; r++) { // 퍼즐 입력(2차원 배열 값 할당)
			for (int c = 0; c < N; c++) {
				arr[r][c] = sc.nextInt();
			}
		}
		
		// 행 우선 순회
		for (int r = 0; r < N; r++) {
			for (int c = 0; c <= (N-K); c++) { // 단어가 들어갈 수 있는 칸의 시작점은 인덱스 (N-K)가 한계임.
				if (arr[r][c] == 1) { // 좌표값이 1이면
					int lng = 1; // 빈칸의 길이를 측정할 변수를 1부터 센다.
					
					// 단어의 길이에서 1을 뺀 나머지 길이만큼 확인한다.
					for (int i = 1; i < K; i++) {
						int nc = c + i;
						if (nc < N) {
							if (arr[r][nc] == 1) lng++; // 다음 자리도 1이면 길이에 추가
						}
					}
					
					int ncBf = c - 1; // 빈칸을 측정하기 시작한 시작점의 전 좌표
					int ncAf = c + K; // 단어의 길이만큼 빈칸을 띈 후 그 다음 좌표
					
					// 1이 연속된 길이가 단어의 길이와 일치하고, 다음 좌표가 경계를 넘었거나, 값이 0이면 cnt 증가
					if (lng == K && (ncBf < 0 || arr[r][ncBf] == 0) && (ncAf == N || arr[r][ncAf] == 0)) cnt++;
				}
			}
		}
		
		// 같은 방식으로 열 우선 순회
		for (int c = 0; c < N; c++) {
			for (int r = 0; r <= (N-K); r++) {
				if (arr[r][c] == 1) {
					int lng = 1;
					
					for (int i = 1; i < K; i++) {
						int nr = r + i;
						if (nr < N) {
							if (arr[nr][c] == 1) lng++; // 다음 자리도 1이면 길이에 추가
						}
					}
					int nrBf = r - 1;
					int nrAf = r + K;
					
					// 1이 연속된 길이가 단어의 길이와 일치하고, 다음 좌표가 경계를 넘었거나, 값이 0이면 cnt 증가
					if (lng == K && (nrBf < 0 || arr[nrBf][c] == 0) && (nrAf == N || arr[nrAf][c] == 0)) cnt++;
				}
			}
		}
		System.out.printf("#%d %d\n", 1, cnt);
	
		sc.close();  
	}
}
