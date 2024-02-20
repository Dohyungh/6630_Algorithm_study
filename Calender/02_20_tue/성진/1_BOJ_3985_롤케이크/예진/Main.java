package BOJ_3985_롤케이크;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int length = sc.nextInt();						// 케이크 길이
		boolean[] isTaken = new boolean[length+1];		// 각 조각의 배분 상태
		
		int people = sc.nextInt();						// 방청객 수
		int[] expect = new int[people+1];				// 각 사람별 기대한 케이크 수
		int[] get = new int[people+1];					// 각 사람별 배정받은 케이크 수
		
		for (int i = 1; i <= people; i++) {				// 사람 수 만큼 케이크 배분 반복
			int start = sc.nextInt();					// 방청객이 원하는 범위 저장
			int end = sc.nextInt();
		
			for (int j = start; j <= end; j++) {		// 원하는 케이크 범위만큼 반복
				expect[i]++;							// 기대 카운트 +1
				if (!isTaken[j]) {						// 아직 배정이 안된 부분이면
					get[i]++;							// 배정 케이크 수 +1
					isTaken[j] = true;					// 배정받은 상태로 변경
				}
			}
		}
		
		int maxExpect = 1;							// 기대한 개수가 가장 많은 사람, 기본값 첫번째 사람
		for (int i = 2; i <= people; i++) {			// 값이 같으면 갱신 안하기 때문에 최대값 중 가장 앞사람이 선택될 것
			if (expect[i] > expect[maxExpect]) maxExpect = i;
		}
		
		int maxGet = 1;						// 실제 받은 개수가 가장 많은 사람, 기본값 첫번째 사람
		for (int i = 2; i <= people; i++) {			// 값이 같으면 갱신 안됨, 최대값 중 가장 앞사람이 선택됨
			if (get[i] > get[maxGet]) maxGet = i;
		}
		
		System.out.println(maxExpect);				// 출력
		System.out.println(maxGet);
		sc.close();									// 문단속
	}
}