package soft_6246_순서대로방문하기;

import java.io.*;
import java.util.*;

public class Main {
	static int n, m, ans;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] arr, node;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][n];
		visited = new boolean[n][n];

		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < n; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 방문 지점 2차원 배열로 저장
		node = new int[m][2];

		for (int r = 0; r < m; r++) {
			st = new StringTokenizer(br.readLine());

			node[r][0] = Integer.parseInt(st.nextToken()) - 1;
			node[r][1] = Integer.parseInt(st.nextToken()) - 1;
		}

		ans = 0;
		visited[node[0][0]][node[0][1]] = true; // 시작 지점 방문 check

		dfs(node[0][0], node[0][1], node[1], 1);

		System.out.println(ans);
	}

	public static void dfs(int r_idx, int c_idx, int[] arrive, int arrive_count) {
		// 도착 지점에 방문했을 때
		if (r_idx == arrive[0] && c_idx == arrive[1]) {
			// 최종 도착 지점이면 ans++ 후 return
			if (arrive_count == m - 1) {
				ans++;
				return;
			}
			// 최종 도착 지점이 아니면 다음 도착 지점을 향해 다시 dfs
			dfs(r_idx, c_idx, node[arrive_count + 1], arrive_count + 1);
		}

		for (int d = 0; d < 4; d++) {
			int nr = r_idx + dr[d];
			int nc = c_idx + dc[d];

			if (nr >= 0 && nr < n && nc >= 0 && nc < n && arr[nr][nc] == 0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, arrive, arrive_count);
				visited[nr][nc] = false;
			}
		}
	}
}
