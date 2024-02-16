package BOJ._2851_슈퍼마리오;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// prefixSum[] 만들었다가 지움
		// 땡스투 민우
		// 누적합 배열은 나중에 구간합을 구해야 할 때나 만드는 것이지
		// 계속 더해가기만 할 때는 그냥 변수하나로 퉁칠 수 있음.

		int sum = 0 ;
		int answer = 0;
		for (int i = 0; i<10; i++) {
			sum+=sc.nextInt();
			if (Math.abs(answer-100) >= Math.abs(sum-100)) answer = sum;
		}
		
		System.out.println(answer);
	}

}
