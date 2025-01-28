package boj_12865_평범한배낭;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] stuff = new int[N + 1][2];
		
		// 물건 무게 저장 
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			stuff[i][0] = Integer.parseInt(st.nextToken());
			stuff[i][1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N + 1][K + 1];

		for (int r = 1; r <= N; r++) {
			for (int c = 0; c <= K; c++) {
				// r번째 물건의 무게보다 c값이 크거나 같으면 dp 비교 실행 
				if (c >= stuff[r][0]) {
					// '(r - 1) 행에서 (현재 무게 - r번째 물건의 무게) 열에 저장된 값에서 r번째 물건의 가치를 더한 값'과 '(r - 1)행의 c열에 저장된 값' 중 최댓값을 찾아 저장한다. 
					dp[r][c] = Math.max(dp[r - 1][c - stuff[r][0]] + stuff[r][1], dp[r - 1][c]);
				} else {
					dp[r][c] = dp[r - 1][c]; // 그렇지 않으면 r - 1행의 c 값 삽입 
				}
			}
		}

		System.out.println(dp[N][K]);
	}
}
