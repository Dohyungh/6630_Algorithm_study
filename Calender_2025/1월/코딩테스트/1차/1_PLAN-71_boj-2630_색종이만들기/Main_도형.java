package BOJ._1647_도시분할계획;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int no;
		int de;
		int val;
		
		Edge(int no, int de, int val) {
			this.no = no;
			this.de = de;
			this.val = val;
		}
		
		@Override
		public int compareTo(Edge o) {
		
			return this.val - o.val;
		}
	}
	
	static int[] p;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		if (N == 2) {
			System.out.println(0);
			return;
		}
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		
		for (int i = 0; i < M; i++) {
			pq.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}
		
		p = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
		
		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			
			Edge curr = pq.poll();
			
			if (find(curr.no) == find(curr.de)) {
				continue;
			}
			
			union(curr.no, curr.de);
			
			sum += curr.val;
			cnt++;
			if (cnt == N-2) break;
		}
		System.out.println(sum);
	}
	
	static int find(int idx) {
		if (p[idx] == idx) return idx;
		
		return p[idx] = find(p[idx]);
	}
	
	static void union(int no, int de) {	
		
		p[find(no)] = find(de);
	}

}
