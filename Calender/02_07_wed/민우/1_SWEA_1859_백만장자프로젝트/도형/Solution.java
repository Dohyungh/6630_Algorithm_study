package SWEA._1859_백만장자프로젝트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	//생각보다 숫자가 크구만
//    System.out.println(Integer.MIN_VALUE);  // -2 147 483 648        
//    System.out.println(Integer.MAX_VALUE);  // 2 147 483 647

	// 답이 4 995 633 799 까지 나옴.
	
	// N <= 1 000 000 개가 나오고
	// 각 값이 <= 10 000 이니까 두 개 곱하면
	// 10 000 000 000 이 최댓값임. 
	
	static long ans = 0; // long!!
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = 0; // 각 테케 마다 초기화 꼭 시켜줘야 함.
			get_ans(arr,0);
			System.out.printf("#%d %d%n",tc,ans);
			
		}
	}
	//이런걸 DP 라 하나봄 ( 큰 문제를 작은 문제로 )
	public static void get_ans(int[] arr, int start) { // start 변수가 필요함. 그래야 안에서 호출할 때 범위를 지정 가능.
		// 안그러면 arr를 계속 짤라내서 복사해줘야하는데 그거 보다는 이게 나을 듯.
		// 재귀 쓸 때 파라미터를 뭘 어떻게 쓸지 잘 생각하는게 중요한 듯! 연습필요
		
		long max = Long.MIN_VALUE; // long!!
		int maxIdx = -1;
		
		for (int i = start; i<arr.length; i++) { // 최댓값과 인덱스 찾기
			if (arr[i] >= max) {
				max = arr[i];
				maxIdx = i;
			}
		}

		for (int i = start; i<maxIdx; i++) { // static answer 변수 업데이트
			ans += max - arr[i];
		}
		
		if (maxIdx != arr.length-1) { // 정지 조건이 꼭 필요함
			get_ans(arr, maxIdx+1); // 고 다음 부터 다시 start
		}
		
		
	}
	

}
