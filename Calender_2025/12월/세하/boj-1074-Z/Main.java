package boj_1074_Z;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int result = sol(r, c, N, (int) Math.pow(2, N - 1));

		System.out.println(result);
	}

	public static int sol(int r, int c, int N, int halfSize) {
		// 기저 조건
		if (N == 0) {
			return 0;
		}

		if (r < halfSize) {
			// 1사분면 (오른쪽 위)
			if (c >= halfSize) {
				return sol(r, c - halfSize, N - 1, halfSize / 2) + halfSize * halfSize;
			}
			// 2사분면 (왼쪽 위)
			else {
				return sol(r, c, N - 1, halfSize / 2);
			}
		} else {
			// 3사분면 (왼쪽 아래)
			if (c < halfSize) {
				return sol(r - halfSize, c, N - 1, halfSize / 2) + 2 * halfSize * halfSize;
			}
			// 4사분면 (오른쪽 아래)
			else {
				return sol(r - halfSize, c - halfSize, N - 1, halfSize / 2) + 3 * halfSize * halfSize;
			}
		}
	}
}
