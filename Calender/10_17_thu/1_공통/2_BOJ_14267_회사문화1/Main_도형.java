package BOJ._14267_회사문화1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	// 상관
	static int[] p;
	
	// 입력받는 기본 칭찬
	static int[] score;
	
	// 한 번 구한 상관부터는 다시 구하지 않기 위해 dp
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		p = new int[N+1];
		for (int i = 1; i < N+1; i++) p[i] = sc.nextInt();
		
		score = new int[N+1];
		for (int i = 0; i < M; i++) score[sc.nextInt()] += sc.nextInt();
		// 여기까지 입력
		
		// 상관의 칭찬 점수 초기화
		dp = new int[N+1];
		Arrays.fill(dp, -1);
		dp[1] = 0;
		
		for (int i = 1; i < N+1; i++) {
			System.out.print(getVal(i) + " ");
		}
		
	}
	static int getVal(int i) {
		// 이미 구한 곳이면 그냥 반환하고 끝
		if (dp[i] != -1) return dp[i];
		
		// 사장 나오면 끝
		if (p[i] == -1) return 0;
		
		// 이외에는 dp 업로드 하면서 부모를 거슬러 오르는 재귀
		return dp[i] = getVal(p[i]) + score[i];
	}

}
