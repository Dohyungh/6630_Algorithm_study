package SWEA._1860_진기의최고급붕어빵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 건방지네 진기
		// 손님 오는 시간 => 누적합
		
		// 손님 오는 시간 별로 카운트 배열로 만들어서
		// 몇초에 몇명이 오는 지 보고
		// 몇초까지 몇개나 만들어야 하는지 보기 위해 오는 사람의 수를 계속 더해서 sum 변수에 저장
		// 해당 시간 까지 만들 수 있는 붕어빵 개수와 비교해서
		// 못 만들면 impossible
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[11112];
			
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			
			for (int i = 0; i<N; i++) { // count 배열
				arr[Integer.parseInt(st2.nextToken())]++;
			}
			
			boolean flag = true;
			int sum = 0; // 지금까지 만들었어야 하는 붕어빵 개수
			for (int i = 0; i<11112; i++) {
				sum += arr[i]; // 손님 추가
				if ((i/M)*K < sum) { //(i/M)*K i 초까지 만들 수 있는 붕어빵 수 vs i초까지 오는 손님 수
					flag = false;
					break;
				}
			}
			
			if (flag) {
				System.out.printf("#%d Possible%n",tc);
			} else {
				System.out.printf("#%d Impossible%n", tc);
			}
			
		}
	}

}
