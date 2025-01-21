package boj_1932_정수삼각형;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][N];

		dp[0][0] = Integer.parseInt(br.readLine()); // 첫 번째 줄 입력

		for (int r = 1; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			dp[r][0] = dp[r - 1][0] + Integer.parseInt(st.nextToken()); // 첫 번째 열은 항상 해당 행까지의 모든 첫 번째 숫자의 합과 같음

			for (int c = 1; c <= r; c++) {
				dp[r][c] = Math.max(dp[r - 1][c - 1], dp[r - 1][c]) + Integer.parseInt(st.nextToken()); // r, c로 올 수 있는
																										// 두 가지 경우 중 최댓값
																										// 저장
			}
		}

		int ans = 0;

		// 최댓값 찾기
		for (int c = 0; c < N; c++) {
			ans = Math.max(ans, dp[N - 1][c]);
		}

		System.out.println(ans);
	}
}
