package boj_17144_미세먼지안녕;

import java.util.Scanner;

public class Main {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	static int row;
	static int col;
	static int time;
	static int[][][] dust;
	static int[] fresher;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		row = sc.nextInt();
		col = sc.nextInt();
		time = sc.nextInt();
		dust = new int[row][col][];
		fresher = new int[2];
		
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				int input = sc.nextInt();
				if (input == -1) {
					fresher = new int[] {r-1, r};
					dust[r][c] = new int[] {input};
				} else dust[r][c] = new int[] {input, 0};
			}
		}
		
		for (int t = 0; t < time; t++) {
			spread();
			oper();
		}
		
		int answer = 0;

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (!(c == 0 && (r == fresher[0] || r == fresher[1]))) {
					answer += dust[r][c][0];
				}
			}
		}
		
		System.out.println(answer);
		sc.close();
	}
	
	public static void spread() {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (dust[r][c][0] > 0) {
					int cnt = howMany(r, c);
					dust[r][c][1] = dust[r][c][0] / 5;
					dust[r][c][0] -= dust[r][c][1] * cnt;
				}
			}
		}
		
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (dust[r][c].length > 1) {
					dust[r][c][0] += sum(r, c);
				}
			}
		}
	}

	public static int sum(int r, int c) {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < row && nc >= 0 && nc < col 
					&& !(nc == 0 && (nr == fresher[0] || nr == fresher[1]))) {
				sum += dust[nr][nc][1];
			}
		}
		return sum;
	}

	public static int howMany(int r, int c) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < row && nc >= 0 && nc < col 
					&& !(nc == 0 && (nr == fresher[0] || nr == fresher[1]))) {
				cnt++;
			}
		}
		return cnt;
	}

	public static void oper() {
		
		for (int r = fresher[0]-2; r >= 0; r--) {
			dust[r+1][0] = dust[r][0];
		}
		
		for (int r = fresher[1]+2; r < row; r++) {
			dust[r-1][0] = dust[r][0];
		}
		
		for (int c = 1; c < col; c++) {
			dust[0][c-1] = dust[0][c];
			dust[row-1][c-1] = dust[row-1][c];
		}
		
		for (int r = 1; r <= fresher[0]; r++) {
			dust[r-1][col-1] = dust[r][col-1];
		}
		
		for (int r = row-2; r >= fresher[1]; r--) {
			dust[r+1][col-1] = dust[r][col-1];
		}
		
		for (int c = col-2; c > 0; c--) {
			dust[fresher[0]][c+1] = dust[fresher[0]][c];
			dust[fresher[1]][c+1] = dust[fresher[1]][c];
		}
		
		dust[fresher[0]][1] = new int[] {0, 0};
		dust[fresher[1]][1] = new int[] {0, 0};
		
	}
}
