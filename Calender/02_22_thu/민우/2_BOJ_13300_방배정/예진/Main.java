package BOJ_13300_방배정;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();							// 총 학생 수
		int max = sc.nextInt();							// 한 방의 최대 인원수
		int[][] students = new int [2][7];				// 성별에 따른 학년별 인원수
		
		for (int i = 0; i < N; i++) {					// 입력
			students[sc.nextInt()][sc.nextInt()]++;
		}
		
		int answer = 0;									// 필요한 방의 개수
		
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < 7; j++) {
				if (students[i][j] % max == 0) {				// 학생수가 방 최대인원 수로 나누어 떨어지면
					answer += students[i][j] / max;				// 그 몫을 답에 합함
				} else answer += (students[i][j] / max) + 1;	// 나머지가 있으면 몫에 +1하여 답에 합함
			}
		}
		
		System.out.println(answer);								// 출력
		sc.close();												// 문단속
	}
}