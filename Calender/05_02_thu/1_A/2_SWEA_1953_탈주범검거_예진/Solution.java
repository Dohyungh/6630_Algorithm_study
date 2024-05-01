/*
 * 설계 : 5분
 * 풀이 : 80분
 * 
 * [풀이 방법]
 * 딱 봐도 bfs 문제 같았다.
 * 해당 시간까지 도착할 수 있는 지점의 개수를 모두 구하면 된다.
 * 문제는 터널 타입에 따라 이동할 수 있는 곳이 다르다는 점이다.
 * map을 통해 이 문제를 해결했다.
 * 
 * 1. 사방 탐색 델타배열을 생성한다.
 * 2. map이라는 이름의 Map<Integer, int[]>에 (터널타입번호, 이동할 수 있는 방향 인덱스)를 저장한다.
 * 3. 현재 위치의 터널타입 번호에 따라 이동가능한 방향을 탐색할 수 있다.
 * 
 * 여기까지 했는데 답이 틀렸다. 
 * 타입이 0인 곳도 이동해서 그런가 했는데, 그 수보다 훨씬 많았다.
 * 디버깅 해보니, 해당 터널타입에서 이동할 수 있는 방향이면 무조건 갈 수 있는게 아니라
 * 이동하려는 터널의 타입과 이어져야 한다는 걸 깨달았다. 당연한건데 간과한 부분.
 * 
 * [해결 방안]
 * 4. able이라는 이름의 Map<Integer, List<Integer>>에 (이동할 방향 인덱스, 연결된 터널타입 번호리스트)를 저장한다.
 * 5. 현재 이동하려는 위치의 번호가 연결된 터널타입 번호리스트에 포함되는지 확인한다.
 * 6. 터널이 서로 연결되어 있으면 큐에 넣는다.
 * 
 * 해결 완료-! >^<
 * 
 * bfs 공식이 아직 손에 착 붙지 않아서 푸는동안 자꾸 자잘자잘 수정하게된다.
 * 확실한 방식 한가지를 손에 익혀놔야할듯
 */
package swea_1953_탈주범검거;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		
		// 사방 탐색 델타
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		// 터널 타입별 이동 가능 방향 인덱스
		Map<Integer, int[]> map = new HashMap<>();
		map.put(1, new int[] {0, 1, 2, 3});
		map.put(2, new int[] {0, 2});
		map.put(3, new int[] {1, 3});
		map.put(4, new int[] {0, 1});
		map.put(5, new int[] {1, 2});
		map.put(6, new int[] {2, 3});
		map.put(7, new int[] {0, 3});
		
		// 이동하는 방향별 연결된 터널 타입번호
		Map<Integer, List<Integer>> able = new HashMap<>();
		able.put(0, new ArrayList<>(Arrays.asList(1, 2, 5, 6)));
		able.put(1, new ArrayList<>(Arrays.asList(1, 3, 6, 7)));
		able.put(2, new ArrayList<>(Arrays.asList(1, 2, 4, 7)));
		able.put(3, new ArrayList<>(Arrays.asList(1, 3, 4, 5)));

//		System.setIn(new FileInputStream("src/swea_1953_탈주범검거/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 행렬 크기
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			// 시작위치(맨홀 위치, 1시간일때 위치)
			int stR = sc.nextInt();
			int stC = sc.nextInt();
			
			// 시간 조건
			int time = sc.nextInt();
			
			int[][] base = new int[N][M];
			boolean[][] visited = new boolean[N][M];
			
			for (int i = 0 ; i < N; i++) {
				for (int j = 0; j < M; j++) {
					base[i][j] = sc.nextInt();
				}
			}
			
			Queue<int[]> bfs = new LinkedList<>();
			bfs.add(new int[] {stR, stC});
			visited[stR][stC] = true;
			
			int hour = 0;	// 1시간째부터 시작
			int answer = 0;		// 도달할 수 있는 위치 총 개수
			
			while (hour < time) {		// 아직 시간이 안끝났을 때 반복
				
				int cnt = bfs.size();	// 해당 시간에 도착할 장소 개수
				hour++;					// 현재 시간 설정
				
				// 도착할 수 있는 장소만큼 반복
				for (int i = 0; i < cnt; i++) {
					
					// 현재 위치 뽑고, 답안 +1
					int[] curr = bfs.poll();
					answer++;
					
					// 터널 타입 번호에 따라 인접한 칸 순회
					for (int d : map.get(base[curr[0]][curr[1]])) {
						// 이동하려는 위치 좌표
						int nr = curr[0] + dr[d];
						int nc = curr[1] + dc[d];
						
						// 범위 안에 있고, 아직 방문하지 않았고, 이동하려는 방향과 연결된 터널이면 큐에 추가, 방문체크
						if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]
								&& able.get(d).contains(base[nr][nc])) {
							bfs.add(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
					}
				}
			}
			System.out.printf("#%d %d\n", tc, answer);
		}
		sc.close();
	}
}
