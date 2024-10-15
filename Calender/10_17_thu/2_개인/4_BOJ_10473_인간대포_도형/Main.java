package BOJ._10473_인간대포;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	// 각 대포와 대포 사이의 모든 거리(=시간)를 구하고
	// dist 배열을 출발점에서 뚜벅이 거리로 초기화
	// 각 대포와 도착점 사이의 최단거리를 구하고(adj 2차원 배열)
	// 다익스트라 돌리면 됨
	
	// 직선 거리만 구하는 공식 반복되니까 함수로 만듦.
	static double getDirectDist(Node from, Node to) {
		return Math.sqrt(Math.pow(from.x - to.x,2) + Math.pow(from.y - to.y,2));
	}
	
	// 대포랑 도착지를 저장할 class 생성
	static class Node {
		
		// 대포 N개 주어지면 0 번부터 N-1번까지는 대포고
		// 마지막 도착지를 마지막 N 번 인덱스에 넣을 거임
		int index;
		double x;
		double y;
		
		Node(int index, double x, double y) {
			this.index = index;
			this.x = x;
			this.y = y;
		}
		
		
		// 대포와 대포 혹은 대포와 도착지 사이의 거리(=시간)를 구할 때 쓸 함수
			// !! 출발지는 그냥 뚜벅이 거리가 최단 거리임. 대포가 없으니까
		double getDist(Node no) {
			// 대포와 대포 사이 거리
			double dist = getDirectDist(this,no); // 물리적 거리. 5로 나눠주면 시간을 얻을 수 있음.
			
			// 리턴하는 값은 시간임.
			
			// 50m 보다 밖이라서 대포 쏘는 게 무조건 이득
			if (dist > 50.0) return 2.0d + (dist - 50)/5;
			
			// 정확히 50m 면 그냥 2초 반환
			if (dist == 50.0) return 2.0d;
			
			// 50m 사정거리 안이면 그냥 뚜벅이랑 대포 쏘고 돌아오는 것 중에 더 빠른 거 반환
			if (dist < 50.0) return Math.min(dist/5, (50-dist)/5 + 2);
			
			return Double.MAX_VALUE;
		}
		
	}
	
	// 다익스트라에서 쓸 노드
	// 그 순간의 위치와 그 위치까지 도달하는 데 걸린 시간을 필드로 가짐
	static class DNode implements Comparable<DNode>{
		int index;
		double time;
		
		DNode(int index, double time) {
			this.index = index;
			this.time = time;
		}
		
		@Override
		public int compareTo(DNode o) {
			return Double.compare(this.time, o.time);
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		
		// 시간제한 1초 무서워서 버퍼 씀
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		double startX = Double.parseDouble(st.nextToken());
		double startY = Double.parseDouble(st.nextToken());
		
		// 시작 노드
		Node start = new Node(-1, startX, startY);
		
		st = new StringTokenizer(br.readLine());
		
		double endX = Double.parseDouble(st.nextToken());
		double endY = Double.parseDouble(st.nextToken());
		
		int N = Integer.parseInt(br.readLine());
		
		// 도착 노드
		Node end = new Node(N, endX, endY);
		
		// 대포들과 도착지를 담고 쓸 배열
		// 마지막은 대포가 아니라 도착지임에 주의
		Node[] cannons = new Node[N+1];
		cannons[N] = end;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cannons[i] = new Node(i, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		
		// 각 노드(대포 혹은 도착)
		// 0 ~ N-1은 대포
		// 마지막 N 자리에 도착
		double[][] adj = new double[N+1][N+1];
		
		for (int i = 0; i < N+1; i++) {
			for (int j = 0; j < N+1; j++) {
				
				// 도착지 는 도착하면 끝이니까 그냥 0
				if (i == N) continue;
				
				// 최단 거리로 adj 배열에 적어놓고 다익스트라에서 쓰도록 하자
				if (i < j) {
					adj[i][j] = cannons[i].getDist(cannons[j]);
					continue;
				}
				
				// 같은 위치면 0
				if (i == j) {
					adj[i][j] = 0.0d;
					continue;
				}
				
				// 대칭성 이용해서 빠르게
				if (i > j) {
					adj[i][j] = adj[j][i];
				}
			}
			
			
		}
		
		// 다익스트라 노드를 담을 pq
		// 시간 순으로 오름차순 정렬
		PriorityQueue<DNode> pq = new PriorityQueue<>();
		
		// dist 배열
		double[] dist = new double[N+1];
		
		for (int i = 0; i < N+1; i++) {
			
			// 뚜벅이 거리로 초기화
			double directTime = getDirectDist(start, cannons[i]) / 5.0d;
			
			// dist 배열에 적어놓고
			dist[i] = directTime;
			
			// 다익스트라 큐에도 담아주기
			pq.add(new DNode(i, directTime));
		}
		
		while(!pq.isEmpty()) {
			DNode curr = pq.poll();
			
			// visited 체크는 이렇게
			if (dist[curr.index] < curr.time) continue;
			
			// 모든 노드에 대해서 검사 (맞나?)
			for (int i = 0; i < N+1; i++) {
				
				// 기록이 단축될 때만
				if (curr.time + adj[curr.index][i] < dist[i]) {
					
					// dist에 적고
					dist[i] = curr.time + adj[curr.index][i];
					
					// 다익스트라 큐에 담아주자
					pq.add(new DNode(i, curr.time + adj[curr.index][i]));
				}
			}
			
		}
		System.out.println(dist[N]);
		
		
		
	}

}
