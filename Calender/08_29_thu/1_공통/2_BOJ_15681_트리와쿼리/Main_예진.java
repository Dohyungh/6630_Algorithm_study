/*
 * 입력값으로 인접리스트를 만들고,
 * 주어진 루트에서 시작해 트리구조로 순회하며 각 정점의 수를 배열에 미리 저장한다.
 * 조회로 주어지는 번호를 통해 저장된 배열에서 값을 바로 가져온다.
 */
package boj_15681_트리와쿼리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static List<Integer>[] adj;
	static int[] tree;
	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int root = Integer.parseInt(input[1]);
		int q = Integer.parseInt(input[2]);
		
		// 인접리스트 초기화
		adj = new List[N+1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		// 인접리스트 입력
		for (int i = 0; i < N-1; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		// tree배열에 각 정점을 루트로 하는 서브트리의 정점개수 저장
		tree = new int[N+1];
		makeTree(root, 0);
		
		// 입력된 쿼리에 따라 저장된 값 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			int node = Integer.parseInt(br.readLine());
			sb.append(tree[node] + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

	/**
	 * 현재 정점 번호와 부모의 번호를 통해 자신을 포함한 자식노드들의 개수 합을 배열에 저장하고 반환하는 메서드
	 * @param curr
	 * @param parent
	 * @return
	 */
	private static int makeTree(int curr, int parent) {
		int count = 1;
		
		for (int node : adj[curr]) {
			if (node != parent) count += makeTree(node, curr);
		}
		
		return tree[curr] = count;
	}
}
