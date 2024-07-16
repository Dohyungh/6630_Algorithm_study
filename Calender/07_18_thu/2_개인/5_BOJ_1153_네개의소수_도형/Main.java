package BOJ._1153_네개의소수;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 숫자를 2개로 쪼개고, 다시 각각을 2개로 쪼개는 식으로 할 것임
 * 마지막에 숫자 4개가 나오면, 그 4개의 숫자가 모두 소수인지 판별하고, (에라토스테네스의 체)
 * 모두 소수일 때 정답 출력하고 return;
 * 
 */


public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// 소수들을 저장할 변수
		int i, j, i_rest, j_rest = 0;
		
		// split과 rest로 쪼갠다
		// (1, N-1), (2, N-2), (3, N-3), ... (N/2, N/2) 까지!
		for (int split = 1; split <= N/2; split++) {
			
			int rest = N - split;
			
			// 다시 i 와 i_rest, j 와 j_rest로 쪼개고,
			// 그 네개의 수가 모두 소수일 때 순서대로 정렬하고 출력하면 끝!
			for (i = 1; i <= split/2; i++) {
				for (j = 1; j <= rest/2; j++) {
					i_rest = split - i;
					j_rest = rest - j;
					
					// 모두 소수인지 판별
					if (isPrime(i) && isPrime(i_rest) && isPrime(j) && isPrime(j_rest)) {
						
						// 정렬 후 출력
						int[] answer = {i, j, i_rest, j_rest};
						Arrays.sort(answer);
						for (int c = 0; c < 4; c++) {
							System.out.print(answer[c] + " ");
						}
						return;
					}
				}
			}
		}
		System.out.println(-1);
		
		sc.close();
	}
	
	// 소수인지 판별 (에라토스테네스의 체)
	public static boolean isPrime(int N) {
		
		// 1은 소수가 아니다
		if (N == 1) return false;
		
		// 제곱근까지만 해보면 된다.
		int sqrt = (int) Math.sqrt(N);
		
		// 2부터 제곱근까지 나눠봤을 때 나누어 떨어지면 소수가 아니다.
		for (int i = 2; i <= sqrt; i++) {
			if (N % i == 0) {
				return false;
			}
		}
		
		// 여기까지 도달했으면 소수이다!
		return true;
		
	}

}
