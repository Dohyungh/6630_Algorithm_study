package boj_17070_파이프옮기기1;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] home;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		home = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				home[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;

		/*
		 * arr[0]: 파이프 끝의 row index 
		 * arr[1]: 파이프 끝의 column index 
		 * arr[2]: 파이프의 현재 방향 (0: 가로, 1: 세로, 2:대각선)
		 */
		Queue<int[]> queue = new ArrayDeque<>();

		queue.add(new int[] { 0, 1, 0 });

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			if (curr[0] == N - 1 && curr[1] == N - 1) {
				ans++;
				continue;
			}

			int nr = curr[0] + 1;
			int nc = curr[1] + 1;

			// 대각선 이동은 모든 방향일 때 가능
			// 이동이 가능할 경우 queue에 삽입
			if (nr < N && nc < N && home[curr[0]][nc] == 0 && home[nr][curr[1]] == 0 && home[nr][nc] == 0) {
				queue.add(new int[] { nr, nc, 2 });
			}

			// 파이프가 가로 방향이거나 대각선인 경우
			if (curr[2] == 0 || curr[2] == 2) {
				// 가로 방향 이동이 가능할 경우 queue에 삽입
				if (nc < N && home[curr[0]][nc] == 0) {
					queue.add(new int[] { curr[0], nc, 0 });
				}
			}

			// 파이프가 세로 방향이거나 대각선인 경우
			if (curr[2] == 1 || curr[2] == 2) {
				// 새로 방향 이동이 가능할 경우 queue에 삽입
				if (nr < N && home[nr][curr[1]] == 0) {
					queue.add(new int[] { nr, curr[1], 1 });
				}
			}
		}

		System.out.println(ans);
	}
}
