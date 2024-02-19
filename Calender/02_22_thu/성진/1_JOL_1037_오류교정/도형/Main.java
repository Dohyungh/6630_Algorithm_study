package JOL._1037_오류교정;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] arr = new int[N][N];
		
		//중복이 좀 많아서 줄일 수 있을지도.
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// col 별 합을 저장
		// row 별 합을 저장
		int[] col = new int[N];
		int[] row = new int[N];
		
		for (int i = 0; i<N; i++) {
			int sum_r = 0;
			int sum_c = 0; 
			for (int j = 0; j<N; j++) {
				sum_r += arr[i][j]; 
				sum_c += arr[j][i]; // 한번에!!
			}
			row[i] = sum_r;
			col[i] = sum_c;
		}
		
		
		// 개수로 Corrupt와 OK, Change bit를 구별할거고
		// 혹시 Change bit의 케이스라면 출력할 row, Column 을 미리 구해놓을 것임.
		// Corrupt 와 OK에는 사실 cntR, cntC. 즉 개수만 필요함.
		// 합이 1이면 한쪽만 1인거니까 corrupt,
		// 합이 0 이면 OK
		// 합이 2 이면 (2, 0) (0,2)인 경우 제외하고 (1,1)밖에 없으니까 Change bit.
		
		int cntR = 0;
		int targetR = -1;
		for (int i = 0; i<N; i++) {
			if (row[i] %2 == 1) { //홀수면 
				cntR++;
				targetR = i+1; //index -> 1 더해줘야 함. 문제조건.
			}
		}
		
		int cntC = 0;
		int targetC = -1;
		for (int j = 0; j<N; j++) {
			if (col[j] %2 ==1) { //홀수면
				cntC++;
				targetC = j+1; //index -> 1 더해줘야 함. 문제조건.
			}
		}
		
		if (cntR>1 || cntC>1 || cntR+cntC == 1) System.out.println("Corrupt");
		else if (cntR+cntC == 0) System.out.println("OK");
		else if (cntR+cntC == 2) System.out.println(("Change bit ("+targetR+","+targetC+")"));
		
		sc.close();
		
	}

}
