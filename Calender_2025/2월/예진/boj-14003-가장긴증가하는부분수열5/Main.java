/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 가장 긴 증가하는 부분 수열 5_문제번호
 * Date: 2025.02.28
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static int[] dp;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
        dp = new int[n];            // 빠른 dp 갱신용
        int[] nums = new int[n];    // 실제 순서대로 입력된 수
        int[] length = new int[n];  // 실제 순서대로 입력된 각 수까지의 증가수열 길이
        int maxIdx = 0;             // 가장 긴 증가하는 부분 수열 마지막 숫자의 실제 순서
        int dpIdx = 1;              // 지금까지의 가장 긴 증가하는 부분 수열 길이

        st = new StringTokenizer(br.readLine(), " ");
        nums[0] = Integer.parseInt(st.nextToken());
        dp[0] = nums[0];
        length[0] = 1;

        for (int i = 1; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if (nums[i] > dp[dpIdx-1]) {
                dp[dpIdx++] = nums[i];
                length[i] = dpIdx;
                maxIdx = i;
            } else {
                length[i] = findIdx(nums[i], dpIdx-1) + 1;
                dp[length[i]-1] = nums[i];
            }
        }

        bw.write(String.valueOf(dpIdx) + "\n");

        Stack<Integer> stack = new Stack<>();
        stack.add(nums[maxIdx]);
        int cnt = dpIdx - 1;    // 더 찾아야 하는 개수
        int idx = maxIdx - 1;   // 탐색할 인덱스
        while (cnt > 0 && idx >= 0) {
            while (nums[idx] >= nums[maxIdx] || length[idx] != length[maxIdx] - 1) {
                idx--;
            }
            stack.add(nums[idx]);
            maxIdx = idx--;
            cnt--;
        }

        while (!stack.isEmpty()) bw.write(String.valueOf(stack.pop()) + " ");
		bw.flush();
		bw.close();
		br.close();
	}

    public static int findIdx(int num, int h) {
        int low = 0;
        int high = h;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (dp[mid] > num) high = mid;
            else if (dp[mid] < num) low = mid + 1;
            else if (dp[mid] == num) return mid;
        }
        return high;
    }
}