package soft_6247_자동차테스트;

import java.io.*;
import java.util.*;

public class Main {
	static int n, q;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		arr = new int[n];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr); // 입력받은 연비를 오름차순으로 정렬

		for (int i = 0; i < q; i++) {
			int m = Integer.parseInt(br.readLine());

			int idx = Arrays.binarySearch(arr, m); // 이분 탐색으로 m의 위치 검색

			if (idx <= 0 || idx == n - 1) { // m이 존재하지 않거나 가장 처음 위치 또는 마지막 위치일 때는 가능한 경우가 없다.
				bw.write(0 + "\n");
			} else {
				// 가능한 경우가 존재한다면
				// 경우의 수는 m의 왼쪽 숫자의 개수 * m의 오른쪽 숫자의 개수
				bw.write(idx * (n - 1 - idx) + "\n");
			}
		}

		bw.flush();
	}
}
