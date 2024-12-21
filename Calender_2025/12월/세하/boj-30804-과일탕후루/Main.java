package boj_30804_과일탕후루;

import java.io.*;
import java.util.*;

public class Main {
	static int N, ans;
	static int[] arr, fruits;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N]; // 탕후루 배열
		fruits = new int[10]; // 과일 종류 별 개수 배열

		ans = Integer.MIN_VALUE; // 과일 개수의 최댓값

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		sol(0, 0, 0, 0);

		System.out.println(ans);
	}

	public static void sol(int left, int right, int cnt, int kinds) {
		if (right >= N) {
			return;
		}

		// 오른쪽 포인터의 과일이 새로운 과일이면 종류 + 1
		if (fruits[arr[right]] == 0) {
			kinds++;
		}

		// 도달한 과일 수 및 배열 길이 증가
		fruits[arr[right]]++;
		cnt++;
		
		// 과일 종류가 2개 초과일 때 
		while (kinds > 2) {
			fruits[arr[left]]--; // 왼쪽 포인터가 가리켰던 과일 수 - 1
			cnt--; // 배열 길이 감소
			
			if(fruits[arr[left]] == 0) {
				kinds--; // 왼쪽 포인터가 가리켰던 과일이 하나 남았었다면 종류 - 1
			}
			
			left++; // 왼쪽 포인터 오른쪽으로 한 칸 이동
		}

		// 최댓값 갱신
		ans = Math.max(cnt, ans);

		sol(left, right + 1, cnt, kinds);
	}
}
