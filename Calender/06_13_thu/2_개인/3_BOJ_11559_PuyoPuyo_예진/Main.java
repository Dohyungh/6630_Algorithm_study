/*
 * 설계: 10분
 * 풀이: 70분
 * 
 * [풀이 방법]
 * 구현은 문제에서 시키는대로 한다.
 * 상하좌우로 4개 이상 연결된 서로 같은 색상의 블럭들을 찾아야 하기 때문에 bfs를 사용했다.
 * 찾는대로 바로 터뜨리는 게 아니라 4개 이상일때만 터뜨리고, 한번이라도 터졌는지 여부를 전달해야했다.
 * 따라서 연결된 블럭들의 위치를 담아두는 리스트가 필요했고 리스트 크기에 따라 처리 방식을 분리했다.
 * 
 * 1. 중복체크하지 않도록 방문체크를 해가며 bfs로 터뜨릴 수 있는 모든 블럭 제거하기
 * 		-> 연결된 블럭이 4개 이상일때만 터뜨리고, 그 여부를 전달
 * 2. 한번이라도 터졌으면 연속횟수를 증가시키고, 빈칸 중력 처리
 * 3. 하나도 터지지 않았을때 더이상 진행 여지가 없으므로 종료
 * 4. 연속된 콤보 횟수 출력
 */

package boj_11559_PuyoPuyo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	// 델타 배열
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1}; 
	
	static char[][] field;
	static int combo;
	static boolean[][] visited;
	public static void main(String[] args) {
		
		field = new char[12][6];	// 게임 필드
		combo = 0;					// 터진 연속 횟수
		
		Scanner sc = new Scanner(System.in);
		
		// 게임 초기화면 입력
		for (int i = 0; i < 12; i++) {
			char[] tmp = sc.nextLine().toCharArray();
			for (int j = 0; j < 6; j++) {
				field[i][j] = tmp[j];
			}
		}
		
		// 내부에서 특정 상황 발생(아무것도 터지지않음) 이전까지 무한반복
		while (true) {
			visited = new boolean[12][6];	// 방문체크
			boolean flag = false;			// 한번이라도 터졌는지 여부
			
			// 모든 곳을 순회하며 블럭이 있으면 bfs
			// 해당 콤보에 터질 수 있는 모든 경우 처리 가능
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (field[i][j] == '.') continue;
					
					if(bfs(i, j)) flag = true;
				}
			}
			
			// 한번이라도 터졌다면 중력 처리
			if (flag) {
				// 콤보 횟수 +1
				combo++;
				
				// 열우선 역순회로 모든 블럭을 끌어내림
				for (int i = 0; i < 6; i++) {
					int bottom = 11;	// 블럭이 없는 가장 아래 칸 인덱스
					int last = 11;		// bottom보다 위에 있는 블럭 중 가장 아래 블럭
					
					// 하나의 열 중력 처리 로직
					while (bottom > 0 && last > 0) {
						
						// 인덱스가 0이 되기 전까지 블럭이 없는 인덱스를 탐색
						while (field[bottom][i] != '.' && bottom > 0) bottom--;
						
						// 끌어내릴 블럭은 bottom보다 하나 위에서부터 탐색 시작할 것
						last = bottom-1;
						// bottom이 0이거나 last가 0보다 작으면 더이상 위에 블럭이 없으므로 break
						if (bottom <= 0 || last < 0) break;
						
						// 범위 안에서 블럭이 있는 인덱스 탐색
						while (field[last][i] == '.' && last > 0) last--;
						
						// 블럭 위치 변경, 이전 블럭위치 초기화
						field[bottom][i] = field[last][i];
						field[last][i] = '.';
					}
				}
				
			// 한번이라도 터지지 않았으면 break
			} else break;
		}
		
		// 현재까지 터진 횟수 출력
		System.out.println(combo);
		sc.close();
	}
	
	private static boolean bfs(int i, int j) {
		
		// 시작 위치의 색상 저장
		char color = field[i][j];
		
		Queue<Integer> queue = new LinkedList<>();	// bfs용 큐
		List<Integer> block = new ArrayList<>();	// 연결된 블럭 각 위치
		
		queue.add(i*6+j);	// 1차원 인덱스 사용
		visited[i][j] = true;	// 큐에 넣을때 방문처리
		
		while (!queue.isEmpty()) {
			
			// 현재 큐에 들어있는 블럭만큼 순회할 것
			int repeat = queue.size();
			
			for (int k = 0; k < repeat; k++) {
				
				// 블럭하나 뽑고
				int curr = queue.poll();
				
				// 연결된 블럭 리스트에 추가
				block.add(curr);
				
				// 필드에서의 행렬 위치 구하기
				int r = curr/6;
				int c = curr%6;
				
				// 상하좌우 블럭 확인하면서
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 필드 범위 안에있고, 아직 방문하지 않았고, 색상이 동일하면
					// 큐에 추가하고 방문처리
					if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 &&
							!visited[nr][nc] && field[nr][nc] == color) {
						queue.add(nr*6+nc);
						visited[nr][nc] = true;
					}
				}
			}
		}
		
		// 연결된 블럭이 4개 이상이면 리스트를 돌며 해당위치 터뜨리고 true 반환
		if (block.size() >= 4) {
			for (int l : block) {
				field[l/6][l%6] = '.';
			}
			return true;
		}
		
		// 연결된 블럭이 4개 미만이면 터뜨리지 않고 false 반환
		return false;
	}
}