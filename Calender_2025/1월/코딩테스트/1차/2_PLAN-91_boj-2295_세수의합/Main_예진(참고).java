package 코테_boj_2295_세수의합;

/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 코테_boj_2295_세수의합
 * Date: 2025.01.09
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
		
		for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(br.readLine());
		Arrays.sort(nums);
		
		Set<Integer> sum = new HashSet<>();
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) sum.add(nums[x] + nums[y]);
		}
		
		out: for (int k = n-1; k >= 0; k--) {
			for (int z = 0; z < n; z++) {
				if (sum.contains(nums[k] - nums[z])) {
					bw.write(String.valueOf(nums[k]));
					break out;
				}
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}