package boj_16235_나무재태크;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

	static int N;
	static int M;
	static int K;
	static int[][] basic;
	static int[][] area;
	static List<Integer>[][] trees;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();		// 밭 한변의 길이
		M = sc.nextInt();		// 나무의 개수 
		K = sc.nextInt();		// 문제: K년이 지난 후 나무의 개수
		
		basic = new int[N][N];		// 로봇이 나누어줄 양분
		area = new int[N][N];		// 실제 땅의 양분 상태
		trees = new List[N][N];		// 나무
		
		// 매년 추가될 기본 양분 정보와 실제 땅 양분 동시 입력
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int tmp = sc.nextInt();
				basic[r][c] = tmp;
				area[r][c] = 5;
			}
		}
		
		// 나무 위치 및 나이 입력, 인덱스 각 -1
		for (int i = 0; i < M; i++) {
			int r = sc.nextInt()-1;
			int c = sc.nextInt()-1;
			int age = sc.nextInt();
			
			// 초기화 안되어 있는 경우, ArrayList 초기화 후 추가
			if (trees[r][c] == null) {
				trees[r][c] = new ArrayList<>();
			}
			trees[r][c].add(age);
			
			// 작은 나무부터 양분 먹어야해서 나무 추가시 재정렬 필요
			Collections.sort(trees[r][c]);
		}
		
		for (int y = 0; y < K; y++) {
			springSummer();
			autumn();
			winter();
		}
		
		int answer = count();

		System.out.println(answer);
		
		sc.close();
	}
	
	public static int count() {
		int result = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
				// 나무가 있었던 칸만 개수 추가
				if (trees[r][c] != null) {
					result += trees[r][c].size();
				}
			}
		}

		return result;
	}

	public static void winter() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
				// 양분 채우기
				area[r][c] += basic[r][c];
			}
		}
	}

	public static void autumn() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
				// 나무가 한개라도 있을 때
				if (trees[r][c] != null && trees[r][c].size() > 0) {
					
					// 나무 개수만큼 차례로 돌면서, 나이가 5의 배수일 때만 실행
					for (int t = 0; t < trees[r][c].size(); t++) {
						if (trees[r][c].get(t) % 5 == 0) {
							
							// 팔방탐색으로 범위 안에 있는 칸이면 1살짜리 나무 추가
							for (int d = 0; d < 8; d++) {
								int nr = r + dr[d];
								int nc = c + dc[d];
								
								if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
									// 초기화 안되어 있으면, 초기화 후 나무 추가
									if (trees[nr][nc] == null) {
										trees[nr][nc] = new ArrayList<>();
									}
									
									// 1살이 가장 어린 나무임으로 0번째 인덱스에 추가
									trees[nr][nc].add(0, 1);
								}
							}
						}
					}
				}
			}
		}
	}

	public static void springSummer() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
				// 나무가 하나라도 있는 곳만 실행
				if (trees[r][c] != null && trees[r][c].size() > 0) {
					
					// 여름에 더해줄 양분 값
					int plus = 0;

					// 해당 칸의 나무 순회
					for (int t = 0; t < trees[r][c].size(); t++) {
						
						int tree = trees[r][c].get(t);
						
						// 땅에 남은 양분이 나무의 나이보다 크거나 같으면, 양분 감소 및 나무 나이 추가
						if (area[r][c] >= tree) {
							area[r][c] -= tree;
							trees[r][c].set(t, tree+1);
							
						// 남은 양분이 나무 나이보다 적으면, 나무 삭제 및 양분 값 저장	
						// 나무를 삭제하면 리스트 인덱스도 변화가 있어서 t-- 필수
						} else {
							trees[r][c].remove(t);
							plus += tree/2;
							t--;
						}
					}
					
					area[r][c] += plus;
				}
			}
		}
	}
}
