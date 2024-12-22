/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 알파벳_1987
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
    static int m;
    static int[][] board;
    static boolean[] visited;
    static int max;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = line[j] - 'A';
            }
        }

        visited = new boolean[26];
        visited[board[0][0]] = true;
        max = 0;
        dfs(0, 0, 1);

        bw.write(String.valueOf(max));
		bw.flush();
		bw.close();
		br.close();
	}

    public static void dfs(int row, int col, int cnt) {

        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];

            if (nr >= n || nr < 0 || nc >= m || nc < 0 || visited[board[nr][nc]]) continue;

            visited[board[nr][nc]] = true;
            dfs(nr, nc, cnt + 1);
            visited[board[nr][nc]] = false;
        }

        max = Math.max(max, cnt);
    }
}