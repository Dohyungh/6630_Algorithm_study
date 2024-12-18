package boj_2178_미로탐색;

import java.io.*;
import java.util.*;

public class Main {
	// Node 클래스 선언 
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

		Queue<Node> queue = new LinkedList<Node>();

		queue.add(new Node(0, 0, 1));

		int ans = Integer.MAX_VALUE;
		visited[0][0] = true;
		
		// BFS 
		while (!queue.isEmpty()) {
			Node curr = queue.poll();

			int c_r = curr.r;
			int c_c = curr.c;
			int c_dist = curr.dist;
			
			// 도착 지점이면 최소거리 찾기 
			if (c_r == N - 1 && c_c == M - 1) {
				ans = Math.min(ans, c_dist);
			}

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			for (int d = 0; d < 4; d++) {
				int nr = c_r + dr[d];
				int nc = c_c + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && arr[nr][nc] == 1 && !visited[nr][nc]) {
					queue.add(new Node(nr, nc, c_dist + 1));					
					visited[nr][nc] = true;
				}
			}
		}

		System.out.println(ans);
	}
}