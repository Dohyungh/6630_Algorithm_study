package BOJ._2999_비밀이메일;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		int N = str.length();
		int r = 0;
		int c = 0;
		for (int i = 1; i <=Math.sqrt(N); i++) {
			if (N % i == 0) {
				r = i;
				c = N/r;
			}
		}
		for (int i = 0; i<r; i++) {
			for (int j = 0; j<c; j++) {
				System.out.print(str.charAt(i+r*j)); // 이거 좀 헷갈렸음 // 그냥 빨리 손으로 좀 쓸걸 괜히 머리 속으로 계산함
			}
		}
		sc.close();

	}

}
