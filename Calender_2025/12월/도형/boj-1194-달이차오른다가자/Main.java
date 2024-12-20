package BOJ._1194_달이차오른다가자;


/*
 * 까다로운 조건 처리의 BFS 문제
 * 비트마스킹으로 열쇠 6개와 50 by 50 map의 탐색을 해내야 한다.
 * 구상보다는 구현이 까다로운 편.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	// 노드 클래스 정의
	// 가지고 있는 열쇠 정보
		// 열쇠가 총 6개이므로 6칸만 있으면 열쇠정보를 표현할 수 있다.
	// 위치 row, col
	// dist 는 해당 열쇠정보와 위치정보 상태에서 얼마나 빠른 시간안에 그 점에 도달했는지를 말한다.
	
	static class Node {
		
		int keys;
		int row;
		int col;
		int dist;
		
		Node(int keys, int row, int col, int dist) {
			this.keys = keys;
			this.row = row;
			this.col = col;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] dr = new int[] {-1,1,0,0};
		int[] dc = new int[] {0,0,-1,1};
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// 지도
		char[][] map = new char[N][M];
	
		// 줄바꿈 이슈
		sc.nextLine();
		
		// 지도 입력
		for(int i = 0; i < N; i++) {
			map[i] = sc.nextLine().toCharArray();
		}
		
		// 거리 배열
		// 본 문제에서 서로 다른 노드의 기준은
			// (당연하게도) 위치가 다르거나 
			// 가지고 있는 열쇠의 종류가 다르거나
			// 도착한 시간이 다르거나, 정확히는 더 빠른 시간에 도착한 경우만 의미가 있다.
		// 1<<6 + 1 인 이유는, 열쇠의 종류 6개 + 아무 열쇠도 가지고 있지 않을 때 (1가지) 를 포함해서임.
		int[][][] distArr = new int[N][M][1<<6 + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < 1<<6 + 1; k++) {
					// 최댓값으로 초기화
					distArr[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		
		// 시작 노드
		Node start = null;
		
		// 시작 노드 탐색
		// 입력받을 때 처리해도 되긴 함.
		out:
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <M; j++) {
				if(map[i][j] == '0') {
					start = new Node(0, i, j, 0);
					break out;
				}
			}
		}
		
		Queue<Node> q = new LinkedList<Node>();
		
		// 시작 노드 집어 넣고 BFS
		q.add(start);
		
		// 정답 변수
		int final_answer = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			
			// 노드 뽑아
			Node curr = q.poll();
			
			// dr, dc 돌아
			for (int i = 0; i < 4; i++) {
				
				// 다음 위치
				int nRow = curr.row + dr[i];
				int nCol = curr.col + dc[i];
				
				// 다음 위치가 출구 일 경우 그냥 정답 업데이트 하고 끝
				if (nRow >= 0 && nRow < N && nCol >= 0 && nCol < M && map[nRow][nCol] == '1') {
					final_answer = Math.min(final_answer, curr.dist+1);
					continue;
				}
				
				// 통로이거나 시작점일 경우 열쇠 조건이랑 거리 조건을 검사해서 더 빠른 경우에만 업데이트
				if (nRow >= 0 && nRow < N && nCol >= 0 && nCol < M && (map[nRow][nCol] == '.' || map[nRow][nCol] == '0')) {
					
					// 열쇠가 하나라도 있을 때
					for (int j = 0; j < 1<<6; j++) {
						if ((curr.keys & j) >= j && distArr[nRow][nCol][j] > curr.dist+1) {
							distArr[nRow][nCol][j] = curr.dist+1;
							q.add(new Node(curr.keys, nRow, nCol, curr.dist+1));
						}
					}
					
					// 열쇠가 하나도 없을 때
					// 예외 처리
					if(distArr[nRow][nCol][1<<6] > curr.dist+1) {
						distArr[nRow][nCol][1<<6] = curr.dist+1;
						q.add(new Node(curr.keys, nRow, nCol, curr.dist+1));
					}
					continue;
				}
				
				// 벽인 경우 패스
				if (nRow >= 0 && nRow < N && nCol >= 0 && nCol < M && map[nRow][nCol] == '#') {
					continue;
				}
				
				// 이제 남은건 열쇠거나 문이거나
				if (nRow >= 0 && nRow < N && nCol >= 0 && nCol < M) {
					
					// 문일 경우
					if(map[nRow][nCol] >= 'A' && map[nRow][nCol] < 'a') {
						
						// 열 수 있는 문인지 열쇠 검사
						if((curr.keys & (1 << (map[nRow][nCol] - 'a'))) > 0) {
							
							// 이후 업데이트는 들고 온 열쇠가 달라졌을 수 있기 때문에 전체 순회
							for (int j = 0; j < 1<<6; j++) {
								if ((curr.keys & j) > 0 && distArr[nRow][nCol][j] > curr.dist+1) {
									distArr[nRow][nCol][j] = curr.dist+1;
									q.add(new Node(curr.keys, nRow, nCol, curr.dist+1));
								}
							}
							continue;
							// 못 여는 문이면 패스
						} else {
							continue;
						}
					}
					
					// 열쇠 칸일 경우
					int attainedKey = map[nRow][nCol] - 'a';
					
					// 새로운 열쇠 저장
					int newKeys = curr.keys | (1<<attainedKey);
					
					if (distArr[nRow][nCol][newKeys] > curr.dist+1) {
						distArr[nRow][nCol][newKeys] = curr.dist+1;
						q.add(new Node(newKeys, nRow, nCol, curr.dist+1));
					}
					continue;
					
				}
			}
		}
		
		System.out.println(final_answer == Integer.MAX_VALUE? -1 : final_answer);
	}

}
