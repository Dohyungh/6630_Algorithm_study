/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 피리 부는 사나이_16724
 * Date: 2025.01.19
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] cycle;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	static int answer;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			char[] chars = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (chars[j] == 'R') map[i][j] = 1;
				else if (chars[j] == 'D') map[i][j] = 2;
				else if (chars[j] == 'L') map[i][j] = 3;
			}
		}

		visited = new boolean[n][m];
		cycle = new boolean[n][m];
		answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) bfs(i, j);
			}
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(int r, int c) {
		if (cycle[r][c]) return;
		if (visited[r][c]) {
			answer++;
			return;
		}
		visited[r][c] = true;
		int direc = map[r][c];
		bfs(r+dr[direc], c+dc[direc]);
		cycle[r][c] = true;
	}
}