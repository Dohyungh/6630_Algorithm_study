/*
 * 설계 : 15분
 * 풀이 : 1시간 15분
 * 
 * 다잌스트라 너무 오랜만이라서 Node를 사용해야하지 Edge를 사용해야 하는지 헷갈렸다..
 * unionSet도 해야하나 헷갈려서 만들어놨다가 필요없길래 지웠다..이건 언제 쓰는거더라 다시 공부해야겠다
 * 결국 인접행렬과 Edge 섞어서 하다가, List<Edge>로 바꿨다가, 전에 민우가 다잌스트라 헤멜때 하던 풀이랑 똑같다는걸 깨달았다.
 * 그때 그렇게 Node로 바꾸라고 내가 쿠사리줬는데,,ㅋㅋ 다시 Node로 바꾸고 하니까 복잡하지 않게 해결됐다.
 * 
 * 1트때 50%에서 실패가 한번 나왔는데, 같은 정점 사이에 경로가 두개이상 있을 수도 있다고 한 걸 간과했다.
 * 단방향이라는 걸 강조한 부분인 줄 알았는데 실제로 같은 방향의 경로가 2개인 케이스가 있었다.
 * 초기화 할때 더 먼 경로가 짧은 경로의 경우를 덮어씌워버려서 생긴 문제였다.
 * 초기화 할때도 현재 길이보다 짧은 경로만 유효하게 처리했더니 통과했다.
 */

package boj_1753_최단경로;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();					// 정점의 개수
		int E = sc.nextInt();					// 간선의 개수
		List<Node>[] adj = new List[V+1];		// 인접 리스트: 도착지와 가중치 값을 가진 Node의 리스트로 구현(0인덱스 패딩)
		int[] dist = new int[V+1];				// 출발 정점부터 각 지점까지의 거리(0인덱스 패딩)
		boolean[] visited = new boolean[V+1];	// 출발 정점부터 각 지점까지 최소거리 확정여부(0인덱스 패딩)
		
		int start = sc.nextInt();	// 시작 정점 번호
		
		// 전체 거리 최대로 초기화, List 초기화(null값이면 add 불가)
		for (int i = 1; i <= V; i++) {
			dist[i] = INF;
			adj[i] = new ArrayList<>();
		}
		
		// 간선 입력
		for (int i = 0; i < E; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			int v = sc.nextInt();
			
			adj[st].add(new Node(ed, v));
		}
		
		// 우선순위 큐 생성 및 선언
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// 시작점까지의 거리 0 설정, 최소거리 확정 설정
		dist[start] = 0;
		visited[start] = true;
		
		// 시작점에서 갈 수 있는 각 정점들까지의 거리 갱신 후 pq에 추가, 최소값만 유효
		for (Node n : adj[start]) {
			if (dist[n.ed] > n.v) {
				dist[n.ed] = n.v;
				pq.add(n);
			}
		}
		
		// pq에 원소가 없을때까지
		while (!pq.isEmpty()) {
			
			// 하나 뽑아서
			Node curr = pq.poll();
			
			// 현재 위치의 최소거리가 이미 확정이면 패스, 아니면 지금 상태로 확정처리
			if (visited[curr.ed]) continue;
			visited[curr.ed] = true;
			
			// 현재 위치에서 갈 수 있는 정점의 지금 기록되어있는 거리와
			// 현재 위치까지의 거리 + 현재위치에서 가려는 정점까지의 거리를 비교
			// 최소거리가 갱신되면 pq에 추가
			for (Node n : adj[curr.ed]) {
				if (!visited[n.ed] && dist[n.ed] > dist[curr.ed]+ n.v) {
					dist[n.ed] = dist[curr.ed]+ n.v;
					pq.add(new Node(n.ed, dist[n.ed]));
				}
			}
		}
		
		// 모든 정점까지의 거리를 확인하면서 INF값이 갱신되지 않았으면 경로가 없음으로 INF 출력
		// INF값이 아니면 최소거리가 확인되었으므로 거리값 출력
		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
		sc.close();
	}
}

class Node implements Comparable<Node>{
	int ed;
	int v;
	
	Node(int ed, int v) {
		this.ed = ed;
		this.v = v;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(v, o.v);
	}
}