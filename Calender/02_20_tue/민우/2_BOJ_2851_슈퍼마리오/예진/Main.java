package BOJ_2851_슈퍼마리오;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int front = 0;							// 이전 합계
		int sum = 0;							// 다음 수를 더한 후 합계
		
		while (sc.hasNextInt()) {				// 다음 읽을 수가 없을 때까지
			front = sum;						// 다음 수를 더하기 전 합계를 저장
			sum += sc.nextInt();				// 다음 수 더하기
			if (sum > 100) break;				// 합계가 100을 넘으면 반복 종료
		}
		
		if (100-front < sum-100) sum = front;	// 이전 합계가 100에 더 가까우면 답 변경
		
		System.out.println(sum);				// 출력
		sc.close();								// 문단속
	}
}