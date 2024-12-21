package boj_2579_계단오르기;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[N];

		for (int i = 0; i < N; i++) {
			if (i == 0) { // 첫번째 계단 
				dp[i] = arr[i];
			} else if (i == 1) { // 두번째 계단까지의 최대 점수는 첫번째 계단 + 두번째 계단 
				dp[i] = arr[i - 1] + arr[i];
			} else if (i == 2) { // 세번째 계단까지의 최대 점수는 첫번째 계단과 두번째 계단 중 최댓값과 세번째 계단 점수의 합 
				dp[i] = Math.max(arr[i - 2], arr[i - 1]) + arr[i];
			} else {
				// 4번째 계단 부터 dp로 최댓값 구하기 
				// 현재 위치 - 2 에서 오는 경우와 현재 위치 - 1 에서 오는 경우 중 최댓값 
				dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]);
			}
		}

		System.out.println(dp[N - 1]);
	}
}
