/*
 * dfs로 dp를 갱신했더니 다른 경우의 수를 합하지 않고 그냥 혼자 가버려서
 * 방문체크 되면 다시 갱신이 안되더라..
 * 어차피 오른쪽, 아래쪽으로만 갈 수 있는 규칙이라서 dfs, bfs 하지 않고 반복문 돌려서 순회했다.
 */
package boj_1890_점프;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] board = new int[N][N];
		long[][] dp = new long[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		dp[0][0] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0 && dp[i][j] != 0) {
					int jump = board[i][j];
					
					if (i+jump < N) dp[i+jump][j] += dp[i][j];
					if (j+jump < N) dp[i][j+jump] += dp[i][j];
				}
			}
		}
		
		long answer = dp[N-1][N-1];
		
		System.out.println(answer);
		sc.close();
	}
}
