package boj_17069_파이프옮기기2;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] home = new int[N][N];
		long[][][] dp = new long[N][N][3];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				home[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		/*
		 * dp[r][c][d]
		 * r: 파이프 끝의 row index 
		 * c: 파이프 끝의 column index
		 * d: 파이프의 현재 방향 (0: 가로, 1: 세로, 2:대각선)
		 */
		dp[0][1][0] = 1;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d = 0; d < 3; d++) {
					if (dp[r][c][d] != 0) {
						int nr = r + 1;
						int nc = c + 1;

						// 대각선 방향 이동 가능 여부 판단
						if (nr < N && nc < N && home[nr][c] == 0 && home[r][nc] == 0 && home[nr][nc] == 0) {
							dp[nr][nc][2] += dp[r][c][d];
						}

						// 현재 방향이 세로 또는 대각선일 경우 세로 방향으로 이동 가능 여부 판단
						if (d != 0 && nr < N && home[nr][c] == 0) {
							dp[nr][c][1] += dp[r][c][d];
						}

						// 현재 방향이 가로 또는 대각선일 경우 가로 방향으로 이동 가능 여부 판단
						if (d != 1 && nc < N && home[r][nc] == 0) {
							dp[r][nc][0] += dp[r][c][d];
						}
					}
				}
			}
		}

		long ans = 0;

		for (int d = 0; d < 3; d++) {
			ans += dp[N - 1][N - 1][d];
		}

		System.out.println(ans);
	}
}
