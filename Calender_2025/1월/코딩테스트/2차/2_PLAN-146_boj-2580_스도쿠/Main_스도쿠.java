package boj_2580_스도쿠;

import java.io.*;
import java.util.*;

public class Main {
	static int blank;
	static int[][] arr, ansArr, blankArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		arr = new int[9][9];
		ansArr = new int[9][9];

		blank = 0;

		for (int r = 0; r < 9; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < 9; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());

				if (arr[r][c] == 0) {
					blank++;
				}
			}
		}

		blankArr = new int[blank][2];
		int idx = 0;

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (arr[r][c] == 0) {
					blankArr[idx][0] = r;
					blankArr[idx][1] = c;
					idx++;
				}
			}
		}

		sol(0);

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				bw.write(ansArr[r][c] + " ");
			}
			bw.write("\n");
		}

		bw.flush();
	}

	static void sol(int blankNum) {
		if (blankNum == blank) {
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					ansArr[r][c] = arr[r][c];
				}
			}
			return;
		}

		int r_idx = blankArr[blankNum][0];
		int c_idx = blankArr[blankNum][1];

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}

		boolean isPossible = false;

		for (int i = 1; i <= 9; i++) {
			if (check(r_idx, c_idx, i)) {
				isPossible = true;
				arr[r_idx][c_idx] = i;
				sol(blankNum + 1);
			}
		}

		if (!isPossible) {
			return;
		}
		arr[r_idx][c_idx] = 0;
	}

	static boolean check(int r_idx, int c_idx, int num) {
		// 가로 check
		for (int c = 0; c < 9; c++) {
			if (c != c_idx && arr[r_idx][c] == num) {
				return false;
			}
		}

		// 세로 check
		for (int r = 0; r < 9; r++) {
			if (r != r_idx && arr[r][c_idx] == num) {
				return false;
			}
		}

		// 정사각형 check
		int r_start = (r_idx / 3) * 3; // (r_idx, c_idx)가 속한 정사각형의 행이 시작되는 곳
		int c_start = (c_idx / 3) * 3; // (r_idx, c_idx)가 속한 정사각형의 열이 시작되는 곳

		for (int r = r_start; r < r_start + 3; r++) {
			for (int c = c_start; c < c_start + 3; c++) {
				if (!(r == r_idx && c == c_idx) && arr[r][c] == num) {
					return false;
				}
			}
		}

		return true;
	}
}
