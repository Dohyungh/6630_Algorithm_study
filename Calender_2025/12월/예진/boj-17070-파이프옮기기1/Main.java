/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 파이프 옮기기 1_17070
 * Date: 2024.12.22
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int n;
	static int[][] room;
	static int answer;

	static int[][] dir = {{0, 2},{1, 2}, {0, 1, 2}};
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 0, 1};

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		room = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		dfs(0, 0, 1);

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int type, int row, int col) {

		if (row == n-1 && col == n-1) {
			answer++;
			return;
		}

		for (int d : dir[type]) {
			int nr = row + dr[d];
			int nc = col + dc[d];

			if (d == 0 && nc < n && room[nr][nc] == 0) dfs(d, nr, nc);
			if (d == 1 && nr < n && room[nr][nc] == 0) dfs(d, nr, nc);
			if (d == 2 && nr < n && nc < n && room[nr-1][nc] == 0
					&& room[nr][nc-1] == 0 && room[nr][nc] == 0) dfs(d, nr, nc);
		}
	}
}