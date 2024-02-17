package JOL._1733_오목;

import java.util.Scanner;

public class Main {
	
	// 정확히 5개여야 한다는 것.
	// 5개를 찾았으면 가장 왼쪽돌의 좌표를 출력해야 한다는 것.
	
	static int[][] map = new int[29][29]; // 19 + 5 + 5
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//padding //안하면 빡셀 듯
		for (int i = 5; i < map.length-5; i++) {
			for (int j = 5; j < map.length-5; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int found = 0;
		out:
		for (int i = 5; i < map.length-5; i++) {
			for (int j = 5; j < map.length-5; j++) {	
				if (map[i][j] != 0) {
					if(isWinner(i,j)) {
					found = map[i][j];
					System.out.println(found);
					System.out.print((i-4)+" "+ (j-4));
					break out;
					}
				}
			}
		}
		if (found == 0) System.out.println(found);
	}
	
	public static boolean isWinner(int i , int j) {
		boolean win = false;
		int color = map[i][j];
		
		// 가로체크
		
		// 6개 이상은 안 쳐줌. 정확히 5개여야 하기 때문에 "처음-1칸"과 "끝+1칸"은 색이 다를 때만 체크
		
		if (map[i][j-1] != color && map[i][j+5] != color) {
			for (int n = j+1; n<j+6; n++) {
				if (n == j+5) win = true;
				if (map[i][n] != color)	break;
			}			
		}
		
		// 세로체크
		if (map[i-1][j] != color && map[i+5][j] !=color) {
			for (int n = i+1; n<i+6; n++) {
				if (n == i+5) win = true;
				if (map[n][j] != color) break;
			}			
		}
		
		
		// n이랑 m 으로 변수가 2개인 for 문을 돌릴건데
		// 종료 조건은 어차피 2개가 동시에 움직이므로
		// 둘중에 하나만 써줘도 됨.
		
		// 우하체크
		if (map[i-1][j-1] != color && map[i+5][j+5] !=color) {
			for (int n = i+1, m = j+1; n<i+6; n++,m++) {// n<i+5 && m<i+5 이렇게 써도 됨. 그게 더 직관적
				if (n == i+5) win = true; 
				if (map[n][m] != color) break;
			}
		}
		
		// 우상체크 // 가장 왼쪽껄 출력해야해서 좌하(가장 윗쪽껄 출력할 때) 가 아니라 우하
		if (map[i+1][j-1] != color && map[i-5][j+5] !=color) {
			for (int n = i-1, m = j+1; m<j+6; n--, m++) {
				if (m==j+5) win = true; 
				if (map[n][m] != color) break;
			}
		}
		
		
		return win;
		
	}

}
