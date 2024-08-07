package boj_17386_선분교차1;

import java.io.*;
import java.util.*;

// 직선의 방정식 이용 풀이 
public class Main {
	// Point 객체 선언 
	static class Point implements Comparable<Point> {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		// x좌표 오름차순으로 정렬, x좌표 같을 경우 y좌표 오름차순으로 정렬 
		@Override
		public int compareTo(Point o) {
			if (this.x == o.x)
				return this.y - o.y;
			else
				return this.x - o.x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 직선 l1 
		Point[] l1 = new Point[2];
		l1[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		l1[1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		// 정렬 
		Arrays.sort(l1);

		st = new StringTokenizer(br.readLine());
		
		// 직선 l2 
		Point[] l2 = new Point[2];
		l2[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		l2[1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		// 정렬 
		Arrays.sort(l2);

		int ans = 0;

		int x1 = l1[0].x;
		int x2 = l1[1].x;
		int x3 = l2[0].x;
		int x4 = l2[1].x;

		int y1 = l1[0].y;
		int y2 = l1[1].y;
		int y3 = l2[0].y;
		int y4 = l2[1].y;
		
		// 선분이 x축과 수직일 경우 선분의 기울기를 구하는 식에서 분모가 0이 되므로 따로 조건 설정 
		// x1, x2가 같으면 선분 l1이 x축에 수직이다.  
		if (x1 == x2) {
			// x1이 x3~x4의 범위 내에 있지 않으면 무조건 교차점이 없다. 
			if (x1 >= x3 && x1 <= x4) {
				// x1이 x3~x4의 범위 내에 있으면서, l2의 x좌표가 x1일 때의 y좌표가 y1~y2 범위 내에 있으면 두 선분은 교차한다.   
				double m = (double) (y4 - y3) / (x4 - x3);
				double b = (double) -m * x3 + y3;
				if (m * x1 + b >= y1 && m * x1 + b <= y2)
					ans = 1;
			}
		} 
		// 선분 l2가 x축에 수직일 때에도 마찬가지로 조건을 설정한다. 
		else if (x3 == x4) {
			if (x3 >= x1 && x3 <= x2) {
				double n = (double) (y2 - y1) / (x2 - x1);
				double a = (double) -n * x1 + y1;
				if (n * x3 + a >= y3 && n * x3 + a <= y4)
					ans = 1;
			}
		} else {
			// 위의 두가지 경우가 아닐 때에는 두 선분의 기울기를 모두 구할 수 있다. 
			double n = (double) (y2 - y1) / (x2 - x1); // l1의 기울기 
			double m = (double) (y4 - y3) / (x4 - x3); // l2의 기울기 
			double a = (double) -n * x1 + y1; // l1의 y절편 
			double b = (double) -m * x3 + y3; // l2의 y절편 

			double xn = (b - a) / (n - m); // 두 선분을 연장한 직선의 교점 계산
			
			// 두 선분이 평행하지 않으며, 두 선분을 연장한 직선의 교점의 x좌표가 두 선분의 x좌표 범위 내에 모두 포함되면 교점이 존재함. 
			if (n != m && x1 <= xn && x2 >= xn && x3 <= xn && x4 >= xn) {
				ans = 1;
			}
		}

		System.out.println(ans);

	}
}
