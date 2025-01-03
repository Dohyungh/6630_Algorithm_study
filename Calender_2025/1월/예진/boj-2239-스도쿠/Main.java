/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 스도쿠_2239
 * Date: 2025.01.03
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[][] sudoku;
	static int[][] test;
	static int[] row;
	static int[] col;
	static int[][] grid;
	static boolean complete;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		sudoku = new int[9][9];
		test = new int[9][9];
		grid = new int[3][3];
		row = new int[9];
		col = new int[9];

		for (int i = 0; i < 9; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = input[j] - '0';
				test[i][j] = sudoku[i][j];
				row[i] |= 1 << sudoku[i][j];
				col[j] |= 1 << sudoku[i][j];
				grid[i / 3][j / 3] |= 1<< sudoku[i][j];
			}
		}

		complete = false;
		dfs(0, 0);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(sudoku[i][j]);
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int r, int c) throws IOException {
		if (complete) return;
		if (r >= 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sudoku[i][j] = test[i][j];
				}
			}
			complete = true;
			return;
		}

		if (c >= 9) {
			dfs(r + 1, 0);
			return;
		}

		if (sudoku[r][c] != 0) {
			dfs(r, c + 1);
			return;
		}

		int visited = row[r] | col[c] | grid[r / 3][c / 3];
		int memoR = row[r];
		int memoC = col[c];
		int memoGrid = grid[r / 3][c / 3];
		for (int i = 1; i <= 9; i++) {
			if ((visited & 1 << i) != 0) continue;

			test[r][c] = i;
			row[r] |= 1 << i;
			col[c] |= 1 << i;
			grid[r / 3][c / 3] |= 1 << i;

			dfs(r, c + 1);

			row[r] = memoR;
			col[c] = memoC;
			grid[r / 3][c / 3] = memoGrid;
		}
	}
}