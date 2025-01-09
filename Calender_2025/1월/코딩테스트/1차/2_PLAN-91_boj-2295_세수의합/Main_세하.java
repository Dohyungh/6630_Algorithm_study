package boj_2295_세수의합;

import java.io.*;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		HashSet<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			set.add(arr[i]);
		}

		int ans = 0;

		for (int i = 0; i < N; i++) {
			label: for (int j = i + 1; j < N; j++) {
				for (int k = N - 1; k > j + 1; k--) {
					if (set.contains(arr[k] - (arr[i] + arr[j]))) {
						ans = Math.max(ans, arr[k]);
						break label;
					}
				}
			}
		}

		System.out.println(ans);
	}
}
