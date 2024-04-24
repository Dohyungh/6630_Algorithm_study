package BOJ._1800_인터넷설치;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main2 {
	static class Node implements Comparable<Node> {
		int idx;
		int cost;
		int from;
		PriorityQueue<Integer> pq;
		
		Node(int idx, int from, PriorityQueue<Integer> pq) {
			this.idx = idx;
			this.from = from;
			this.pq = pq;
		}
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public int compareTo (Node node) {
			return this.pq.peek() - node.pq.peek();
		}
		
		// 우선순위 큐의 길이를 K+1로 유지해주는 메서드
		public void add(Integer num) {
			if (this.pq.size() < K+1) {
				this.pq.add(num);
			} else {
				this.pq.add(num);
				this.pq.poll();
			}
		}
		@Override
		public String toString() {
			return "Node [idx=" + idx + ", cost=" + cost + ", from=" + from + ", pq=" + pq + "]";
		}
		
		
	}
	
	// 할인해주는 인터넷 선의 개수
	static int K;
	
	// N 번째 노드의 가장 작은 answer
	static int final_answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 노드의 개수
		int N = sc.nextInt();
		// 간선의 개수
		int P = sc.nextInt();
		// 할인해주는 개수
		K = sc.nextInt();
		
		// 인접리스트
		List<Node>[] adjList = new ArrayList[N+1];
		
		for (int i = 0; i < N+1; i++) adjList[i] = new ArrayList<Node>();
		
		for (int i = 0; i < P; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			
			// 양방향 
			adjList[from].add(new Node(to, cost));
			adjList[to].add(new Node(from, cost));
		}
		
		
		// 다익스트라 시작
		
		// 다익스트라에서 쓸 Node를 담을 우선순위 큐
		PriorityQueue<Node> nodePq = new PriorityQueue<Node>();
		
		// 시작노드 만들기
		PriorityQueue<Integer> startPq = new PriorityQueue<>();
		startPq.add(0);
		Node start = new Node(1, 1, startPq);
		
		// 시작노드 넣고 출발
		nodePq.add(start);
		
		// 백트래킹 용 테이블
		int[][] answer = new int[N+1][N+1];
		
		for (int i = 0; i < N+1; i++) {
			Arrays.fill(answer[i], Integer.MAX_VALUE);
		}
		
		// 최종 답안
		final_answer = Integer.MAX_VALUE;
		
		while(!nodePq.isEmpty()) {
			// 현재 노드
			Node nowNode =nodePq.poll();
			
			// 인접노드들에 대해서
			for (Node nextNode : adjList[nowNode.idx]) {
				
				// 현재 노드의 pq를 복사해서 각각의 인접노드에 보내줘야 함.
				PriorityQueue<Integer> tempPq = new PriorityQueue<>();
				PriorityQueue<Integer> nowPq = nowNode.pq;
				
				int size = nowPq.size();
				
				int[] temp = new int[size];
				
				for (int i = 0; i < size; i++) {
					temp[i] = nowPq.poll();
					tempPq.add(temp[i]);
				}
				
				for (int i = 0 ; i < size; i++) {
					nowPq.add(temp[i]);
				}
				// 여기까지 pq 복사
				
				// 새로운 노드 생성
				Node newNode = new Node(nextNode.idx, nowNode.idx, tempPq);
				
				// !! 이동할 간선의 가중치를 pq에 추가 !!
				newNode.add(nextNode.cost);
				
				// 추가했는데 백트래킹 테이블의 값보다 구리면 그냥 다익스트라 큐에 넣지 말고 넘어가자
				if (answer[newNode.from][newNode.idx] <= newNode.pq.peek() ) continue;
				
				// 백트래킹 테이블 업데이트
				answer[nowNode.idx][newNode.idx]= Math.min(answer[nowNode.idx][newNode.idx], newNode.pq.peek());
				
				// 만약 목적 노드에 도달했으면,
				if (newNode.idx == N) {
					// 정답을 업데이트 해볼 기회를 줌.
					final_answer = Math.min(final_answer, newNode.pq.peek());
				}
				if (newNode.idx != N) {
					// 목적 노드 이후로는 더 이상 진행할 필요 없음
					// 잔가지
					nodePq.add(newNode);
				}
				
			}
			
		}
		
		System.out.println(final_answer);
		
		
		
	}

}
