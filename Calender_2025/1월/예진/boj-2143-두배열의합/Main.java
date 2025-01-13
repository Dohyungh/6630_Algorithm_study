/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 두 배열의 합_2143
 * Date: 2025.01.13
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
		
		int T = Integer.parseInt(br.readLine());

		int[] prefixSumA = inputArr();
		int n = prefixSumA.length;
		int[] prefixSumB = inputArr();
		int m = prefixSumB.length;

		HashMap<Integer, Long> A = new HashMap<>();
		for (int i = 1; i < n; i++) {
			for (int j = i; j < n; j++) {
				int sum = prefixSumA[j] - prefixSumA[i-1];
				Long count = A.getOrDefault(sum, 0L);
				A.put(sum, count + 1);
			}
		}
		HashMap<Integer, Long> B = new HashMap<>();
		for (int i = 1; i < m; i++) {
			for (int j = i; j < m; j++) {
				int sum = prefixSumB[j] - prefixSumB[i-1];
				Long count = B.getOrDefault(sum, 0L);
				B.put(sum, count + 1);
			}
		}

		Long answer = 0L;
		for (int aSum : A.keySet()) {
			answer += A.get(aSum) * B.getOrDefault(T - aSum, 0L);
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	private static int[] inputArr() throws IOException {
		int size = Integer.parseInt(br.readLine());
		int[] arr = new int[size+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= size; i++) arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());

		return arr;
	}
}