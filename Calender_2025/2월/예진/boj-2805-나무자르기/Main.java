/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 나무 자르기_문제번호
 * Date: 2025.02.03
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[] trees;
	static int m;
	static int answer;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		trees = new int[n];
		m = Integer.parseInt(input[1]);
		st = new StringTokenizer(br.readLine(), " ");
		int max = 0;
		for (int i = 0; i < n; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, trees[i]);
		}

		binarySearch(1, (int)max);

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	private static void binarySearch(int low, int high) {
		if (low > high) return;

		int mid = (low + high) / 2;

		long sum = cut(mid);
		if (sum == m) answer = mid;
		else if (sum > m) {
			answer = mid;
			binarySearch(mid + 1, high);
		} else binarySearch(low, mid - 1);
	}

	private static long cut(int height) {
		long sum = 0;
		for (long tree : trees) {
			sum += Math.max(tree-height, 0);
		}
		return sum;
	}
}