/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 공유기 설치_2110
 * Date: 2025.02.13
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[] house;
	static int n;
	static int c;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		c = Integer.parseInt(input[1]);

		house = new int[n];
		for (int i = 0; i < n; i++) house[i] = Integer.parseInt(br.readLine());
		Arrays.sort(house);

		int low = 1;
		int high = (house[n-1] - house[0]) / (c-1);

		int mid;
		int answer = 0;
		while (low <= high) {
			mid = (low + high) / 2;

			if (isPossible(mid)) {
				answer = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean isPossible(int dist) {
		int cnt = c;
		int idx = 0;
		while (cnt > 0 && idx < n) {
			int a = house[idx];
			while (idx < n && house[idx] - a < dist) idx++;
			cnt--;
		}
        return cnt <= 0;
    }
}