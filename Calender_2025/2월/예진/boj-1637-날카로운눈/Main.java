/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 날카로운 눈_1637
 * Date: 2025.02.18
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static int[][] condition;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
        condition = new int[n][];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            condition[i] = new int[] {a, c, b};
            min = Math.min(min, a);
            max = Math.max(max, c);
        }

        long low = min;
        long high = max;
        long mid;

        long answer = getRangeCount(low, high);
        if (answer % 2 == 0) {
            bw.write("NOTHING");

        } else {
            while (low < high) {
                mid = (low + high) / 2;

                long cnt = getRangeCount(low, mid);
                if (cnt % 2 == 0) {
                    answer -= cnt;
                    low = mid + 1;
                } else {
                    answer = cnt;
                    high = mid;
                }
            }

            bw.write(String.valueOf(low) + " " + String.valueOf(answer));
        }

		bw.flush();
		bw.close();
		br.close();
	}

    public static long getRangeCount(long min, long max) {

        long sum = 0;
        for (int[] cond : condition) {
            long low = Math.max(min, cond[0]);
            long gap = (low - cond[0]) % cond[2];
            if (gap != 0) low += cond[2] - gap;

            long high = Math.min(max, cond[1]);

            if (high - low >= 0) {
                long cnt = (high - low) / cond[2] + 1;

                sum += cnt;
            }
        }

        return sum;
    }

}