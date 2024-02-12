package 벌집_2292_240212;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int num = 1;
		int cnt = 1;
		int answer = 1;
		
		// 각 겹마다 수의 범위를 두고 그 안에 N이 포함되면 종료
		// 1로부터 몇번째 바깥인지만 찾으면 그게 최소 이동 수
		while(N != 1) {
			if (N > num && N <= num+(6*cnt)) {
				answer = cnt+1;
				break;
			} else num += 6*cnt++;
		}
		
		System.out.println(answer);
		sc.close();
	}
}
