package BOJ._17144_미세먼지안녕;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int R;
	static int C;
	
	static int[] machineTop = new int[2];
	static int[] machineBottom= new int[2];
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		
		// 모든 칸에 확산
		// 새로운 맵에 업데이트
		// 리턴
		
		
//		1. 확산
//		2. 바람 순환
		
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		int T = sc.nextInt(); // swea임? 아 아니구나
		
		int[][] map = new int[R][C];
		boolean flag = false;
		for (int i = 0; i< R; i++) {
			for (int j = 0; j <C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1 && !flag) {
					flag = true;
					machineTop[0] = i;
					machineTop[1] = j;
					
				}
				if (map[i][j] == -1 && flag) {
					machineBottom[0] = i;
					machineBottom[1] = j;
					
				}
			}
		}

		
		int time = 0;
		while(time < T) {
			
			map = diffusion(map);
			
			map = move(map);
			
			time++;
		}
		
		int answer = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j <C; j++) {
				answer += map[i][j];
			}
		}
		
		System.out.println(answer); // -1은 복사 안했음
		
		
		
		
	}
	
	public static int[][] diffusion(int[][] map) {
		int[][] newMap = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					if (diffPossible(nr,nc)) {
						newMap[nr][nc] += map[i][j] / 5;
						cnt++;
					}
				}
				newMap[i][j] += map[i][j] - (map[i][j] /5) * cnt ; // 요기가 헷갈리네
				
				
			}
		}
		
		return newMap;
		
		
		
	}
	
	public static boolean diffPossible(int nr, int nc) {
		if (nr >=0 && nr < R && nc >= 0 && nc < C && !Arrays.equals(new int[] {nr,nc}, machineTop) && !Arrays.equals(new int[] {nr,nc}, machineBottom)){
			return true;
		}
		
		return false;
	}
	
	public static int[][] move(int[][] map) {
		int[][] newMap = new int[R][C];
		
		
		//top spin
		int r = machineTop[0];
		int c = 0; // 뭐야 무조건 0이네
		
		
		for (int i = r-1; i >=1; i--) {
			newMap[i][0] = map[i-1][0];
		}
		for (int i = 0; i <=C-2; i++) {
			newMap[0][i] = map[0][i+1];
		}
		for (int i = 0; i <=r-1; i++) {
			newMap[i][C-1] = map[i+1][C-1];
		}

		for (int i= C-1; i>=2; i--) {
			newMap[r][i] = map[r][i-1];
		}
		
		
		//bottom spin
		
		r = machineBottom[0];
		c = 0;
		
		for (int i = 2; i <=C-1; i++) {
			newMap[r][i] = map[r][i-1];
		}
		for (int i = r+1; i <=R-1; i++) {
			newMap[i][C-1] = map[i-1][C-1];
		}
		for (int i = C-2; i>=0; i--) {
			newMap[R-1][i] = map[R-1][i+1];
		}
		for (int i = R-2; i>=r+1; i--) {
			newMap[i][0] = map[i+1][0];
		}
		
		for (int i =1; i < machineTop[0]; i++) {
			for (int j =1; j <C-1; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
		// fill
		for (int i =machineBottom[0]+1; i < R-1; i++) {
			for (int j =1; j <C-1; j++) {
				newMap[i][j] = map[i][j];
			}
		}
			
			
		return newMap;
	}

}
