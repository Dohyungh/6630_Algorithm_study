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

	public static void divide(int r, int c, int size) {
		// 기저 조건 : 정사각형이 완성되었을 때
		if (check(r, c, size)) {
			if (arr[r][c] == 1) {
				blue++;
			} else {
				white++;
			}
			return;
		}

		int halfSize = size / 2;

		// 4등분으로 나누기
		divide(r, c, halfSize); // 2사분면
		divide(r, c + halfSize, halfSize); // 1사분면
		divide(r + halfSize, c, halfSize); // 3사분면
		divide(r + halfSize, c + halfSize, halfSize); // 4사분면
	}

	public static boolean check(int r_idx, int c_idx, int size) {
		int isBlue = arr[r_idx][c_idx]; // 배열의 첫 값을 isBlue로 저장

		for (int r = r_idx; r < r_idx + size; r++) {
			for (int c = c_idx; c < c_idx + size; c++) {
				if (isBlue != arr[r][c]) { // 다른 색이 등장하면 false return
					return false;
				}
			}
		}

		// 여기까지 도달하면 같은 색상의 정사각형 완성
		return true;
	}
}
