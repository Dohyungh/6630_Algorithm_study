package day_04.BOJ_G4_16234_Fail;

import java.util.Scanner;

public class Main {
	
	static int[][] population;    // 인구 저장 배열
	static int[][] linkMat;    // 연결 관계 행렬
	static boolean[] visited;    // 방문 체크(안하면 계속 도는듯)
	static int N;
	static int L;
	static int R;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		
		population = new int[N][N];
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				population[i][j] = sc.nextInt();
			}
		}
		
		linkMatrixUpdate();
//		printTable();
		
		
		for(int i=0 ; i<N ; i++) {
			visited = new boolean[N];
			linkSearch(i);
			pMove();
		}
		
		sc.close();
	}
	
	

//	static void printTable() {
//		for(int i=0 ; i<N*N ; i++) {
//			for(int j=0 ; j<N*N ; j++) {
//				System.out.print(linkMat[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
	
	// 인구 상태에서 연결 관계 행렬을 갱신하는 메서드
	static void linkMatrixUpdate() {
		linkMat = new int[N*N][N*N];
		
		for(int r=0 ; r<N ; r++) {
			for(int c=0 ; c<N ; c++) {
				for(int k=0 ; k<4 ; k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					if((nr>=0) && (nc>=0) && (nr<N) && (nc<N) && 
							(Math.abs(population[r][c]-population[nr][nc])>=L) && 
							(Math.abs(population[r][c]-population[nr][nc])<=R)) {
						linkMat[N*r+c][N*nr+nc] = 1;
					}
				}
			}
		}
	}
	
	// idx와 국경이 열리는 곳을 찾아보려고 함
	static void linkSearch(int idx) {
		for(int i=idx ; i<N*N ; i++) {
			if(!(visited[idx]) && (linkMat[idx][i] == 1)) {
				visited[idx] = true;
				linkSearch(i);
			}
		}
	}
	
	// 인구 이동하기
	static void pMove() {
		int num = 0;
		for(int i=0 ; i<N ; i++) {
			if(visited[i]) {
				num += population[i / (int) (Math.sqrt(N))][i % (int) (Math.sqrt(N))];
			}
		}
		System.out.println(num);
	}
	
	
}
