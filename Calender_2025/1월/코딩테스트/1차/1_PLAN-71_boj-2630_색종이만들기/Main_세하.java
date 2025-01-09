package boj_2630_색종이만들기;

import java.io.*;
import java.util.*;

public class Main {
	static int N, white, blue;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		divide(0, 0, N);

		System.out.println(white);
		System.out.println(blue);
	}

	public static void divide(int r, int c, int size) {
		if (check(r, c, size)) {
			if (arr[r][c] == 1) {
				blue++;
			} else {
				white++;
			}
			return;
		}

		int halfSize = size / 2;

		divide(r, c, halfSize);
		divide(r, c + halfSize, halfSize);
		divide(r + halfSize, c, halfSize);
		divide(r + halfSize, c + halfSize, halfSize);

	}

	public static boolean check(int r_idx, int c_idx, int size) {
		int isBlue = arr[r_idx][c_idx];

		for (int r = r_idx; r < r_idx + size; r++) {
			for (int c = c_idx; c < c_idx + size; c++) {
				if (arr[r][c] != isBlue) {
					return false;
				}
			}
		}
		return true;
	}
}
