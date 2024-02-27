package BOJ._14502_연구소;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] map = new int[N][M];
		for (int i = 0 ; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int answer = 0;
		for(int i = 0; i < N*M-2; i++) {
			int ri = i/M;
			int ci = i%M;
			
			if (map[ri][ci] !=0) continue;
			for(int j = i+1; j < N*M-1; j++) {
				int rj = j/M;
				int cj = j%M;
				if(map[rj][cj] !=0) continue;
				for (int k = j+1; k < N*M; k++) {
					int rk = k/M;
					int ck = k%M;
					if(map[rk][ck] !=0) continue;
					
					int[][] temp = new int[N][M];
					for (int l = 0; l <map.length; l++) {
						temp[l] = Arrays.copyOf(map[l], map[l].length);
					}
					temp[ri][ci] = 1;
					temp[rj][cj] = 1;
					temp[rk][ck] = 1;
					
					answer = Math.max(answer, fillVirus(temp));
					
				}
			}
		}
		System.out.println(answer);
		
	}
	
	public static int countVirus(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j]==2) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static int fillVirus(int[][] map) {
		
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		

		int change = Integer.MAX_VALUE;
		
		while(change != 0) {
			change = 0;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if (map[i][j] == 2) {
						for (int d = 0; d < 4; d++) {
							if (i+dr[d] >= 0 && i+dr[d] <map.length && j+dc[d]>=0 && j+dc[d] <map[0].length && map[i+dr[d]][j+dc[d]] == 0) {
								map[i+dr[d]][j+dc[d]] = 2;
								change++;
							}
							
							
						}
					}
				}
			}
		}
		int numZeros = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j] ==0) numZeros++;
			}
		}
		return numZeros;
		
	}

}
