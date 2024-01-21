package swea_1954_달팽이숫자;

import java.util.Scanner;

public class Swea_1954_달팽이숫자_sol1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T= sc.nextInt();
		for (int test_case =1;test_case<=T;test_case++) {
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			int[] dr = {0,1,0,-1}; //우하좌상
			int[] dc = {1,0,-1,0};
			int cycle = 0;
			int cnt =1;
			int r = 0;
			int c = 0;
			while (true) {
				if (arr[r][c] ==0) {
					arr[r][c] = cnt;
				}
				if (cnt == N*N) {
					break;
				}
				
				int idx = cycle %4;
				if (r+dr[idx]==N||r+dr[idx]==-1||c+dc[idx]==N||c+dc[idx]==-1||arr[r+dr[idx]][c+dc[idx]]!=0) {
					cycle++;
					
				} else {
					r+=dr[idx];
					c+=dc[idx];
					cnt++;
					
				}
				
			}
			
			for (int i=0;i<N;i++) {
				for (int j=0;j<N;j++) {
					System.out.printf("%3d",arr[i][j]);
				}
				System.out.println();
			}
			
		}
		sc.close();
		
	}

}