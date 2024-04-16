package boj_22865_가장먼곳;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

// 인접 리스트 & 우선순위 큐 사용
public class Main {
	
	// 노드 중심으로 다익스트라 알고리즘을 사용하므로 노드 클래스를 선언
	static class Node{
		int v;
		int w;
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	static int N;	// 자취할 땅 후보의 개수 N
	static int A;	// 친구 A의 위치
	static int B;	// 친구 B의 위치
	static int C;	// 친구 C의 위치
	
	// 노드를 담을 인접리스트
	static List<Node>[] adjList;
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		
		// 도로의 개수 M
		int M = sc.nextInt();
		
		// 인접리스트 생성
		// adjList[i]는 i번 노드와 연결된 노드를 담을 것
		adjList = new ArrayList[N+1];
		for (int i=0; i<N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			int w = sc.nextInt();
			adjList[st].add(new Node(ed, w));
			adjList[ed].add(new Node(st, w));
		}
		
		
		// A, B, C에서 각 모든 땅의 거리를 계산
		int[] distA = dijkstra(A);
		int[] distB = dijkstra(B);
		int[] distC = dijkstra(C);
		
		// "가장 먼 곳"에 집을 구하기 위해 maxMin을 사용
		// 땅 X를 기준으로 A, B, C까지의 최소 거리(min)를 선정하고,
		// 해당 min이 가장 큰 곳(maxMin)을 선정해 답을 구한다.
		int maxMin = Integer.MIN_VALUE;
		int maxIdx = -1;
		for (int i=1; i<N+1; i++) {
			if (i == A || i == B || i == C) {
				continue;
			}
			int min = Math.min(Math.min(distA[i], distB[i]), distC[i]);
			if (min > maxMin) {
				maxMin = min;
				maxIdx = i;
			}
		}
		
		System.out.println(maxIdx);
		
	}
	
	// 다익스트라 알고리즘
	public static int[] dijkstra(int start) {
		// start로부터 최소 거리 배열 생성
	    int[] dist = new int[N+1];
	    // 방문 체크
	    boolean[] visited = new boolean[N+1];
	    // 초기 비용은 가장 크게
	    Arrays.fill(dist, Integer.MAX_VALUE);
	    // start는 비용 0으로 만들어놓기
	    dist[start] = 0;

	    // 우선순위 큐 compare 사용해서 노드의 가중치를 기준으로 우선순위 설정
	    PriorityQueue<Node> pq = new PriorityQueue<>((node1, node2) -> Integer.compare(node1.w, node2.w));
	    pq.add(new Node(start, 0));	// 시작점 넣어주기

	    // 큐가 빌때까지
	    while (!pq.isEmpty()) {
	        Node current = pq.poll();
	        int currentNode = current.v;

	        // 이미 방문했던 노드라면 pass~
	        if (visited[currentNode]) {
	        	continue;
	        }
	        // 아니라면 우선 방문체크
	        visited[currentNode] = true;

	        // 인접리스트에 담긴 노드를 하나씩 꺼내면서
	        for (Node next : adjList[currentNode]) {
	        	// 아직 방문 X, 해당 노드를 방문했을 때 거리가 더 짧다면
	            if (!visited[next.v] && dist[currentNode] + next.w < dist[next.v]) {
	                dist[next.v] = dist[currentNode] + next.w;	// dist 배열을 갱신
	                pq.add(new Node(next.v, dist[next.v]));
	            }
	        }
	    }

	    return dist;
	}
	
	
	
}
