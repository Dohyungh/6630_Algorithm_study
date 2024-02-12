package 색종이_2567_240212;

import java.util.Scanner;

public class Main {

	static boolean[][] covered;
	static int answer;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		covered = new boolean[101][101];
		answer = 0;
		int N = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			// 추가되는 색종이의 각 면적을 확인할거다
			for (int r = x; r < x+10; r++) {
				for (int c = y; c < y+10; c++) {
					// 이미 덮힌 부분은 변화가 없으니, 안덮힌 부분만 확인할거다
					if (!covered[r][c]) {
						sum(r, c);
						// 둘레값 갱신 했으면 true로 바꿔주기
						covered[r][c] = true;
					}
				}
			}
		}
		System.out.println(answer);
		sc.close();
	}
	
	public static void sum(int x, int y) {
		// 인접한 4개의 곳 중, covered인 이웃을 카운트해서 뺄거야
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = x + dr[i];
			int nc = y + dc[i];
			// 이웃이 범위안에 있으면 확인, 범위 밖이면 확인할 필요없음. 어차피 새로운 둘레가 될거야
			if (nr >= 0 && nr <= 100 && nc >= 0 && nc <= 100) {
				if (covered[nr][nc]) cnt++;
			}
		}
		// 더하려는 도형 둘레 +4, - 겹치는 부분 *2 
		answer = answer + 4 - (cnt * 2);
	}
}
