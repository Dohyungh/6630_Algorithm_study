package boj_11660_구간합구하기5;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				if (c == 0) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				} else {
					arr[r][c] = arr[r][c - 1] + Integer.parseInt(st.nextToken());
				}
			}
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;

			int sum = 0;

			for (int r = x1; r <= x2; r++) {
				if (y1 > 0) {
					sum += arr[r][y2] - arr[r][y1 - 1];
				} else {
					sum += arr[r][y2];
				}
			}

			bw.write(sum + "\n");
		}

		bw.flush();
	}
}