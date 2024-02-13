package boj_2563_색종이;

import java.util.Scanner;

public class Main {
	
	// paper 배열 static으로 선언
	static int[][] paper = new int[101][101];
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		int N = sc.nextInt();
		
		
		for (int n=0; n<N; n++) {
			
			int x = sc.nextInt();
			int y = sc.nextInt();
			// 아래 정의한 coloredArea 메소드
			coloredArea(x, y);
			
			
		}
		
		int answer = 0;
		
		// 전체 배열에서 1의 값들을 더해 전체 색종이가 놓여진 범위를 계산
		for (int i=0; i<paper.length; i++) {
			for (int j=0; j<paper[0].length; j++) {
				answer += paper[i][j];
			}
		}
		
		System.out.println(answer);
		sc.close();
		
		
	}
	
	
	
	public static void coloredArea(int x, int y) {
		// 입력받은 x, y의 +10만큼의 범위에 1로 채우기
		for (int i=x; i<x+10; i++) {
			for (int j=y; j<y+10; j++) {
				if (paper[i][j] != 1) {
					paper[i][j] = 1;
				}
			}
		}
		
		
	}
	

}
