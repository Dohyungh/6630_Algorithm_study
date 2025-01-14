package boj_18111_마인크래프트;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];

		int max = 0;
		int min = 256;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[r][c]);
				min = Math.min(min, arr[r][c]);
			}
		}

		int min_second = Integer.MAX_VALUE;
		int height = 0;

		// 가능한 높이 min ~ max 에 대해 모두 탐색
		for (int i = min; i <= max; i++) {
			int b = B;
			int second = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					int diff = Math.abs(i - arr[r][c]); // 가능한 높이와 현재 높이의 차이 계산
					// 현재 높이가 더 클 경우 인벤토리에서 블록을 꺼낸다.
					if (arr[r][c] < i) {
						b -= diff;
						second += 1 * diff; // 차이 * 1 만큼의 시간 소요
					}
					// 현재 높이가 더 작을 경우 인벤토리에 블록을 저장한다.
					else if (arr[r][c] > i) {
						b += diff;
						second += 2 * diff; // 차이 * 2 만큼의 시간 소요
					}
				}
			}

			// b가 0보다 작을 때는 해당 높이로 고르게 하는 것이 불가능하다.
			if (b >= 0) {
				// 최소 시간이 여러 경우일 때
				if (second == min_second) {
					// 가장 높은 높이
					height = Math.max(height, i);
				}
				// 지금 시간이 가장 최소일 때는
				else if (second < min_second) {
					min_second = second; // 최소시간 갱신
					height = i; // 높이 갱신
				}
			}
		}

		System.out.println(min_second + " " + height);
	}
}
