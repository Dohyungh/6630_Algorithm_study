package boj_15666_N과M12;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] arr, sel;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		sel = new int[M];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr); // 오름차순으로 정렬

		pow(0, 0);

		bw.flush();
	}

	public static void pow(int idx, int s_idx) throws IOException {
		// 다 골랐으면 출력
		if (s_idx == M) {
			for (int i = 0; i < M; i++) {
				bw.write(sel[i] + " ");
			}
			bw.write("\n");
			return;
		}

		for (int i = idx; i < N; i++) {
			// i가 0보다 클 때, 이전 숫자와 같을 경우에는 중복이므로 continue
			if (i > 0 && arr[i] == arr[i - 1]) {
				continue;
			}
			sel[s_idx] = arr[i]; // 숫자 고르고
			pow(i, s_idx + 1); // 다음 숫자 고르기
		}
	}
}
