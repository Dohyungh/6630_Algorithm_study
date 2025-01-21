/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 합이 0인 네 정수_7453
 * Date: 2025.01.21
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
		int[] A = new int[n];
		int[] B = new int[n];
		int[] C = new int[n];
		int[] D = new int[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}

		int[] AB = new int[n*n];
		int[] CD = new int[n*n];
		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				AB[idx] = A[i] + B[j];
				CD[idx++] = C[i] + D[j];
			}
		}
		Arrays.sort(AB);
		Arrays.sort(CD);

		long answer = 0;
		int abIdx = 0;
		int cdIdx = n*n - 1;
		while (abIdx < n*n && cdIdx >= 0) {
			int gap = AB[abIdx] + CD[cdIdx];
			if (gap > 0) cdIdx--;
			else if (gap < 0) abIdx++;
			else {
				long abCnt = 0;
				long cdCnt = 0;
				int ab = AB[abIdx];
				int cd = CD[cdIdx];
				while (abIdx < n*n && AB[abIdx] == ab) {
					abCnt++;
					abIdx++;
				}
				while (cdIdx >= 0 && CD[cdIdx] == cd) {
					cdCnt++;
					cdIdx--;
				}

				answer += abCnt * cdCnt;
			}
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}