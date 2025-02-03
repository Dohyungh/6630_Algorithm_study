/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 예산_2512
 * Date: 2025.02.04
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
        int[] town = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        int max = 0;
        for (int i = 0; i < n; i++) {
            town[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, town[i]);
        }
        int m = Integer.parseInt(br.readLine());

        int answer = 0;
        int low = 1;
        int high = max;

        while (low <= high) {
            int mid = (low + high) / 2;
            long sum = 0;
            for (int t : town) {
                sum += Math.min(t, mid);
            }

            if (sum == m) {
                answer = mid;
                break;
            } else if (sum < m) {
                answer = mid;
                low = mid + 1;
            } else high = mid - 1;
        }

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}