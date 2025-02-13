/**
 * Author: yngbao97, Yuk Yejin
 * Problem: K번째 수_1300
 * Date: 2025.02.13
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int n;
	static int k;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        int answer = 0;

		int low = 1;
		int high = k;
		int mid;
		while (low <= high) {
			mid = (low + high) / 2;

			int cnt = getMinCnt(mid);
			if (cnt < k) low = mid + 1;
			else {
				answer = mid;
				// 개수가 일치해도 최적의 수를 찾기 위해서는 끝까지 줄여서 확인해야 함
//				if (cnt == k) break;
				high = mid - 1;
			}
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	public static int getMinCnt(int num) {
		int cntSum = 0;
		for (int i = 1; i <= num && i <= n; i++) {
			cntSum += Math.min(num / i, n);
		}
		return cntSum;
	}
}