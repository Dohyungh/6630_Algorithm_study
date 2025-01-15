package boj_21736_헌내기는친구가필요해;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] arr = new char[N][M];
		boolean[][] visited = new boolean[N][M];

		Queue<int[]> queue = new ArrayDeque<>();

		for (int r = 0; r < N; r++) {
			arr[r] = br.readLine().toCharArray();

			for (int c = 0; c < M; c++) {
				if (arr[r][c] == 'I') {
					queue.add(new int[] { r, c });
					visited[r][c] = true;
				}
			}
		}

		int ans = 0;

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (arr[nr][nc] != 'X' && !visited[nr][nc]) {
						queue.add(new int[] { nr, nc });
						visited[nr][nc] = true;

						if (arr[nr][nc] == 'P') {
							ans++;
						}
					}
				}
			}
		}

		if (ans == 0) {
			System.out.println("TT");
			return;
		}

		System.out.println(ans);
	}
}
