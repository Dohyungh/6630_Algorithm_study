package boj_1976_여행가자;

import java.util.Scanner;

public class Main {
	static int[] parent;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i; // 초기에는 각각 자기 자신을 대표로 하는 집합
		}

		// union-find
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int isConnected = scanner.nextInt();
				if (isConnected == 1) {
					union(i, j);
				}
			}
		}

		int[] plan = new int[M];
		for (int i = 0; i < M; i++) {
			plan[i] = scanner.nextInt();
		}

		if (isPossible(plan)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	// 두 도시가 인접해있으면 union 실행 
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			parent[rootB] = rootA; // 한 집합으로 합치기
		}
	}

	// 해당 도시가 속한 집합의 대표 노드를 찾는 find 실행 
	static int find(int a) {
		if (parent[a] == a) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	// 여행 계획에 있는 모든 도시가 같은 집합에 속해 있는지 check
	static boolean isPossible(int[] plan) {
		int root = find(plan[0]);
		for (int city : plan) {
			if (find(city) != root) {
				return false;
			}
		}
		return true;
	}
}