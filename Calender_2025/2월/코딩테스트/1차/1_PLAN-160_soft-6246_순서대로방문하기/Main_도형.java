package SOFT._6246_순서대로방문하기;

import java.util.Scanner;

public class Main {
	static int answer = 0;
	static boolean[][] visited;
	static int[][] station;
	static int N;
	static int M;
	static int[] dr = new int[] {-1,1,0,0};
	static int[] dc = new int[] {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = sc.nextInt() == 0 ? false : true;
			}
		}
		
		station = new int[M][2];
		
		for (int i = 0; i < M; i++) {
			station[i][0] = sc.nextInt() - 1;
			station[i][1] = sc.nextInt() - 1;
			visited[station[i][0]][station[i][1]] = true;
		}
		
		start(station[0][0], station[0][1], 1);
		
		
		System.out.println(answer);
		
		
	}
	
	static void start(int row, int col, int level) {
		
//		System.out.println("row: " + row + " col: " + col + " level: " + level);
		
		if (level == M && row == station[M-1][0] && col == station[M-1][1]) {
//			System.out.println("+1!");
			answer++;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nRow = row + dr[i];
			int nCol = col + dc[i];
			
			if (nRow == station[level][0] && nCol == station[level][1]) {
				start(nRow, nCol, level+1);
				continue;
			}
			
			if (nRow >= 0 && nRow < N && nCol >= 0 && nCol < N && !visited[nRow][nCol]) {
				visited[nRow][nCol] = true;
				start(nRow, nCol, level);
				visited[nRow][nCol] = false;
			}
			
			
		}
	}

}
