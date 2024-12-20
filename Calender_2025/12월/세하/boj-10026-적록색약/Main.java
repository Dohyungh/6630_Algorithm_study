package boj_10026_적록색약;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new char[N][N];
		visited = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			arr[r] = br.readLine().toCharArray();
		}

		int ans_1 = 0;
		int ans_2 = 0;
		
		// 적록색약이 아닐 때 구역 개수 찾기 
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					BFS(r, c);
					ans_1++;
				}
			}
		}
		
		// 적록색약일 경우 Green을 모두 Red로 변경 
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (arr[r][c] == 'G') {
					arr[r][c] = 'R';
				}
			}
		}
		
		// visited 초기화 
		visited = new boolean[N][N];
		
		// 적록색약일 때 구역 개수 찾기 
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					BFS(r, c);
					ans_2++;
				}
			}
		}

		System.out.println(ans_1 + " " + ans_2);
	}

	public static void BFS(int r, int c) {
		Queue<int[]> queue = new LinkedList<int[]>();

		queue.add(new int[] { r, c });

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			for (int d = 0; d < 4; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]
						&& arr[curr[0]][curr[1]] == arr[nr][nc]) {
					queue.add(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}
		}

	}
}
