package boj_2527_직사각형;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 4줄 동안 입력
		int line = 4;
		
		for (int l=0; l<line; l++) {
			
			int[] rectArr1 = new int[4];
			int[] rectArr2 = new int[4];
			
			// 첫번째 직사각형
			for (int i=0; i<4; i++) {
				rectArr1[i] = sc.nextInt();
			}
			
			// 두번째 직사각형
			for (int i=0; i<4; i++) {
				rectArr2[i] = sc.nextInt();
			}
			
			
			
			int x1, x2, y1, y2, p1, p2, q1, q2;
			
			// xy 좌표평면에서
			// 왼쪽의 직사각형의 좌표 (x1, y1) ~ (p1, q2)
			// 오른쪽의 직사각형의 좌표 (x2, y2) ~ (p2, q2)로 설정
			if (rectArr1[0] <= rectArr2[0]) {
				x2 = rectArr2[0];
				y1 = rectArr1[1];
				x1 = rectArr1[0];
				y2 = rectArr2[1];
				p1 = rectArr1[2];
				p2 = rectArr2[2];
				q1 = rectArr1[3];
				q2 = rectArr2[3];
			}
			else {
				x1 = rectArr2[0];
				x2 = rectArr1[0];
				y1 = rectArr2[1];
				y2 = rectArr1[1];
				p1 = rectArr2[2];
				p2 = rectArr1[2];
				q1 = rectArr2[3];
				q2 = rectArr1[3];
			}
			
			
			
			// for case d)
			if (p1 < x2 || q1 < y2 || y1 > q2) {
				System.out.println("d");
			}
			
			// for case c)
			else if ((p1 == x2 && q1 == y2) || (p1 == x2 && q2 == y1)) {
				System.out.println("c");
			}
			
			// for case b)
			else if ((p1 == x2 && q1 > y2 && y1 < q2) || (q1 == y2 && p1 > x2) || (y1 == q2 && p1 > x2)){
				System.out.println("b");
			}
			
			// for case a)
			else {
				System.out.println("a");
			}
			
			
			
			
			
		}
		
		sc.close();
		
	}

}
