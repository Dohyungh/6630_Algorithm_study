package 영식이와친구들_1592_240212;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int member = sc.nextInt();
		int limit = sc.nextInt();
		int passDist = sc.nextInt();
		
		int[] recieve = new int[member];
		int who = 0;
		int throwCnt = 0;
		
		// 원형인덱스 사용
		while(true) {
			// 누가 받았어?
			int cnt = ++recieve[who];

			// 받은 거 몇번 쨰야?
			if (cnt == limit) break;		// limit이면 종료
			// 짝수면 반시계방향으로 전달, 던진횟수 증가 - 이때, 인덱스가 음수로 가면 모듈러 사용이 불가해서 인원수만큼 더해준다
			else if (cnt%2 == 0) {
				who = ((who - passDist + member) % member);
				throwCnt++;
			// 홀수면 반시계방향으로 전달, 던진횟수 증가
			} else {
				who = (who + passDist) % member;
				throwCnt++;
			}
		}
		
		System.out.println(throwCnt);
		sc.close();
	}
}
