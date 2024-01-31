package 부먹왕국의차원관문_7964_240127;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.setIn(new FileInputStream("src/부먹왕국의차원관문_7964_240127/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();            // 도시 개수
			int dist = sc.nextInt();         // 차원관문 거리조건
			
			int ans = 0;                     // 필요한 차원관문 개수
			int temp = 1;                    // 연속된 0의 개수 1부터 시작
			for (int i = 0; i < N; i++) {
				if (sc.nextInt() == 0) {     // 0을 찾았는데
					if (temp == dist) {      // 차원 간 거리조건과 길이가 같으면
						ans++;               // 차원관문 +1
						temp = 1;            // 0의 개수 초기화
					} else temp++;           // 거리조건과 길이가 같지 않으면 0의 개수만 +1
				} else temp = 1;             // 0이 아니라면 0의 개수 초기화
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
		sc.close();
	}

}
