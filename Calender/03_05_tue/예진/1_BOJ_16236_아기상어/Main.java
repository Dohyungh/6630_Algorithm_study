package boj_16236_아기상어;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	static int N;
	static int[][] pool;			// 입력받을 배열
	static int[][] timeMap;			// 각 칸까지 이동하는데 걸리는 시간
	static List<int[]> fishes;		// 먹을 수 있는 물고기의 위치 리스트
	static Queue<int[]> queue;		// BFS 방문리스트
	static int size;				// 아기상어의 크기
	static int[] current;			// 아기상어의 현위치
	static int queueCnt;			// BFS 에서 같은 depth의 개수
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		pool = new int[N][N];
		size = 2;		// 아기상어 처음 크기
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
				pool[r][c] = sc.nextInt();
				
				// 아기상어 위치저장
				if (pool[r][c] == 9) {
					current = new int[] {r, c};
				}
			}
		}
		
		int time = 0;		// 답 변수
		int growth = 0;		// 아기상어가 먹은 물고기 수
		
		while (true) {
			// 시간맵, 큐, 먹을 수 있는 물고기리스트 등 매번 초기화 필요
			timeMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(timeMap[i], -1);
			}
			timeMap[current[0]][current[1]] = 0;	// 상어 현위치 시간 0
			queue = new LinkedList<>();
			fishes = new ArrayList<>();
			queueCnt = 0;
			
			bfs(current[0], current[1], 0);		// 시간맵 채우면서 가까운 곳의 먹을 수 있는 물고기 찾기
			
			// 먹을 수 있는 물고기가 한마리 이상 있다면,
			// 더 위쪽에 있고, 더 왼쪽에 있는 순서대로 정렬
			// 먹어야 할 타깃이 0번 인덱스에 오게됨
			if (fishes.size() > 0) {
				Collections.sort(fishes, (o1, o2) -> {
					if (o1[0] == o2[0]) {
						return o1[1] - o2[1];
					}
					return o1[0] - o2[0];
				});
				
				// 아기상어가 있던 자리를 0으로 변경
				pool[current[0]][current[1]] = 0;
				
				// 아기상어의 현재위치를 타깃의 위치로 이동
				current = new int[] {fishes.get(0)[0], fishes.get(0)[1]};
				
				// 타깃의 위치까지 걸리는 시간추가
				time += timeMap[fishes.get(0)[0]][fishes.get(0)[1]];
				
				// 본인의 사이즈만큼 물고기를 먹었으면 사이즈 업
				if (++growth == size) {
					size++;
					growth = 0;
				}
			} else break;
		}
		
		System.out.println(time);
		sc.close();
		
	}
	
	public static void bfs(int r, int c, int time) {
		
		// 사방탐색해서 방문할 곳의 리스트를 큐에 저장
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 범위 안에 있고, 방문하지 않았고, 아기상어보다 큰 물고기가 없을 때만
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && timeMap[nr][nc] == -1 
					&& pool[nr][nc] <= size) {
				
				queue.add(new int[] {nr, nc});
				// 큐에 넣는 순간 방문 표시를 해주어야 함
				timeMap[nr][nc] = time+1;
			}
		}

		while(!queue.isEmpty()) {
			
			// 물고기를 찾았는데, 같은 depth를 다 확인했으면 더 확인 안함
			if (fishes.size() > 0 && queueCnt == 0) return;
			
			// 같은 depth를 다 확인한 순간 큐에 남아있는 수가 다음 depth 개수
			if (queueCnt == 0) queueCnt = queue.size();
			
			// 다음 방문지로 갈꾸야
			int[] next = queue.poll();
			
			// 같은 depth 카운드 감소
			queueCnt--;
			
			// 가려는 곳에 물고기가 있는데 상어보다 작은 물고기면,
			// 먹을 수 있는 물고기 리스트에 추가
			if (pool[next[0]][next[1]] > 0 && pool[next[0]][next[1]] < size) {
				fishes.add(new int[] {next[0], next[1]});
			}
			
			// 방문하러 가자!
			bfs(next[0], next[1], timeMap[next[0]][next[1]]);
		}
		
	}

}
