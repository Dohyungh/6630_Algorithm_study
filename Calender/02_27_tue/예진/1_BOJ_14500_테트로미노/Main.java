package boj_14500_테트로미노;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 문제 입력
		int row = sc.nextInt();
		int col = sc.nextInt();
		int[][] arr = new int[row][col];
		
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				arr[r][c] = sc.nextInt();
			}
		}
		
		// 확인이 까다로운 모양 3개 boolean 배열로 틀 만들기
		boolean[][] T = new boolean[2][3];
		boolean[][] Z = new boolean[2][3];
		boolean[][] L = new boolean[2][3];
		
		for (int r = 0; r < 2; r++) {
			for (int c = 0; c < 3; c++) {
				if (r == 1 || c == 1) T[r][c] = true;
				if ((r == 0 && c < 2) || (r == 1 && c > 0)) Z[r][c] = true;
				if (!(r == 0 && c > 0)) L[r][c] = true;
			}
		}
		
		// 답안 변수
		int max = 0;
		
		// 배열의 모든 좌표를 순회하면서 좌표 기준에 따라 유효한 모양을 모두 검사
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				
				// 모양 안 값들의 합을 임시저장할 변수
				int temp;
				
				// 밭전 모양 검사
				if (r < row-1 && c < col-1) {
					temp = arr[r][c] + arr[r+1][c] + arr[r][c+1] + arr[r+1][c+1];
					if (temp > max) max = temp;
				}
				
				// 일자 모양 가로
				if (c < col-3) {
					temp = arr[r][c] + arr[r][c+1] + arr[r][c+2] + arr[r][c+3];
					if (temp > max) max = temp;
				}
				
				// 일자 모양 세로
				if (r < row-3) {
					temp = arr[r][c] + arr[r+1][c] + arr[r+2][c] + arr[r+3][c];
					if (temp > max) max = temp;
				}
				
				// 모양 틀 크기는 모두 2*3, 가로 방향
				if (r < row-1 && c < col-2) {
					
					// 배열로 저장해서 비교갱신 한번에
					int[] sum = new int[8];
					
					// 틀 모양대로 순회
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 3; j++) {
							
							// ㅗ
							if (T[i][j]) sum[0] += arr[r+i][c+j];
							
							// T
							if (T[1-i][j]) sum[1] += arr[r+i][c+j];
							
							// Z
							if (Z[i][j]) sum[2] += arr[r+i][c+j];
							
							// Z대칭
							if (Z[1-i][j]) sum[3] += arr[r+i][c+j];
							
							// ㄴ
							if (L[i][j]) sum[4] += arr[r+i][c+j];
							
							// ㄴ 상하대칭
							if (L[1-i][j]) sum[5] += arr[r+i][c+j];
							
							// ㄴ 좌우대칭
							if (L[i][2-j]) sum[6] += arr[r+i][c+j];
							
							// ㄱ
							if (L[1-i][2-j]) sum[7] += arr[r+i][c+j];
						}
					}
					
					for (int i = 0; i < 8; i++) {
						if (sum[i] > max) max = sum[i];
					}
				}
				
				// 틀 가로 방향 확인
				if (r < row-2 && c < col-1) {
					int[] sum = new int[8];
					
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 3; j++) {
							
							// ㅗ 
							if (T[i][j]) sum[0] += arr[r+j][c+i];
							
							// T
							if (T[1-i][j]) sum[1] += arr[r+j][c+i];
							
							// Z
							if (Z[i][j]) sum[2] += arr[r+j][c+i];
							
							// Z 대칭
							if (Z[1-i][j]) sum[3] += arr[r+j][c+i];
							
							// ㄴ
							if (L[i][j]) sum[4] += arr[r+j][c+i];
							
							// ㄴ 상하대칭
							if (L[1-i][j]) sum[5] += arr[r+j][c+i];
							
							// ㄴ 좌우대칭
							if (L[i][2-j]) sum[6] += arr[r+j][c+i];
							
							// ㄱ
							if (L[1-i][2-j]) sum[7] += arr[r+j][c+i];
						}
					}
					
					for (int i = 0; i < 8; i++) {
						if (sum[i] > max) max = sum[i];
					}
				}
			}
		}
		System.out.println(max);
		sc.close();
	}
}
