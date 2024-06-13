package boj_11437_LCA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_성진 {

    static int[] depth;
    static int[] p;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 노드의 수 입력
        int n = sc.nextInt();
        int[][] edges = new int[n - 1][2];

        // 간선 입력
        for (int i = 0; i < n - 1; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }

        List<Integer>[] adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }

        depth = new int[n + 1];
        p = new int[n + 1];
        
        boolean[] visited = new boolean[n + 1];

        // 깊이와 부모 노드 계산
        // depth는 0부터 시작, 루트는 1번 노드부터 시작
        dfs(adjList, 1, 0, visited);

        int M = sc.nextInt();

        for (int i = 0; i < M; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();

            System.out.println(findLCA(node1, node2));
        }
    }

    // 기존에 실패했던 getAncestors라는 메소드는 dfs처럼 재귀적으로 동작하면서 조상을 담아두지만,
    // 따로 조상리스트를 메모리에 담아두지 않고, dfs 재귀로 트리를 순회하면서 depth를 저장한다면
    // 전체의 조상 리스트를 담아두는 것보다 메모리 소모가 덜할 것
    static void dfs(List<Integer>[] adjList, int curr, int d, boolean[] visited) {
        visited[curr] = true;
        // 각 노드의 depth를 저장한다.
        depth[curr] = d;

        // 인접리스트를 돌면서 깊이 우선 탐색할 것
        for (int node : adjList[curr]) {
            if (!visited[node]) {
                p[node] = curr;	// 도형이 형 코드처럼 부모 노드를 저장하기
                dfs(adjList, node, d + 1, visited);
            }
        }
    }

    // LCA를 찾기 위해서 우선 확인하고자 하는 노드의 depth를 확인한다.
    // depth가 작은 노드를 기준으로 depth를 통일하고, 그 바로 위에 존재하는 부모 노드를 불러오면 된다.
    static int findLCA(int node1, int node2) {
        // 두 노드의 깊이를 맞추는 로직
    	// node1, node2 중 한 쪽의 depth가 다르다면 해당 노드의 부모 노드로 이동하고, 이를 반복
    	if (depth[node1] != depth[node2]) {
    		if (depth[node1] > depth[node2]) {
        		while(depth[node1] > depth[node2]) {
        			node1 = p[node1];
        		}
        	}
        	else {
        		while (depth[node2] > depth[node1]) {
                    node2 = p[node2];
                }	
        	}
    	}
    	
        // 두 노드의 depth가 같아졌다면?
    	// 이번엔 두 노드의 번호가 같아질 때까지 부모로 이동한다.
        // 즉, 공통 조상 LCA를 찾는다.
        while (node1 != node2) {
            node1 = p[node1];
            node2 = p[node2];
        }

        return node1;
    }
}
