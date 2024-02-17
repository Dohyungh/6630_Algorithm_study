package BOJ._13300_방배정;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] countingBoard = new int[12];	
		for (int i = 0; i<N; i++) {
			int gender = sc.nextInt();
			int grade = sc.nextInt();
			int idx = gender + (grade-1)*2; // ex) 1 1 -> index = 1; 1 4 -> index = 7; 표에 나온 그대로임
			
			countingBoard[idx]++;
			
		}
		
		int answer = 0;
		for (int i = 0; i<12; i++) {
			if (countingBoard[i] == 0) continue; // 아무도 없으면 넘기고
			if (countingBoard[i] % K == 0) { // 나누어 떨어지면 몫
				answer += countingBoard[i]/K;
				continue;
			}
			answer += countingBoard[i]/K +1; // 안나누어 떨어지면 몫+1 
		}
		
		System.out.println(answer);
		
		sc.close();
	}

}
