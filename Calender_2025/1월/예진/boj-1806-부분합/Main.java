/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 부분합_1806
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
		
		String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int S = Integer.parseInt(input[1]);

        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int min = n+1;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (left <= right && right < n) {
            while (right < n && sum < S) sum += nums[right++];
            while (left < n && sum - nums[left] >= S) sum -= nums[left++];

            if (sum >= S) min = Math.min(min, right - left);

            sum -= nums[left++];
        }

        if (min == n+1) bw.write("0");
        else bw.write(String.valueOf(min));
		bw.flush();
		bw.close();
		br.close();
	}
}