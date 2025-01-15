/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 세 용액_2473
 * Date: 2025.01.15
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		long[] liq = new long[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) liq[i] = Long.parseLong(st.nextToken());
		Arrays.sort(liq);

		long min = 3_000_000_000L;
		long[] answer = new long[3];

		out: for (int i = 0; i < n - 2; i++) {

			int left = i+1;
			int right = n-1;

			while (left < right) {

				long sum = liq[i] + liq[left] + liq[right];
				long gap = Math.abs(sum);
				if (gap < min) {
					min = gap;
					answer = new long[] {liq[i], liq[left], liq[right]};
					if (min == 0) break out;
				}

				if (sum < 0) left++;
				else if (sum > 0) right--;
			}
		}

		Arrays.sort(answer);
		for (long num : answer) bw.write(String.valueOf(num) + " ");
		bw.flush();
		bw.close();
		br.close();
	}
}