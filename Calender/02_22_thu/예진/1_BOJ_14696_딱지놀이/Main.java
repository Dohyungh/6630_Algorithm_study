package BOJ_14696_딱지놀이;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int round = sc.nextInt();
		
		for (int i = 0; i < round; i++) {							// 라운드 만큼 반복
			int[][] battle = new int[2][4];							// 두 사람의 각 카드 내 모양(4개)의 수
		
			for (int person = 0; person < 2; person++) {			// 두 사람꺼 입력 받을거다
				int shapeCnt = sc.nextInt();						// 카드에 그림 몇개?
				
				for (int j = 0; j < shapeCnt; j++) {
					int shape = sc.nextInt();
					
					if (shape == 4) battle[person][0]++;			// 4면 0에 카운트,
					else if(shape == 3) battle[person][1]++;		// 3이면 1에 카운트,
					else if(shape == 2) battle[person][2]++;		// 2면 2에 카운트,
					else battle[person][3]++;						// 1이면 3에 카운트
				}
			}
			
			char answer = 'D';										// 각 모양의 카운트 대소비교가 안나면 무승부
			
			// 별모양 개수 비교
			if (battle[0][0] > battle[1][0]) answer = 'A';
			else if (battle[0][0] < battle[1][0]) answer = 'B';
			
			// 동그라미 개수 비교
			else if (battle[0][1] > battle[1][1]) answer = 'A';
			else if (battle[0][1] < battle[1][1]) answer = 'B';
			
			// 네모 개수 비교
			else if (battle[0][2] > battle[1][2]) answer = 'A';
			else if (battle[0][2] < battle[1][2]) answer = 'B';
			
			// 세모 개수 비교
			else if (battle[0][3] > battle[1][3]) answer = 'A';
			else if (battle[0][3] < battle[1][3]) answer = 'B';
			
			System.out.println(answer);
			
		}
		sc.close();
	}
}