package BOJ._14891_톱니바퀴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 짱많네
	static int[] directions = {0,0,0,0};
	static int[][] wheels = new int[4][8];
	
	static int[] twelve = {0,0,0,0};
	static int[] spin = {0,0,0,0};
	
	public static void main(String[] args) throws IOException {
		
//		Scanner sc = new Scanner(System.in);
		// 입력 ㅈㄴ 짜증나네 진짜로
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		for (int i = 0; i < 4; i++) {
			String[] row = br.readLine().split("");
			for (int j = 0; j < 8; j++) {
				wheels[i][j] = Integer.parseInt(row[j]);
			}
		}
		int ord = Integer.parseInt(br.readLine());
	
		for (int i = 0; i < ord; i++) {
			
			for (int l = 0; l<4; l++) { // 초기화
				spin[l] = 0;
			}
			
			String str = br.readLine();
			
			StringTokenizer st = new StringTokenizer(str);
			
			
			int wheel = Integer.parseInt(st.nextToken())-1;
			int dir = 0;
			if (st.nextToken().equals("-1")) {
				dir = -1;
			} else {
				dir = 1;
			}
			
			spin[wheel] = dir;
			spining(wheel,dir);
			
			for (int j = 0; j<4; j++) {
				if (spin[j] == -1) {
					twelve[j] = (twelve[j]+1)%8;
				}
				if (spin[j] == 1) {
					twelve[j] = (twelve[j]-1+8)%8;
				}
			}

		}
		
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			answer += wheels[i][twelve[i]] * Math.pow(2, i);
		}
		System.out.println(answer);

		
		
		
		
	}
	
	public static void spincheck (int wheel, int dir, int passiveWheel) {
		if (wheel >passiveWheel) {
			if (wheels[wheel][(twelve[wheel]-2+8)%8] != wheels[passiveWheel][(twelve[passiveWheel]+2)%8]) {
				spin[passiveWheel] = -dir;
			} else {
				spin[passiveWheel] = 0;
			}
		}
		if (wheel <passiveWheel) {
			if (wheels[wheel][(twelve[wheel]+2)%8] != wheels[passiveWheel][(twelve[passiveWheel]-2+8)%8]) {
				spin[passiveWheel] = -dir;
			} else {
				spin[passiveWheel] = 0;
			}
			
		}
	}
	
	public static void spining(int wheel, int dir) {
		if (wheel == 0) {
			spincheck(0,dir,1);
			spincheck(1,spin[1],2);
			spincheck(2,spin[2],3);
		}
		if (wheel ==1) {
			spincheck(1,dir,0);
			spincheck(1,dir,2);
			spincheck(2,spin[2],3);
		}
		if (wheel ==2) {
			spincheck(2,dir,1);
			spincheck(2,dir,3);
			spincheck(1,spin[1],0);
		}
		if (wheel ==3) {
			spincheck(3,dir,2);
			spincheck(2,spin[2],1);
			spincheck(1,spin[1],0);
		}
	}

}
