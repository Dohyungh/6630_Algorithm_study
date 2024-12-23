package boj_17626_FourSquares;

import java.io.*;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];
		
		Arrays.fill(dp, 987654321); // 무한으로 초기화 
		dp[0] = 0; // 최솟값을 구하기 위해 0번째는 0으로 초기화 

		for (int i = 1; i < N + 1; i++) {
			// i보다 작은 제곱수들을 차례로 순회한다. 
			for(int j=1; j*j<=i; j++) {
				// i에서 어떤 제곱수를 뺀 수의 dp 값 중 가장 작은 것을 찾는다. 
				dp[i] = Math.min(dp[i-j*j], dp[i]);
			}
			
			dp[i]++; // i번째 dp 값은 위에서 구한 가장 작은값에서 해당 제곱수(j*j) 1가지를 더한 값이다. 
		}
		
		System.out.println(dp[N]);
	}
}
