package BOJ_2999_비밀이메일;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		char[] input = sc.nextLine().toCharArray();					// 입력값 문자 배열로 저장
		
		int length = input.length;									// 문자 길이 저장
		int col = 0;												// 나누어 떨어지는 두 수중 작은 수가 열 길이(문제에서는 자꾸 행이라고 하는데 이해 안됨)
		
		for (int i = (int)Math.sqrt(length); i > 0; i--) {			// 문자 길이의 루트값부터 내려가면서 나눠보자
			if (length % i == 0) {									// 나누어 떨어지는 수를 찾으면 저장
				col = i;
				break;
			}
		}
		
		for (int c = 0; c < col; c++) {								// 행길이만큼 반복
			for (int i = c; i < length; i += col) {					// 시작점부터 문자배열 길이 전까지 행 길이를 더해가며 반복
				System.out.print(input[i]);
			}
		}
		sc.close();
	}
}