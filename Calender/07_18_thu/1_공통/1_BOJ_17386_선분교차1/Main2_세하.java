package boj_17386_선분교차1;

import java.io.*;
import java.util.*;

// CCW 이용 풀이
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 선분1을 이루는 두 점
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		
		// 선분2를 이루는 두 점
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		int x4 = Integer.parseInt(st.nextToken());
		int y4 = Integer.parseInt(st.nextToken());

		int ans = 0;
		
		// 선분1에서 점 (x3, y3), (x4, y4) 로의 방향
		int ccw1 = ccw(x1, y1, x2, y2, x3, y3);
		int ccw2 = ccw(x1, y1, x2, y2, x4, y4);
		// 선분2에서 점 (x1, y1), (x2, y2) 로의 방향
		int ccw3 = ccw(x3, y3, x4, y4, x1, y1);
		int ccw4 = ccw(x3, y3, x4, y4, x2, y2);
		
		// 선분1에서 나머지 두 점이 서로 반대 방향에 위치하고, 선분2에서 나머지 두 점이 서로 반대 방향에 위치한다면 두 선분은 교차한다.  
		if (ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0)
			ans = 1;

		System.out.println(ans);

	}
	
	// (x1, y1) -> (x2, y2), (x2, y2) -> (x3, y3) 로의 외적 계산
	// 외적 결과가 음수이면 시계방향, 양수이면 시계 반대방향 
	// ccw 내 매개 변수가 int형이면 정답 실패 -> 매개변수로 들어가는 좌표는 값의 범위가 정해져 있어서 return 값만 long 타입이면 될 것 같은데 아직 이유를 모르겠음.
	public static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
		// 외적 결과도 마찬가지로 long 타입 매개변수로 받아도 정답 실패
		// return 내에서 조건문으로 -1 또는 1로 반환해야 성공 
		return (x2 - x1) * (y3 - y2) - (y2 - y1) * (x3 - x2) < 0 ? -1 : 1;
	}
}
