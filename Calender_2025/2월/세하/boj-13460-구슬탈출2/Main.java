package boj_13460_구슬탈출2;

import java.io.*;
import java.util.*;

public class Main {
	static char[][] board;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int i_max = Math.max(N, M); // 판을 기울일 때 이동할 수 있는 최대 칸수 저장

		board = new char[N][M];

		int[] red_start = new int[2]; // 빨간 구슬의 시작 위치
		int[] blue_start = new int[2]; // 파란 구슬의 시작 위치

		for (int r = 0; r < N; r++) {
			board[r] = br.readLine().toCharArray();

			for (int c = 0; c < M; c++) {
				if (board[r][c] == 'R') {
					red_start[0] = r;
					red_start[1] = c;
				}
				if (board[r][c] == 'B') {
					blue_start[0] = r;
					blue_start[1] = c;
				}
			}
		}

		int ans = -1;

		Queue<int[]> queue = new ArrayDeque<>();

		queue.add(new int[] { red_start[0], red_start[1], blue_start[0], blue_start[1], 0 });

		bfs: while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			// 이동횟수가 10번이면 불가능하므로 break
			if (curr[4] == 10) {
				break;
			}

			// 상하좌우로 기울이기
			for (int d = 0; d < 4; d++) {
				int red_nr = curr[0];
				int red_nc = curr[1];
				int blue_nr = curr[2];
				int blue_nc = curr[3];

				int red_times = 0;
				int blue_times = 0;

				boolean red_goal = false;
				boolean blue_goal = false;

				// 빨간 구슬 기울이기
				for (int i = 0; i < i_max; i++) {
					red_nr += dr[d];
					red_nc += dc[d];

					red_times = i; // 빨간 구슬이 이동한 칸 수

					// 구멍 만나면 red_goal true로 변경 후 기울이기 중단
					if (board[red_nr][red_nc] == 'O') {
						red_goal = true;
						break;
					}

					// 벽 만나면 직전 위치 저장 후 기울이기 중단
					if (board[red_nr][red_nc] == '#') {
						red_nr -= dr[d];
						red_nc -= dc[d];

						break;
					}
				}

				// 파란 구슬 기울이기
				for (int i = 0; i < i_max; i++) {
					blue_nr += dr[d];
					blue_nc += dc[d];

					blue_times = i;

					// 구멍 만나면 blue_goal true로 변경 후 기울이기 중단
					if (board[blue_nr][blue_nc] == 'O') {
						blue_goal = true;
						break;
					}

					// 벽 만나면 직전 위치 저장 후 기울이기 중단
					if (board[blue_nr][blue_nc] == '#') {
						blue_nr -= dr[d];
						blue_nc -= dc[d];

						break;
					}
				}

				// 파란 구슬이 구멍을 빠져나가면 실패이므로 continue
				if (blue_goal)
					continue;

				// 빨간 구슬이 구멍을 빠져나가면 ans에 기울인 횟수 저장 후 bfs 중단
				if (red_goal) {
					ans = curr[4] + 1;
					break bfs;
				}

				// 파란 구슬과 빨간 구슬의 위치가 같을 때
				if (red_nr == blue_nr && red_nc == blue_nc) {
					// 이동한 칸수가 더 많은 구슬을 한칸 뒤로 이동
					if (red_times < blue_times) {
						blue_nr -= dr[d];
						blue_nc -= dc[d];
					} else {
						red_nr -= dr[d];
						red_nc -= dc[d];
					}
				}

				// 기울인 결과와 횟수를 queue에 추가
				queue.add(new int[] { red_nr, red_nc, blue_nr, blue_nc, curr[4] + 1 });
			}

		}
		System.out.println(ans);
	}
}
