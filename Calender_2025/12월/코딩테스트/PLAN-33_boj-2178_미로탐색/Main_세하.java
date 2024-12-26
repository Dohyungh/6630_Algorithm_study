package boj_2178_미로탐색;

import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int r;
		int c;
		int dist;

		public Node(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];
		boolean[][] visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			char[] tmp = br.readLine().toCharArray();

			for (int c = 0; c < M; c++) {
				arr[r][c] = Character.getNumericValue(tmp[c]);
			}
		}

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		Queue<Node> queue = new LinkedList<Node>();

		queue.add(new Node(0, 0, 1));
		visited[0][0] = true;

		int ans = 987654321;

		while (!queue.isEmpty()) {
			Node curr = queue.poll();

			if (curr.r == N - 1 && curr.c == M - 1) {
				ans = Math.min(ans, curr.dist);
			}

			for (int d = 0; d < 4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && arr[nr][nc] == 1) {
					queue.add(new Node(nr, nc, curr.dist + 1));
					visited[nr][nc] = true;
				}
			}
		}

		System.out.println(ans);
	}
}
