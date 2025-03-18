/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 문자열 게임 2_20437
 * Date: 2025.03.18
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static final int TOIDX = -97;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {

			char[] str = br.readLine().toCharArray();
			int k = Integer.parseInt(br.readLine());
			if (k == 1) {
				bw.write("1 1\n");
				continue;
			}

			int[] start = new int[26];
			int[] cnt = new int[26];
			int min = Integer.MAX_VALUE;
			int max = 0;

			for (int i = 0; i < str.length; i++) {
				int c = str[i] + TOIDX;
				if (cnt[c] == 0) start[c] = i;
				if (++cnt[c] == k) {
					int length = i - start[c] + 1;
					min = Math.min(min, length);
					max = Math.max(max, length);

					start[c]++;
					while(str[start[c]] != str[i]) start[c]++;
					cnt[c]--;
				}
			}

			if (max == 0) bw.write("-1\n");
			else bw.write(String.valueOf(min) + " " + String.valueOf(max) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}