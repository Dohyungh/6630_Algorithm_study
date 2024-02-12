package 블랙잭_2798_240212;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int limit = sc.nextInt();
		int[] nums = new int[N];
		
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		// 최대값 초기화는 최소값으로
		int max = Integer.MIN_VALUE;

		// 3중 for문 간다. 재귀보다 이게 빠를것 같아서
		for (int i = 0; i < N-2; i++) {
			for (int j = i+1; j < N-1; j++) {
				for (int k = j+1; k < N; k++) {
					int sum = nums[i]+nums[j]+nums[k];
					// 합이 limit이하고, max보다 크면 갱신!
					if (sum <= limit && sum > max) max = sum;
				}
			}
		}
		System.out.println(max);
		sc.close();
	}
}
