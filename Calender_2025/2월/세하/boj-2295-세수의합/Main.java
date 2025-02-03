package boj_2295_세수의합;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr); // 오름차순 정렬
		
		// set에 모든 x + y의 경우의 수 저장 
		for (int i = 0; i < N - 1; i++) {
			for (int j = i; j < N - 1; j++) {
				set.add(arr[i] + arr[j]);
			}
		}

		for (int i = N - 1; i > 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				// x + y = k - z 이므로
				// set에 k - z 가 존재하는지 찾기
				// 오름차순으로 정렬되어 있으므로 배열의 마지막 부터 탐색 후
				// 찾으면 바로 출력 
				if (set.contains(arr[i] - arr[j])) {
					System.out.println(arr[i]);
					return;
				}
			}
		}
	}
}
