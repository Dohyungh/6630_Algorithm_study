package boj_3085_사탕게임;

import java.io.*;

public class Main {
	static int N, max;
	static char[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		max = Integer.MIN_VALUE;

		for (int r = 0; r < N; r++) {
			arr[r] = br.readLine().toCharArray();
		}

		// 배열 전체 탐색하여 사탕의 오른쪽, 아래 위치 바꾸기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (r < (N - 1) && arr[r][c] != arr[r + 1][c]) {
					// 해당 사탕과 아래 사탕이 다른 색이면 swap
					char tmp = arr[r][c];
					arr[r][c] = arr[r + 1][c];
					arr[r + 1][c] = tmp;
					searchMax(); // 사탕의 최대 갯수 찾기
					// 원상복구
					tmp = arr[r][c];
					arr[r][c] = arr[r + 1][c];
					arr[r + 1][c] = tmp;
				}
				if (c < (N - 1) && arr[r][c] != arr[r][c + 1]) {
					// 해당 사탕과 오른쪽 사탕이 다른 색이면 swap
					char tmp = arr[r][c];
					arr[r][c] = arr[r][c + 1];
					arr[r][c + 1] = tmp;
					searchMax(); // 사탕의 최대 갯수 찾기
					// 원상복구
					tmp = arr[r][c];
					arr[r][c] = arr[r][c + 1];
					arr[r][c + 1] = tmp;
				}
			}
		}
		System.out.println(max);
	}

	// 최대 사탕 갯수 찾
	public static void searchMax() {

		// 행의 방향으로 최댓값 찾기
		for (int c = 0; c < N; c++) {
			// 인접한 사탕 개수 초기화
			int num = 1;
			for (int r = 0; r < N - 1; r++) {
				if (arr[r][c] == arr[r + 1][c]) {
					// 인접한 사탕의 색이 일치하면 num 증가
					num++;
				} else {
					// 인접한 사탕의 색이 일치하지 않으면 최댓값 비교 후 다시 num 초기화
					max = Math.max(max, num);
					num = 1;
				}
			}
			// 하나의 열에 대해 탐색 후 최댓값 비교
			max = Math.max(max, num);
		}

		// 열의 방향으로 최댓값 찾기
		for (int r = 0; r < N; r++) {
			// 인접한 사탕 개수 초기화
			int num = 1;
			for (int c = 0; c < N - 1; c++) {
				if (arr[r][c] == arr[r][c + 1]) {
					// 인접한 사탕의 색이 일치하면 num 증가
					num++;
				} else {
					// 인접한 사탕의 색이 일치하지 않으면 최댓값 비교 다시 num 초기화
					max = Math.max(max, num);
					num = 1;
				}
			}
			// 하나의 열에 대해 탐색 후 최댓값 비교
			max = Math.max(max, num);
		}
	}
}
