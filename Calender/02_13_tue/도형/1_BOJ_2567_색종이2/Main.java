package BOJ._2567_색종이2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		int N = sc.nextInt();
		// 10000 에 n^2 이 1초면 넉넉한듯?
		
		// 3차원인듯 3차원 아닌 2차원 배열
		int[][] hor_u = new int[N][3];
		int[][] hor_d = new int[N][3];
		int[][] ver_u = new int[N][3];
		int[][] ver_d = new int[N][3];

	
		for (int i = 0; i<N; i++) { // 짝수에 아래, 홀수에 위
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			hor_u[i][0] = y+10;
			hor_u[i][1] = x;
			hor_u[i][2] = x+10;
			
			hor_d[i][0] = y;
			hor_d[i][1] = x;
			hor_d[i][2] = x+10;
			
			ver_u[i][0] = x+10;
			ver_u[i][1] = y;
			ver_u[i][2] = y+10;
			
			ver_d[i][0] = x;
			ver_d[i][1] = y;
			ver_d[i][2] = y+10;

		}
		
		int total = 0;
		//바보
//		
//		// 아 환경이 다르니까 최적화할 머리가 안돌아가네
//		for(int i = 0; i<N; i++) {
//			for(int j = 0; j<N; j++) {
//				if (i==j) break;
//				int[] d = hor_d[j];
//				if (d[0] >= hor_u[i][0]-10 && d[0] <= hor_u[i][0]) {
//					int[] temp = getRest(temp,d[1],d[2]);
////					System.out.println(Arrays.toString(temp));
//				}
//			}
//			System.out.println(Arrays.toString(temp));
//			total+=temp[2]-temp[1];
//		}
//		for(int i = 0; i<N; i++) {
//			int[] temp = hor_d[i];
//			for(int j = 0; j<N; j++) {
//				if (i==j) break;
//				int[] u = hor_u[j];
//				if (u[0] <= temp[0]+10 && u[0] >= temp[0]) {
//					temp = getRest(temp,u[1],u[2]);
////					System.out.println(Arrays.toString(temp));
//				}
//			}
//			System.out.println(Arrays.toString(temp));
//			total+=temp[2]-temp[1];
//		}
//		
//		for(int i = 0; i<N; i++) {
//			int[] temp = ver_u[i];
//			for(int j = 0; j<N; j++) {
//				if (i==j) break;
//				int[] d = hor_d[j];
//				if (d[0] >= temp[0]-10 && d[0] <= temp[0]) {
//					temp = getRest(temp,d[1],d[2]);
////					System.out.println(Arrays.toString(temp));
//				}
//			}
//			System.out.println(Arrays.toString(temp));
//			total+=temp[2]-temp[1];
//		}
//		
//		for(int i = 0; i<N; i++) {
//			int[] temp = ver_d[i];
//			for(int j = 0; j<N; j++) {
//				if (i==j) break;
//				int[] u = ver_u[j];
//				if (u[0] <= temp[0]+10 && u[0] >= temp[0]) {
//					temp = getRest(temp,u[1],u[2]);
////					System.out.println(Arrays.toString(temp));
//				}
//			}
//			System.out.println(Arrays.toString(temp));
//			total+=temp[2]-temp[1];
//		}
//		

		System.out.println(total);
		
		
		
	}
	public static int[] getRest(int[] temp, int cut_start, int cut_end) {
		int[] answer = {temp[0],0,0};
		int start= temp[1];
		int end = temp[2];
		if (cut_start < start && cut_end >end) {
			answer[1] = 0;
			answer[2] = 0;
		}
		else if (start <= cut_start) { // 다행히 길이가 10으로 똑같아서 무조건 넘어감.
			answer[1] = start;
			answer[2] = cut_start;
		}
		else if (cut_end <= end) { // 다행히 길이가 10으로 똑같아서 무조건 안넘어감.
			answer[1] = cut_end;
			answer[2] = end;
		}
		else if (end <= cut_start) {
			answer[1] = start;
			answer[2] = end;
		}
		return answer;
	}
}
