package BOJ._2567_색종이2;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
	static int answer = 0;
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
		calcIter(hor_u , 'u');
		calcIter(hor_d, 'd');
		calcIter(ver_u, 'u');
		calcIter(ver_d, 'd');


		System.out.println(answer);
		
		
		
	}
	public static int[] getRest(int[] temp, int cut_start, int cut_end) {
		int[] answer = {temp[0],0,0};
		int start= temp[1];
		int end = temp[2];
		if (cut_start <= start && cut_end >=end) {
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
	
	public static void calcIter(int[][] arr, char uOrd) {
		int N = arr.length;
		Arrays.sort(arr, (o1,o2)->{return o1[1] - o2[1];});
		int[] temp;
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				if (arr[j][1] >arr[i][2]) {
					answer+=10; 
					break; // arr[i][1] 보다는 정렬했으니까 무조건 크거나 같음
				}
				else {
					if (uOrd == 'u') {
						if (arr[j][0] >= arr[i][0] -10 && arr[j][0]<=arr[i][0]) {
							temp = getRest(arr[i], arr[j][1], arr[j][2]);
							
							System.out.println(Arrays.toString(temp));
							
							answer += temp[2]-temp[1];
							break;
							
						}
					} else if (uOrd == 'd') {
						if (arr[j][0]<=arr[i][0]+10 && arr[j][0]>=arr[i][0]) {
							temp = getRest(arr[i], arr[j][1], arr[j][2]);
							
							System.out.println(Arrays.toString(temp));
							
							answer += temp[2]-temp[1];
							break;							
							
						}
						
					}
					
					
				}
			}
			
		}
			
			
	}
		
	
	
}
