/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 수 찾기_1920
 * Date: 2025.01.31
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[] nums;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		nums = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(nums);

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (binarySearch(num)) bw.write("1\n");
			else bw.write("0\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean binarySearch(int num) {
		int left = 0;
		int right = nums.length - 1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			if (nums[mid] < num) left = mid + 1;
			else if (nums[mid] > num) right = mid - 1;
			else return true;
		}
		return false;
	}
}