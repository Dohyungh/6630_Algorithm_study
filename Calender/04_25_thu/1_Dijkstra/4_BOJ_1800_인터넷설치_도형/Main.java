package BOJ._1800_인터넷설치;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Node implements Comparable<Node> {
		int idx;
		int cost;
		
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public int compareTo (Node node) {
			return this.cost - node.cost;
		}
		
		@Override
		public String toString() {
			return "Node [idx=" + idx + ", cost=" + cost + "]";
		}
		
		
	}
	static int N;
	static int K;
	static int left;
	static int right;
	static int mid;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int P = sc.nextInt();
		K = sc.nextInt();
		
		List<Node>[] adjList = new ArrayList[N+1];
		
		for (int i = 0; i < N+1; i++) adjList[i] = new ArrayList<Node>();
		
		// 이분탐색 시작
		right = Integer.MIN_VALUE;
		for (int i = 0; i < P; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			
			// 최댓값 찾아놓고 시작
			right = Math.max(cost, right);
			
			adjList[from].add(new Node(to, cost));
			adjList[to].add(new Node(from, cost));
		}
		// 최솟값도 찾아놓을걸
		left = 0;
		
		// 중간값으로 이분탐색
		mid = (left + right) /2;
		
		// 이분탐색 ㄱㄱ
		while (left < right) {
			// 다익스트라 안에서 left , right 을 조정해줌
			Dijkstra(adjList);
			// 만약 N을 도달 못했으면 break
			if (right == -1) break;
		}
		
		System.out.println(right);
		
		sc.close();
		
		}

	private static void Dijkstra(List<Node>[] adjList) {
		// 우선순위 큐 생성
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 시작 노드
		pq.add(new Node(1,0));
		
		// 거리 배열
		// 기존의 거리배열과는 조금 다르게
		// 지나온 간선 중 mid 값보다 큰 놈들의 개수를 세어 줄 거임
		int[] dist = new int[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		while(!pq.isEmpty()) {
			Node nowNode = pq.poll();
			
			// 안 유망하면 버려!
			// visited
			if (nowNode.cost > dist[nowNode.idx]) continue;
			
			for (Node nextNode : adjList[nowNode.idx]) {
				
				// 할인 기준 값보다 크면
				// dist 에 +1
				// dist에 들어있는 값보다 작으면 dist를 업데이트 해주고, pq에 넣어준다.
				if (nextNode.cost > mid) {
					if (nowNode.cost+1 < dist[nextNode.idx]) {
						dist[nextNode.idx]= nowNode.cost+1; 
						pq.add(new Node(nextNode.idx, nowNode.cost+1));
					}
				} else {
					if (nowNode.cost < dist[nextNode.idx]) {
						dist[nextNode.idx]= nowNode.cost; 
						pq.add(new Node(nextNode.idx, nowNode.cost));
					}
				}
			}
			
		}
		
		// dist[N] 을 한번도 안 건드렸으면 break.
		if (dist[N] == Integer.MAX_VALUE) {
			right = -1;
			return;
		}
		
		// dist[N] 이 K 보다 작거나 같으면 (pass면)
		// 더 빡빡한 조건으로 가는거고 (최솟값을 찾고 있으면 왼쪽탐색 - right 업데이트)
		// 아니면 더 널널한 조건으로 가는거임 (최솟값을 찾고 있으면 오른쪽 탐색 - left 업데이트)
		if (dist[N] <= K) {
			
			right = mid;
			
		} else {
			
			left = mid+1;
		}
		
		mid = (left+right) /2;
		
	}



}
