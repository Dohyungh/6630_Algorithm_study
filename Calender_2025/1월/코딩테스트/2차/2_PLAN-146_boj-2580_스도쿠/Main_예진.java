package 코테_boj_2580_스도쿠;

/**
 * Author: yngbao97, Yuk Yejin
 */

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[][] sudoku;
	static int[][] copy;
	static int[] row;
	static int[] col;
	static int[][] grid;
	static boolean complete;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new InputStreamReader(new FileInputStream("src/"+Main.class.getPackage().getName()+"/input.txt")));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		sudoku = new int[9][9];
		copy = new int[9][9];
		row = new int[9];
		col = new int[9];
		grid = new int[3][3];
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
				copy[i][j] = sudoku[i][j];
				row[i] |= (1 << sudoku[i][j]);
				col[j] |= (1 << sudoku[i][j]);
				grid[i / 3][j / 3] |= (1 << sudoku[i][j]);
			}
		}
		
		complete = false;
		dfs(0, 0);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				bw.write(sudoku[i][j] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();

	}

	private static void dfs(int r, int c) {
		if (complete) return;
		
		if (c >= 9) {
			dfs(r + 1, 0);
			return;
		}
		
		if (r >= 9) {
			for (int i = 0; i < 9; i++) sudoku[i] = Arrays.copyOf(copy[i], 9);
			complete = true;
			return;
		}
		
		if (sudoku[r][c] == 0) {
			int oriRow = row[r];
			int oriCol = col[c];
			int oriGrid = grid[r/3][c/3];
			for (int i = 1; i <= 9; i++) {
				int num = (1 << i);
				if ((row[r] & num) > 0 || (col[c] & num) > 0 
						|| (grid[r/3][c/3] & num) > 0) continue;
				
				copy[r][c] = i;
				row[r] |= num;
				col[c] |= num;
				grid[r/3][c/3] |= num;
				dfs(r, c + 1);
				row[r] = oriRow;
				col[c] = oriCol;
				grid[r/3][c/3] = oriGrid;
			}
		} else dfs(r, c + 1);
	}
}
