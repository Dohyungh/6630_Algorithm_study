package boj_2467_용액;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int min = Integer.MAX_VALUE; // 혼합된 용액 특성값의 절댓값의 최소 

		int lft = 0;
		int rgt = N - 1;

		int lft_ans = arr[lft]; // 혼합된 용액의 특성값이 최소일 때 왼쪽 포인터가 가리키는 값 
		int rgt_ans = arr[rgt]; // 혼합된 용액의 특성값이 최소일 때 오른쪽 포인터가 가리키는 값 

		while (lft < rgt) {
			int sum = arr[lft] + arr[rgt]; // 두 용액의 합 
			int abs = Math.abs(sum); // 두 용액의 합의 절댓값 
			
			// 절댓값이 저장된 최솟값 보다 작으면 최솟값 갱신 
			if (min > abs) {
				min = abs;
				// 현재 두 포인터가 가리키는 값 저장 
				lft_ans = arr[lft];
				rgt_ans = arr[rgt];
			}
			
			// 합이 0이면 더 이상 탐색 불필요 
			if (sum == 0) {
				break;
			}
			
			// 합이 0보다 크면 오른쪽 포인터 왼쪽으로 한 칸 이동 
			if (sum > 0) {
				rgt--;
			}
			// 합이 0보다 작으면 왼쪽 포인터 오른쪽으로 한 칸 이동 
			else {
				lft++;
			}
		}

		System.out.println(lft_ans + " " + rgt_ans);
	}
}
