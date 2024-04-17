package boj_4386_별자리만들기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int[] p;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();					// 별 개수
		double[][] stars = new double[N][2];	// 각 별의 좌표
		p = new int[N];							// 집합의 대표
		
		// 별 위치 입력
		for (int i = 0; i < N; i++) {
			stars[i] = new double[] {sc.nextDouble(), sc.nextDouble()};
		}
		
		// 예비 간선 수 계산 및 간선배열 초기화, 입력
		int edgeCnt = N*(N-1) / 2;
		Edge[] edges = new Edge[edgeCnt];
		int idx = 0;
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				
				// 인덱스를 사용하여 별 사이의 거리 구해 Edge 추가하기
				edges[idx++] = new Edge(i, j, getDist(stars[i], stars[j]));
			}
		}
		
		// 집합 대표 배열 초기화(자기자신 가리키도록)
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
		
		// 간선배열 정렬(거리가 짧은 순), Edge 클래스 Comparable 구현 필요
		Arrays.sort(edges);
		
		// 답안 실수형
		double answer = 0;
		
		// 확정 간선 개수
		int cnt = 0;
		for (Edge edge : edges) {
			
			// 집합의 대표를 찾아
			int a = findSet(edge.st);
			int b = findSet(edge.ed);
			
			// 같은 집합이라면 패스
			if (a == b) continue;
			
			// 아니면 Union
			p[b] = a;
			answer += edge.w;
			cnt++;
			if (cnt >= N-1) break;
		}
		
		// 소수점 둘째자리에서 반올림
		System.out.println(Math.round(answer*100) / 100.0);
		sc.close();
	}

	private static int findSet(int x) {
		if (x == p[x]) return x;
		return p[x] = findSet(p[x]);
	}

	private static double getDist(double[] st, double[] ed) {
		return Math.sqrt(Math.pow(st[0] - ed[0], 2) + Math.pow(st[1] - ed[1], 2));
	}
}

class Edge implements Comparable<Edge>{
	int st;
	int ed;
	double w;
	
	public Edge(int st, int ed, double w) {
		this.st = st;
		this.ed = ed;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(w, o.w);
	}
}
