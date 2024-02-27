package boj_14502_연구소;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int row;
	static int col;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 문제 입력 변수
		row = sc.nextInt();
		col = sc.nextInt();
		int[][] lab = new int[row][col];
		
		// 0위치 좌표 배열
		int[][] zeroLo = new int[row * col][2];
		
		// 초기 바이러스 개수
		int virus = 0;
		
		// 바이러스가 퍼지지않은 공간의 넓이
		int answer = 0;
		
		// 0좌표 입력 인덱스, 0의 개수
		int idx = 0;
		
		// 배열 입력하면서 0의 좌표와 2개수 체크
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				lab[r][c] = sc.nextInt();
				if(lab[r][c] == 0) zeroLo[idx++] = new int[] {r, c};
				if(lab[r][c] == 2) virus++;
			}
		}
		
		// 0좌표 배열 빈공간 잘라내고 카피
		int[][] zero = Arrays.copyOf(zeroLo, idx);
		
		// 0좌표 배열로 3가지 선택 경우의 수 순회
		for (int i = 0; i < zero.length - 2; i++) {
			lab[zero[i][0]][zero[i][1]] = 1;
			
			for (int j = i+1; j < zero.length - 1; j++) {
				lab[zero[j][0]][zero[j][1]] = 1;
				
				for (int k = j+1; k < zero.length; k++) {
					lab[zero[k][0]][zero[k][1]] = 1;
					
					// 해당 경우에 바이러스가 모두 퍼지고 나면 깨끗한 공간이 얼마인지 받아냄
					int temp = howClean(lab, virus);
					
					// 최대값 비교 및 갱신
					if (temp > answer) answer = temp;
					
					
					lab[zero[k][0]][zero[k][1]] = 0;
				}
				lab[zero[j][0]][zero[j][1]] = 0;
			}
			lab[zero[i][0]][zero[i][1]] = 0;
		}
		
		System.out.println(answer);
		sc.close();
	}
	
	static int howClean(int[][] lab, int virus) {
		
		// 바이러스 퍼지는 시뮬레이션 해야해서 배열 카피
		int[][] copy = new int[lab.length][];
		for (int i = 0; i < lab.length; i++) {
			copy[i] = Arrays.copyOf(lab[i], lab[i].length);
		}
		
		// 이미 확인한 곳은 패스하도록 visited배열 사용
		boolean[][] visited = new boolean[row][col];
		
		// 사방탐색 델타배열
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		// 함수가 반환할 변수, 깨끗한 공간의 넓이
		int result = 0;
		
		// 새 바이러스가 0이 나올 때까지
		while (virus != 0) {
			
			// 바이러스 수 초기화
			virus = 0;
			
			// 배열을 모두 순회하면서
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < col; c++) {
					
					// 방문하지 않았는데 값이 2이면 사방탐색
					if (!visited[r][c] && copy[r][c] == 2) {
						visited[r][c] = true;
						
						for (int i = 0; i < 4; i++) {
							int nr = r+dr[i];
							int nc = c+dc[i];
							
							// 경계조건 확인하여 값이 0이면 퍼뜨리기
							if (nr >= 0 && nr < row && nc >=0 && nc < col && copy[nr][nc] == 0) {
								copy[nr][nc] = 2;
								virus++;
							}
						}
					}
				}
			}
		}
		
		// 바이러스가 더이상 퍼지지 않을 때, 0의 개수
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (copy[r][c] == 0) result++;
			}
		}
		
		return result;
	}
}
