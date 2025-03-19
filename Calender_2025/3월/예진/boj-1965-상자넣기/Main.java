/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 상자넣기_1965
 * Date: 2025.03.20
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[] dp;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		dp = new int[n];

		st = new StringTokenizer(br.readLine(), " ");
		dp[0] = Integer.parseInt(st.nextToken());

		int answer = 1;
		for (int i = 1; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (dp[answer-1] < num) dp[answer++] = num;
			else {
				int loc = bs(num, answer-1);
				dp[loc] = num;
			}
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	public static int bs(int num, int maxIdx) {
		int low = 0;
		int high = maxIdx;

		int mid = 0;
		while (low < high) {
			mid = (low + high) / 2;
			if (dp[mid] > num) high = mid;
			else if (dp[mid] < num) low = mid + 1;
			else return mid;
		}
		return low;
	}
}