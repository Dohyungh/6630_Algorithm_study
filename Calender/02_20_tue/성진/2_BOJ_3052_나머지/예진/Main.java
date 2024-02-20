package BOJ_3052_나머지;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		int[] cnt = new int[42];						// 나머지가 될 수 있는 수들의 카운트 배열
		int answer = 0;									// 나머지가 서로 다른 수의 개수
		
		for(int i = 0; i < 10; i++) {
			if(cnt[sc.nextInt() % 42]++ == 0) answer++;	// 나머지 카운트에 처음 추가되는 값이면 답 +1
		}
		
		System.out.println(answer);						// 출력
		sc.close();										// 문단속
	}
}