/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 용액_2467
 * Date: 2025.01.03
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
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int a = 0, b = 0;
        int left = 0;
        int right = n-1;
        int min = 2_000_000_000;

        while (left < right && min > 0) {
            int tmp = nums[left] + nums[right];
            int gap = Math.abs(tmp);
            if (gap < min) {
                min = gap;
                a = nums[left];
                b = nums[right];
            }
            if (tmp < 0) left++;
            else if (tmp > 0) right--;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(a).append(" ").append(b);
        bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}