package BOJ_8320_직사각형을만드는방법;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();								// 숫자 입력
		int root = (int)Math.sqrt(num);						// 반복을 줄이기 위해서 루트값보다 작은 정수를 구함
		boolean[][] checked = new boolean[num+1][num+1];	// 중복 체크 배열

		int answer = 0;										// 총 직사각형 개수
		
		for (int r = 1; r <= num; r++) {					// 행은 1부터 num까지 반복
			for (int c = 1; c <= root; c++) {				// 열은 루트길이 다음부터 전부 중복임으로 제외
				
				if (r*c <= num) {							// 행렬의 곱이 최대 num이어야 꽉채운 직사각형이 될 수 있음
					if (!checked[r][c]) {					// 이전에 중복된 모양이 없어야 함
						answer++;							// 답안 개수 +1
						checked[r][c] = true;				// 중복되는 모양의 직사각형 함께 체크 표시
						checked[c][r] = true;
					}
				}
			}
		}
		
		System.out.println(answer);							// 출력
		sc.close();											// 문단속
	}
}