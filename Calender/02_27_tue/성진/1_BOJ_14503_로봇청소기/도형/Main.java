package BOJ._14503_로봇청소기;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		// 0 : 북, 1: 동, 2: 남, 3: 서
		int dir = sc.nextInt();
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 얘도 북동남서
		int[] dr = {-1,0,1,0};
		int[] dc = {0,1,0,-1};
		
		int answer = 0;
		out:
		while(true) {
			if(map[r][c] == 0) {
//				System.out.println("청소합니다.");
				map[r][c] = 2;
				answer++;
				continue;
			}
			
			boolean flag = false;
			for(int d = 0; d < 4; d++) {
				if (r+dr[d] >=0 && r+dr[d] <N && c+dc[d] >=0 && c+dc[d]<M && map[r+dr[d]][c+dc[d]] == 0) {
					flag = true;
//					System.out.println("청소할 칸이 있습니다.");
				}
			}
			
			if (flag) {				
				dir = (dir-1+4)%4;
//				System.out.println("방향을 회전합니다.");
				if(r+dr[dir] >=0 && r+dr[dir] <N && c+dc[dir] >=0 && c+dc[dir]<M &&map[r+dr[dir]][c+dc[dir]] == 0) {
//					System.out.println("이동합니다.");
					r = r+dr[dir];
					c = c+dc[dir];
					continue out;
				}
				continue out; // 여기 한줄 안써서 틀림!!!!!!!!
			}
			
			if(r+dr[dir] >=0 && r+dr[dir] <N && c+dc[dir] >=0 && c+dc[dir]<M &&map[r-dr[dir]][c-dc[dir]] !=1) {
//				System.out.println("후진합니다.");
				r = r-dr[dir];
				c = c-dc[dir];
				continue out;
			} else {
//				System.out.println("끝냅니다.");
				break;
			}
			
			
			
		}
		System.out.println(answer);
		sc.close();
		
		
		
		
	}

}
