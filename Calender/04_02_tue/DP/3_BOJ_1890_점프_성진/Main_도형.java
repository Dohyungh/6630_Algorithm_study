package BOJ._1890_점프;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	// 그래프로 만들어서 DFS 돌려보는 것도 의미 있을 듯
	
	// 오 깨달음
	
	static long answer;
	static int N;
	static int[][] map;
	static long[][] timeMap;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		map = new int[N][N];
		timeMap = new long[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(timeMap[i],-1);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// 여기까지 입력
		 
		for (int i = N-1; i>=0; i--) {
			for (int j = N-1; j>=0; j--) {
				answer = 0L;
				DP(i,j);
				timeMap[i][j] = answer;
			}
		}
		
		System.out.println(timeMap[0][0]);
	}

	private static void DP(int i, int j) {
		
		if (i >= N || j >= N) return;
		
		if (timeMap[i][j] != -1) {
			answer+=timeMap[i][j];
			return;
		}
		
		if (i == N-1 && j == N-1) {
			answer++;
			return;
		}
		
		//이거 없으면 무한호출
		if (map[i][j] ==0) return; // 진짜 빡치게 하네
		
		DP(i+map[i][j],j);
		DP(i,j+map[i][j]);
		
	}

}
