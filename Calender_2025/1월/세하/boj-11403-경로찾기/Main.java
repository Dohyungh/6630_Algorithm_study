package boj_11403_경로찾기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 플로이드-워셜 알고리즘
		for (int h = 0; h < N; h++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// r -> c 로 바로 가는 경로가 있으면 continue
					if (arr[r][c] > 0) {
						continue;
					}

					if (arr[r][h] > 0 && arr[h][c] > 0) {
						arr[r][c] = 1;
					}
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sb.append(arr[r][c]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
