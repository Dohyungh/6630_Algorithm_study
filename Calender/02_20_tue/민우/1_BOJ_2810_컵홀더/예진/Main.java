package BOJ_2810_컵홀더;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.nextLine());
		char[] input = sc.nextLine().toCharArray();
		
		int answer = 1;						// 첫 좌석 앞에 언제나 홀더가 있음
		int idx = 0;						// 첫 좌석부터 검사
		
		while (idx < N) {					// 모든 좌석을 검사할 동안 반복
			if (input[idx] == 'S') {		// 혼석이면 인덱스 1증가, 홀더 카운트 1증가
				idx++;
				answer++;
			} else if (input[idx] == 'L') {	// 커플석이면 인덱스 2증가, 홀더 카운트 1증가
				idx += 2;
				answer++;
			}
		}
		
		if (answer > N) answer = N;			// 홀더수가 좌석수보다 많아도 좌석수 만큼만 쓸 수 있음
		
		System.out.println(answer);
		sc.close();
	}
}