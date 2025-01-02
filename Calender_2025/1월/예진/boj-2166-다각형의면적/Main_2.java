/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 다각형의 면적_2166
 * Date: 2025.01.02
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
		long[][] point = new long[n+1][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			point[i] = new long[] {x, y};
		}
		point[n] = point[0];

		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += point[i][0]*point[i+1][1] - point[i+1][0]*point[i][1];
		}

		double answer = Math.abs(sum) / 2.00;
		bw.write(String.format("%.1f", answer));
		bw.flush();
		bw.close();
		br.close();
	}
}