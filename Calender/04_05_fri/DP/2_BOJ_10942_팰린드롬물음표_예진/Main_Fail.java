package boj_10942_팰린드롬;

import java.util.Scanner;

public class Main2 {

	static int N;
	static int[] nums;
	static int[] dp;
	static StringBuilder sb;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		nums = new int[N];
		dp = new int[N];
		sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		
		int order = sc.nextInt();
		for (int i = 0; i < order; i++) {
			int st = sc.nextInt()-1;
			int ed = sc.nextInt()-1;
			if ((ed - (st - 1)) % 2 != 0) {
				sb.append(oddCheck(st, ed)+"\n");
			} else {
				sb.append(evenCheck(st, ed)+"\n");
			}
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static int evenCheck(int st, int ed) {
		int mid = (st + ed) / 2;
		
		int gap = ed - (st - 1);
		if (dp[mid] % 2 == 0 && dp[mid] >= gap) return 1;
		
		int depth = dp[mid] / 2;
		
		for (int i = depth; i < gap/2; i++) {
			if (nums[mid-i] != nums[mid+(i+1)]) {
				dp[mid] = Math.max(dp[mid], 2*i);
				if (dp[mid] == 0) dp[mid] = 1;
				return 0;
			}
		}
		
		dp[mid] = Math.max(dp[mid], gap);
		return 1;
	}

	private static int oddCheck(int st, int ed) {
		int mid = (st + ed) / 2;
		
		int gap = ed - (st - 1);
		if (dp[mid] % 2 != 0 && dp[mid] >= gap) return 1;
			
		int depth = dp[mid] / 2;
		for (int i = depth+1; i <= gap/2; i++) {
			if (nums[mid-i] != nums[mid+i]) {
				dp[mid] = Math.max(dp[mid], 1 + 2*(i-1));
				return 0;
			}
		}
		dp[mid] = Math.max(dp[mid], gap);
		return 1;
	}

}
