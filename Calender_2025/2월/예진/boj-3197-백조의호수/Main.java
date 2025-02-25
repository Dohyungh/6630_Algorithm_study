/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 백조의 호수_3197
 * Date: 2025.02.25
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[][][] p;
	static char[][] lake;
	static int[] a;
	static int[] b;
	static boolean[][] visited;
	static Queue<int[]> will;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		lake = new char[n+2][m+2];
		p = new int[n+2][m+2][2];

		// 호수 입력
		for (int i = 1; i<= n; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 1; j <= m; j++) {
				lake[i][j] = tmp[j-1];
				p[i][j] = new int[] {i, j};
			}
		}

		will = new ArrayDeque<>();
		a = new int[2];
		b = new int[2];
		visited = new boolean[n+2][m+2];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (!visited[i][j] && (lake[i][j] == '.' || lake[i][j] == 'L')) {
					makeOne(i, j);
				}
			}
		}

		visited = new boolean[n+2][m+2];

		int day = 0;
		while (!will.isEmpty()) {

			if (isConnected()) break;

			int size = will.size();
			for (int i = 0; i < size; i++) {
				int[] curr = will.poll();

				lake[curr[0]][curr[1]] = '.';

				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];

					if (lake[nr][nc] == '\u0000') continue;
					if (!visited[nr][nc] && lake[nr][nc] == 'X') {
						will.add(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
					else if (lake[nr][nc] == '.' || lake[nr][nc] == 'L') {
						union(curr[0], curr[1], nr, nc);
					}
				}
			}

			day++;
		}

		bw.write(String.valueOf(day));
		bw.flush();
		bw.close();
		br.close();
	}

	public static void union(int r1, int c1, int r2, int c2) {
		int[] root1 = findSet(r1, c1);
		int[] root2 = findSet(r2, c2);

		if (root1[0] == root2[0] && root1[1] == root2[1]) return;

		p[root1[0]][root1[1]] = root2;
	}

	public static int[] findSet(int r, int c) {
		if (p[r][c][0] == r && p[r][c][1] == c) return p[r][c];
		return p[r][c] = findSet(p[r][c][0], p[r][c][1]);
	}

	public static boolean isConnected() {
		int[] A = findSet(a[0], a[1]);
		int[] B = findSet(b[0], b[1]);

        return A[0] == B[0] && A[1] == B[1];
    }

	public static void makeOne(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		visited[r][c] = true;
		queue.add(p[r][c]);

		while (!queue.isEmpty()) {

			int[] curr = queue.poll();
			if (lake[curr[0]][curr[1]] == 'L') {
				if (a[0] == 0) a = new int[] {curr[0], curr[1]};
				else b = new int[] {curr[0], curr[1]};
			}

			for (int d = 0; d < 4; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];

				if (visited[nr][nc] || lake[nr][nc] == '\u0000') continue;

				visited[nr][nc] = true;
				if (lake[nr][nc] == 'X') will.add(new int[] {nr, nc});
				else {
					queue.add(new int[] {nr, nc});
					union(curr[0], curr[1], nr, nc);
				}
			}
		}
	}
}