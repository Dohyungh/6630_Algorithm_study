package boj_11054_가장긴바이토닉부분수열;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp_inc = new int[N]; // 증가하는 수열
		int[] dp_dec = new int[N]; // 감소하는 수열

		// 증가하는 수열 DP
		for (int i = 0; i < N; i++) {
			dp_inc[i] = 1;

			for (int j = 0; j < i; j++) {
				if (A[j] < A[i] && dp_inc[j] + 1 > dp_inc[i]) {
					dp_inc[i] = dp_inc[j] + 1;
				}
			}
		}

		// 감소하는 수열 DP
		for (int i = N - 1; i >= 0; i--) {
			dp_dec[i] = 1;

			for (int j = N - 1; j > i; j--) {
				if (A[j] < A[i] && dp_dec[j] + 1 > dp_dec[i]) {
					dp_dec[i] = dp_dec[j] + 1;
				}
			}
		}

		int max = Integer.MIN_VALUE;

		// (증가하는 수열 + 감소하는 수열)이 최대가 되는 인덱스의 값 출력
		for (int i = 0; i < N; i++) {
			max = Math.max(max, dp_inc[i] + dp_dec[i] - 1);
		}

		System.out.println(max);

	}
}
