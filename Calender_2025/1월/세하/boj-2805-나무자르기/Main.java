package boj_2805_나무자르기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());

		long[] trees = new long[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			trees[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(trees); // 최초 end 값 찾기 위해 tree 정렬

		long start = 0;
		long end = trees[N - 1];
		long ans = 0;

		// 이분 탐색
		while (start <= end) {
			long mid = (start + end) / 2;

			long sum = 0;

			// 자른 나무의 합 계산
			for (int i = 0; i < N; i++) {
				if (trees[i] > mid) {
					sum += trees[i] - mid;
				}
			}

			// 나무의 합이 M보다 작으면 높이 감소
			if (sum < M) {
				end = mid - 1;
			}
			// 나무의 합이 M보다 크거나 같으면 ans에 저장 후 높이 증가
			else {
				ans = mid;
				start = mid + 1;
			}
		}

		System.out.println(ans);
	}
}
