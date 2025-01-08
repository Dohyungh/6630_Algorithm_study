package boj_2096_내려가기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][3];
		int[][][] dp = new int[N][3][2]; // 최댓값, 최솟값을 저장하는 3차원 dp 배열

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < 3; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// dp배열 0번째 행 초기화
		dp[0][0][0] = dp[0][0][1] = arr[0][0];
		dp[0][1][0] = dp[0][1][1] = arr[0][1];
		dp[0][2][0] = dp[0][2][1] = arr[0][2];

		for (int r = 1; r < N; r++) {
			// r 행의 0번째 column
			// r-1 행의 0, 1번째 column에서 내려갔을 경우의 최댓값과 최솟값 비교
			dp[r][0][0] = Math.max(dp[r - 1][0][0], dp[r - 1][1][0]) + arr[r][0];
			dp[r][0][1] = Math.min(dp[r - 1][0][1], dp[r - 1][1][1]) + arr[r][0];

			// r 행의 1번째 column
			// r-1 행의 0, 1, 2번째 column에서 내려갔을 경우의 최댓값과 최솟값 비교
			dp[r][1][0] = Math.max(Math.max(dp[r - 1][0][0], dp[r - 1][1][0]), dp[r - 1][2][0]) + arr[r][1];
			dp[r][1][1] = Math.min(Math.min(dp[r - 1][0][1], dp[r - 1][1][1]), dp[r - 1][2][1]) + arr[r][1];

			// r 행의 2번째 column
			// r-1 행의 1, 2번째 column에서 내려갔을 경우의 최댓값과 최솟값 비교
			dp[r][2][0] = Math.max(dp[r - 1][1][0], dp[r - 1][2][0]) + arr[r][2];
			dp[r][2][1] = Math.min(dp[r - 1][1][1], dp[r - 1][2][1]) + arr[r][2];
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		// 최댓값 최솟값 찾기 
		for (int c = 0; c < 3; c++) {
			max = Math.max(max, dp[N - 1][c][0]);
			min = Math.min(min, dp[N - 1][c][1]);
		}

		System.out.println(max + " " + min);
	}
}
