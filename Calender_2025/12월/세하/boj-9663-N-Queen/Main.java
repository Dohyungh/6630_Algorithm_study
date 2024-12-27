package boj_9663_NQueen;

import java.io.*;
import java.util.Arrays;

public class Main {
	static int N, ans;
	static int[] position;
	static boolean[][] isImpossible;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		position = new int[N]; // i번째 행에 위치한 말의 column 위치를 저장
		isImpossible = new boolean[N][N]; // isImpossible[r_idx]의 index가 방문하는 column의 index일 때, 방문 가능한지 아닌지 체크하는 변수
		ans = 0;

		sol(0);

		System.out.println(ans);

	}

	public static void sol(int r_idx) {
		// 마지막 행까지 도착하면 말이 모든 행에 존재하는 경우이므로 ans + 1
		if (r_idx == N) {
			ans++;
			return;
		}

		// [r_idx]번째 isImpossible 초기화
		Arrays.fill(isImpossible[r_idx], false);

		// position을 직전 행까지 순회하며 해당 행에서 말의 position(즉, position[i]의 값)에 따라 새로, 대각선 방향에
		// 위치하면 방문할 수 없음
		for (int i = 0; i < r_idx; i++) {
			int i_col = position[i]; // i번째 행에서 말의 위치
			int diff = r_idx - i; // 현재 행과 i번째 행의 차이

			int dir_1 = i_col + diff; // 대각선 오른쪽 아래 방향
			int dir_2 = i_col - diff; // 대각선 왼쪽 아래 방향

			isImpossible[r_idx][i_col] = true; // 새로 방향 불가능

			// 대각선 방향 불가능
			if (dir_1 < N) {
				isImpossible[r_idx][dir_1] = true;
			}
			if (dir_2 >= 0) {
				isImpossible[r_idx][dir_2] = true;
			}
		}

		for (int c = 0; c < N; c++) {
			if (!isImpossible[r_idx][c]) { // 방문 가능하면
				position[r_idx] = c; // position에 위치 저장
				sol(r_idx + 1); // 다음 행으로
			}
		}

	}
}
