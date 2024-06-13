/*
 * 설계: 10분
 * 풀이: 30분
 * 
 * 가중치가 없어서 dist 배열이나 Priority Queue 없이 
 * 1. 연결여부만 확인하고 연결되어 있다면 합집합으로 만들어준다.
 * 2. 여행계획을 순회하며 모든 도시가 같은 집합에 있는지 확인한다.
 * 
 * 같은 도시를 중복해서 방문할 수 있고, 다른 도시를 경유해서 가도 연결되어 있다고 가정했기 때문에
 * 같은 집합 안에만 존재하면 모두 여행할 수 있는 코스가 된다.
 */

package boj_1976_여행가자;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	// 동일 집합 여부 확인
	static int[] p;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();		// 도시 개수
		int M = sc.nextInt();		// 여행계획 순 도시 개수
		
		int[][] adj = new int[N+1][N+1];	// 인접행렬, 0인덱스 패딩
		int[] plan = new int[M];			// 여행 계획
		p = new int[N+1];					// 소속 집합, 0인덱스 패딩
		
		// 인접행렬 입력 및 소속 집합 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				adj[i][j] = sc.nextInt();
			}
			p[i] = i;
		}
		
		// 여행계획 입력
		for (int i = 0; i < M; i++) {
			plan[i] = sc.nextInt();
		}
		
		// 시작점, 여행계획 0번 인덱스
		int start = plan[0];
		
		// 가중치가 의미없으므로 queue를 통해 bfs처럼 순회
		Queue<Integer> queue = new LinkedList<>();
		
		// 시작지점 추가
		queue.add(start);
		
		// 큐가 빌 때까지
		while(!queue.isEmpty()) {
			
			// 도시하나 뽑고
			int curr = queue.poll();
			
			// 인접한 도시를 찾아 아직 같은 집합이 아닌경우 합집합 처리
			// 이제 처리하는 거면 처음 방문이므로 queue에 추가
			for (int i = 1; i <= N; i++) {
				if(adj[curr][i] == 1) {
					if(findSet(curr) != findSet(i)) {
						unionSet(curr, i);
						queue.add(i);
					}
				}
			}
		}
		
		// 답 기본값 YES
		String answer = "YES";
		
		// 여행계획을 순회하며 집합이 다른 도시가 있으면 여행 불가이므로 값 NO로 변경
		for (int i = 0; i < M; i++) {
			if (p[plan[i]] != start) {
				answer = "NO";
			}
		}
		
		// 출력
		System.out.println(answer);
		sc.close();
	}

	// 합집합 메서드
	private static void unionSet(int curr, int i) {
		p[i] = p[curr];
	}

	// 소속 집합 찾기
	private static int findSet(int x) {
		if (p[x] != x) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}
}