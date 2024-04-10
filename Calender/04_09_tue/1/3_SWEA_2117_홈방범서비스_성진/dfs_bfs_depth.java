package swea_2117_홈방범서비스;

import java.util.LinkedList;
import java.util.Queue;

public class dfs_bfs_depth {

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static int N;

	static boolean[][] visited;

	public static void main(String[] args) {

		int N = 5;

		int[][] map = new int[5][5];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bfs(map, i, j, 2);
				dfs(map, i, j, 0, 1);
			}
		}

	}

	static void bfs(int[][] map, int row, int col, int limit) {

		Queue<Integer> queue = new LinkedList<Integer>();
		int N = map.length;

		visited = new boolean[N][N];

//		map[row][col] = 1;
		visited[row][col] = true;

		int num = row * N + col;

		int depth = 0;

		queue.add(num);

		while (!queue.isEmpty()) {

			if (depth == limit) {
				break;
			}

			int size = queue.size();
			System.out.println(queue);

			for (int i = 0; i < size; i++) {

				int n = queue.poll();

				int r = n / N;
				int c = n % N;

				for (int d = 0; d < 4; d++) {

					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {

						queue.add(nr * N + nc);

//						map[nr][nc] = 1;
						visited[nr][nc] = true;
					}

				}

			}

			printMap(map);
			depth++;

		}
		System.out.println(depth);

	}

	static void dfs(int[][] map, int row, int col, int currentDepth, int maxDepth) {
		if (currentDepth > maxDepth) {
			return; // 최대 깊이를 초과하면 탐색 중단
		}

		// 방문 처리
		visited[row][col] = true;

		// 4방 탐색
		for (int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
				dfs(map, nr, nc, currentDepth + 1, maxDepth);
			}
		}

		// 방문 처리 해제 (다른 경로에서의 재탐색 허용)
		visited[row][col] = false;
	}

	static void printMap(int[][] map) {
		System.out.println("---start---");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
