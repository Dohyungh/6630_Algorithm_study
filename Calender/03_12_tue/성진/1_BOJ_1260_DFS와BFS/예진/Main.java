package boj_1260_DFS와BFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	/*
	 * dfs 메서드 기저조건에 gragh[num] == null 이면 중단하라고 했는데, 쓸데없이 답 출력만 막는 부분이었다.
	 * 내 코드의 경우 시작점으로 지정된 노드가 아무와도 연결되어있지 않다면 배열에 노드가 선언되지 않았을거고, 순회를 시작할 때 위 조건에서 빠져나가면서 출력에서 제외될 것이다.
	 * 
	 * 또, 같은 경우에 bfs메서드에서 인접 노드를 큐에 넣기 위한 조건문에서 현재 노드가 널이 아닐때 라는 조건문을 넣어주어야 널포인터 에러가 안난다.
	 */
	
	static Node[] gragh;
	static boolean[] visited;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();		// 정점의 개수
		int M = sc.nextInt();		// 간선의 개수
		int V = sc.nextInt();		// 탐색을 시작할 정점의 번호
		gragh = new Node[1001];
		
		for (int i = 0; i < M; i++) {
			int idx1 = sc.nextInt();
			if (gragh[idx1] == null) {
				gragh[idx1] = new Node();
			}
			int idx2 = sc.nextInt();
			if (gragh[idx2] == null) {
				gragh[idx2] = new Node();
			}
			gragh[idx1].links.add(idx2);
			gragh[idx2].links.add(idx1);
		}

		visited = new boolean[1001];
		dfs(V);
		
		System.out.println();
		
		visited = new boolean[1001];
		bfs(V);
		
		sc.close();
	}
	
	public static void dfs(int num) {
		if (visited[num]) return;
		
		System.out.print(num+" ");
		visited[num] = true;
		
		if (gragh[num] != null && gragh[num].links.size() != 0) {
			Collections.sort(gragh[num].links);
			for (int n : gragh[num].links) {
				dfs(n);
			}
		}
	}
	
	public static void bfs(int num) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(num);
		visited[num] = true;
		
		while (!queue.isEmpty()) {
			
			int tmp = queue.poll();
			System.out.print(tmp + " ");
			
			if (gragh[tmp] != null && gragh[tmp].links != null) {
				for (int n : gragh[tmp].links) {
					
					if (!visited[n]) {
						queue.add(n);
						visited[n] = true;
					}
				}
			}
		}
	}

}

class Node {
	List<Integer> links = new ArrayList<>();
}
