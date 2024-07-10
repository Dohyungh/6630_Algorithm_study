package BOJ._2533_사회망서비스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
	
	// Node 클래스 생성
	static class Node implements Comparable<Node>{
		int idx;
		int degree;
		
		Node(int idx, int degree) {
			this.idx = idx;
			this.degree = degree;
		}
		
		// 차수로 우선순위 큐를 정렬할 것임
		@Override
		public int compareTo(Node o) {
			return this.degree - o.degree;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 인접리스트
		List<Integer>[] adj = new List[N+1];
		for (int i = 1; i < N+1; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		// 차수를 저장할 배열
		int[] degree = new int[N+1];

		// 입력받으면서 인접리스트 완성하고
		// 차수도 같이 저장함
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			int de = Integer.parseInt(st.nextToken());
			
			adj[no].add(de);
			adj[de].add(no);
			
			degree[no]++;
			degree[de]++;
		}
		
		// 본격적인 풀이 시작
		// 우선순위 큐로 leaf 노드를 쉽고 빠르게 탐색할 것임

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		// 일단 모든 노드를 다 넣고 시작
		for (int i = 1; i <= N; i++) {
			pq.offer(new Node(i, degree[i]));
		}
		
		// 방문 체크를 하기 위한 visited
		boolean[] visited = new boolean[N+1];
		
		int answer = 0;
		
		while(!pq.isEmpty()) {
			
			// 노드를 하나 뽑고
			Node node = pq.poll();
			
			int idx = node.idx;
			
			// 방문한 노드면 패스
			if (visited[idx]) continue;
			
			// 방문체크
			visited[idx] = true;
			
			// 우리가 관심있는 건 leaf 노드 뿐임
			// 우선순위 큐에서 노드를 뽑았을 때, 0이하인건 이미 탈락한거니까 그냥 넘어가면 되고
			// 1보다 큰 건 이미 모두 탈락했다는 뜻이므로 break 하면 됨
			if (node.degree == 1) {
				answer++;
			} else if (node.degree < 1){
				continue;
			} else {
				break;
			}
			// 사실 그냥 else { continue; } 하면 됨
			
			// adopter 로 만들 노드를 찾자.
			// adopter 로 만들 노드는 즉, leaf 노드의 부모 노드이다.
			int adopter = -1;
			
			// leaf 노드랑 연결된 애들 중에
			for (int connectedNode : adj[idx]) {
				// 차수가 0 보다 크고, 아직 방문안한(=이미 adopter인 경우 제외) 노드를 adopter로 만들자
				if (degree[connectedNode] > 0 && !visited[connectedNode]) {
					adopter = connectedNode;
					break;
				}
			}
			
			// adopter도 방문체크 해주자
			visited[adopter] = true;
			
			// adopter랑 연결된 애들을 전부 차수를 하나 낮춰서 우선순위 큐에 넣어주자
			for (int connectedNode : adj[adopter]) {
				degree[connectedNode]--;
				pq.offer(new Node(connectedNode, degree[connectedNode]));
			}
			
		}
		
		System.out.println(answer);
		
		br.close();
	}

}
