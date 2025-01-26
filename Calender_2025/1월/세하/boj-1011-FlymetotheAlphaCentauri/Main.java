package boj_1011_FlymetotheAlphaCentauri;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int t = 0;

		while (t++ < T) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int diff = y - x;

			bw.write(sol(diff) + "\n");
		}

		bw.flush();
	}

	public static int sol(int diff) {
		int n = (int) Math.sqrt(diff);
		int pow = n * n;

		// 거리가 (n의 제곱)이면 이동횟수는 (n * 2 - 1)
		if (pow == diff) {
			return n * 2 - 1;
		}

		// 거리가 (n의 제곱) 초과, (n의 제곱 + n) 이하이면 이동횟수는 (n * 2)
		if (pow + n >= diff) {
			return n * 2;
		}

		// 거리가 (n의 제곱 + n) 초과이면 이동횟수는 (n * 2 + 1)
		return n * 2 + 1;
	}
}
