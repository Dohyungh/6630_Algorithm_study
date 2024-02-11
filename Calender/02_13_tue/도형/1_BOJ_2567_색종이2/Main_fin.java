package BOJ._2567_색종이2;

import java.util.Scanner;

public class Main_fin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int [102][102]; //padding
		
		int N = sc.nextInt();
		
		for (int n = 0; n<N; n++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for (int i = x; i<x+10; i++) {
				for (int j = y; j<y+10; j++) {
					arr[i+1][j+1] = 1; // 테두리 사방탐색 할 수 있게, (0,0) -> (1,1) (90,90) -> (100,100)
				}
			}
		}
		
		int answer =0;
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		for (int i = 1; i<101; i++) { // padding 했으니까 1부터 100까지 검사 가능
			for (int j = 1; j<101; j++) {
				if (arr[i][j]==1) {
					int count = 0;
					for (int d = 0; d<4; d++) count+=arr[i+dx[d]][j+dy[d]];
					if (count == 3) answer++;
					if (count == 2) answer+=2;
				}
			}
		}
		System.out.println(answer);
		sc.close();
	}

}
