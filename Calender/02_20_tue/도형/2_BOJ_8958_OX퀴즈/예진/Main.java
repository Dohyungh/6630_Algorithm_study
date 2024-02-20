package BOJ_8958_OX퀴즈;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());			// 테스트케이스 개수
		
		for (int tc = 1; tc <= T; tc++) {
			char[] result = sc.nextLine().toCharArray();	// 문자열 -> 문자 배열
			
			int score = 0;									// 점수
			int cnt = 1;									// 연속된 O의 개수
			
			for (int i = 0; i < result.length; i++) {		// 문자 배열 순회
				if (result[i] == 'O') score += cnt++;		// O이면 현재 연속된 개수만큼 점수를 더하고, 연속횟수 증가
				else cnt = 1;								// 아니면 연속된 O개수 초기화
			}
			
			System.out.println(score);						// 출력
		}
		sc.close();											// 테스트 케이스 전부 종료 후 문단속, 중요!!
	}
}