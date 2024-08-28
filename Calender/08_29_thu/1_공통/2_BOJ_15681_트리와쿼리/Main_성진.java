package boj_15681_트리와쿼리;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int[] subTreeSize;
	static List<Integer>[] tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int R = sc.nextInt();
		int Q = sc.nextInt();

		tree = new ArrayList[N + 1]; // 리스트 배열 생성!
		subTreeSize = new int[N + 1];

		// 초기화
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		// 트리 생성
		for (int i = 0; i < N - 1; i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();

			tree[n1].add(n2);
			tree[n2].add(n1);
		}

		// 루트 노드부터 함수 실행
		calcSize(R, -1);

		// 쿼리 수행!
		for (int i = 0; i < Q; i++) {
			int n = sc.nextInt();
			System.out.println(subTreeSize[n]);
		}

	}

	// 문제 내 힌트의 방식을 차용
	static void calcSize(int curr, int parent) {

		// 정점 1개짜리 서브트리 초기화
		subTreeSize[curr] = 1;

		// curr 노드와 연결되어 있는 노드를 순회하며
		for (int i = 0; i < tree[curr].size(); i++) {
			// curr 노드의 부모가 아닌 자식 노드들인 경우
			if (tree[curr].get(i) != parent) {
				// 다시 함수를 호출
				calcSize(tree[curr].get(i), curr);
				// 자식 노드의 서브트리 사이즈를 더해주어야 함
				subTreeSize[curr] += subTreeSize[tree[curr].get(i)];
			}
		}

	}

}
