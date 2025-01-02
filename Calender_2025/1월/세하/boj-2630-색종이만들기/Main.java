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

		white = 0;
		blue = 0;

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

	static void divide(int row, int col, int size) {
		// 현재 영역이 모두 같은 색인지 확인
		if (check(row, col, size)) {
			if (arr[row][col] == 0)
				white++;
			else
				blue++;
			return;
		}

		int newSize = size / 2;
		// 4개의 영역으로 분할
		divide(row, col, newSize); // 좌상단
		divide(row, col + newSize, newSize); // 우상단
		divide(row + newSize, col, newSize); // 좌하단
		divide(row + newSize, col + newSize, newSize); // 우하단
	}

	static boolean check(int row, int col, int size) {
		int color = arr[row][col];

		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (arr[i][j] != color) {
					return false;
				}
			}
		}
		return true;
	}
}
