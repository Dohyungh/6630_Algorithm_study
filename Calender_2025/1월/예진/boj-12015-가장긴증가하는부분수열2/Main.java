/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 가장 긴 증가하는 부분 수열 2_12015
 * Date: 2025.01.24
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
		int[] nums = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());

		List<Integer> lis = new ArrayList<>();
		lis.add(nums[0]);
		for (int i = 1; i < n; i++) {
			int last = lis.get(lis.size() - 1);
			if (last < nums[i]) lis.add(nums[i]);
			else if (last > nums[i]) {
				int left = 0;
				int right = lis.size() - 1;
				while (left < right) {
					int mid = (left + right) / 2;
					if (nums[i] > lis.get(mid)) left = mid + 1;
					else right = mid;
				}
				lis.set(right, nums[i]);
			}
		}

		bw.write(String.valueOf(lis.size()));
		bw.flush();
		bw.close();
		br.close();
	}
}