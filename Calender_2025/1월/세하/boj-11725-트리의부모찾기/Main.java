package boj_11725_트리의부모찾기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int n = 0;

		List<Integer>[] adj = new ArrayList[N + 1];

		// 배열 List 초기화
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<Integer>();
		}

		// 인접 노드 입력
		while (++n < N) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			adj[A].add(B);
			adj[B].add(A);
		}

		int[] p = new int[N + 1]; // index번째 노드의 부모를 저장하는 배열

		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1); // 최상위 루트인 1 삽입

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			// queue에서 꺼낸 node와 인접한 node 순회
			for (Integer node : adj[curr]) {
				if (p[node] == 0) { // 인접 node의 부모가 아직 없을 경우 (즉, current node의 자식일 경우)
					queue.add(node); // 인접 node queue 삽입
					p[node] = curr; // current node를 해당 노드의 부모로 저장
				}
			}
		}

		for (int i = 2; i <= N; i++) {
			bw.write(p[i] + "\n");
		}

		bw.flush();
	}
}
