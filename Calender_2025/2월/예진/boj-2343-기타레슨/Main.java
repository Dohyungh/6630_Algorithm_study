/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 기타 레슨_2343
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
		
		String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[] course = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        int sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            course[i] = Integer.parseInt(st.nextToken());
            sum += course[i];
            max = Math.max(max, course[i]);
        }

        int low = max;
        int high = sum;
        int answer = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            int blue = 0;
            int total = 0;
            for (int c : course) {
                total += c;
                if (total > mid) {
                    total = c;
                    blue++;
                }
            }

            if (blue < m) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}