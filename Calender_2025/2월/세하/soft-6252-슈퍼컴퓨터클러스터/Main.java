package soft_6252_슈퍼컴퓨터클러스터;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		int[] computers = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			computers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(computers);

		int start = computers[0];
		int end = (int) Math.sqrt(B) + start;
		int ans = start;

		while (start <= end) {
			int mid = (start + end) / 2;

			long sum = 0;

			for (int i = 0; i < N; i++) {
				if (computers[i] < mid) {
					sum += (long) Math.pow(mid - computers[i], 2);
				}
			}

			if (sum > B) {
				end = mid - 1;
			} else {
				ans = mid;
				start = mid + 1;
			}
		}

		System.out.println(ans);
	}
}
