package BOJ._14499_주사위굴리기;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		
		int K = sc.nextInt();
		
		int[][] map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int[] dice = {0,0,0,0,0,0};
		boolean moved = false;
		for (int k = 0; k < K; k++) {
			moved = false;
			int ord = sc.nextInt();
			
			if(ord ==1) {
				if(y <= M-2) {
					y++;
					dice = turnOne(dice);
					if (map[x][y] != 0) {
						dice[5] = map[x][y];
						map[x][y] = 0;
					} else {
						map[x][y] = dice[5];
					}
					moved = true;
				}
			}
			if(ord ==2) {
				if(y >= 1) {
					y--;
					dice = turnTwo(dice);
					if (map[x][y] != 0) {
						dice[5] = map[x][y];
						map[x][y] = 0;
					} else {
						map[x][y] = dice[5];
					}	
					moved = true;

				}
			}
			if(ord ==3) {
				if(x >= 1) {
					x--;
					dice = turnThree(dice);
					if (map[x][y] != 0) {
						dice[5] = map[x][y];
						map[x][y] = 0;
					} else {
						map[x][y] = dice[5];
					}	
					moved = true;

				}
			}
			if(ord ==4) {
				if(x <= N-2) {
					x++;
					dice = turnFour(dice);
					if (map[x][y] != 0) {
						dice[5] = map[x][y];
						map[x][y] = 0;
					} else {
						map[x][y] = dice[5];
					}	
					moved = true;

				}
			}
			//boolean 안쓰고 각 if 문 안에서 출력해도 됨
			if (moved) System.out.println(dice[0]);
		}
		
		
		
		sc.close();
		
		
	}
	
	public static int[] turnOne (int[] dice) {
		int[] temp = {dice[4],dice[1],dice[0],dice[3],dice[5],dice[2]};
		return temp;
	}
	public static int[] turnTwo (int[] dice) {
		int[] temp = {dice[2],dice[1],dice[5],dice[3],dice[0],dice[4]};
		return temp;
	}
	public static int[] turnThree (int[] dice) {
		int[] temp = {dice[3],dice[0],dice[2],dice[5],dice[4],dice[1]};
		return temp;
	}
	public static int[] turnFour (int[] dice) {
		int[] temp = {dice[1],dice[5],dice[2],dice[0],dice[4],dice[3]};
		return temp;
	}

}
