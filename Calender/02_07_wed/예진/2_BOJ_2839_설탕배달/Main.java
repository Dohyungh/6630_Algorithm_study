package 설탕배달_2839_240205;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();				// 배달할 설탕의 양
		int answer = Integer.MAX_VALUE;		// 봉지개수 최소값, 정수의 최대값으로 초기화
		
		for (int i = N/5; i >= 0; i--) {	// 5키로 봉지의 최대 사용개수부터 0개까지
			int rest = N-(5*i);				// 5키로 봉지 사용 후 나머지
			if (rest%3 == 0) {				// 나머지를 3으로 나누어 떨어지면
				if (i+(rest/3) < answer)	// 봉지개수 최소값과 비교 및 갱신
					answer = i+(rest/3);
			}
		}
		
		if (answer == Integer.MAX_VALUE) answer = -1; 	// 초기화 값에서 변화가 없으면 정확한 양을 배달할 수 없으므로 -1
		System.out.println(answer);
		
		sc.close();
	}
}
