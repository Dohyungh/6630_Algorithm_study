package BOJ._1753_최단경로;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		
		int idx;
		int dist;
		
		Node(int idx, int dist) {
			this.idx = idx;
			this.dist= dist;
		}
		
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.dist - o.dist;
		}
	}
	
	static class Edge {
		int from;
		int to;
		int val;
		
		Edge(int from, int to, int val) {
			this.from = from;
			this.to = to;
			this.val = val;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		
		List[] adj = new List[V+1];
		
		for (int i = 0; i < V+1; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		
		int[] dist = new int[V+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			int val = Integer.parseInt(st.nextToken());
			
			adj[from].add(new Edge(from, to,val));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		dist[K] = 0;
		
		
		pq.add(new Node(K, 0));
		
		
		while(!pq.isEmpty()) {	
			
			Node curr = pq.poll();
			
			int idx = curr.idx;
			int d = curr.dist;
			if (d > dist[idx]) continue;
					
			for (int i = 0; i < adj[idx].size(); i++) {
				Edge e = (Edge) adj[idx].get(i);
				
				if (dist[e.to] > d + e.val) {
					dist[e.to]= d + e.val;
					pq.add(new Node(e.to, d + e.val));					
				}
				
			}
			
			
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i < V+1; i++) {
			if (dist[i]==Integer.MAX_VALUE) {
				bw.write("INF");
				bw.write("\n");
				
				
			} else {
				bw.write("" + dist[i]);
				bw.write("\n");
			}
		}
		bw.flush();
		
	}

}
