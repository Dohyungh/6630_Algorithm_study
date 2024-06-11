package BOJ._11437_LCA;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 : 노드의 개수
        int N = Integer.parseInt(br.readLine());
        
        // 노드의 부모
        int[] p = new int[N+1];

        // BFS 를 위한 인접 리스트
        List[] adjList = new List[N+1];
        // 인접 리스트 초기화
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

        // N-1개 간선 입력
        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // node 각각을 no 와 de 로 지칭
            int no = Integer.parseInt(st.nextToken());
            int de = Integer.parseInt(st.nextToken());
            // 대칭되게 인접리스트 입력
            adjList[no].add(de);
            adjList[de].add(no);
        }
        
        // BFS 를 위한 visited 배열 생성
        boolean[] visited = new boolean[N+1];
        // BFS 를 이용해 각 노드의 부모를 찾는다.
        BFS(p, adjList, visited);
        
        // 입력 : LCA를 구해야하는 쌍의 개수
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            int de = Integer.parseInt(st.nextToken());
            
            // LCA 함수를 이용해 LCA 를 출력하면 끝
            System.out.println(LCA(no,de,p));

        }

    }

    // BFS 함수
    // 우리가 알던 BFS 와 똑같은데,
    // 한 depth 내려갈 때마다 그 이전에 연결된 친구를 부모로서 p 배열에 저장해줌
    private static void BFS(int[] p, List[] adjList, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        
        // 시작 노드는 1번 루트 노드
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();

            for(int i = 0; i < adjList[nowNode].size(); i++) {

                int nextNode = (int) adjList[nowNode].get(i);
                if(!visited[nextNode]) {
                	
                	// 우리가 알던 BFS에서 딱 이 한 줄만 추가됨
                    p[nextNode]=nowNode;
                    
                    visited[nextNode] = true;
                    queue.add(nextNode);
                }

            }
        }

    }

    private static int LCA(int no, int de, int[] p) {
    	
    	// getAncestors 함수를 이용해 주어진 노드의 부모들 (마지막은 항상 루트 노드인 1번 노드임)을 List로 받음
        List<Integer> noList = getAncestors(no,p);
        List<Integer> deList = getAncestors(de,p);
        
        int noSize = noList.size();
        int deSize = deList.size();
        int answer = 1;
        
        // 조상 리스트를 끝에서부터 (1에서 부터) 돌면서 서로 다른게 나오면 바로 그 이전 노드 번호가 LCA 임.
        for (int i = noSize-1, j = deSize-1; i>=0 || j>=0; i--,j--) {

            int noSide = i<0? noList.get(0) : noList.get(i);
            int deSide = j<0? deList.get(0) : deList.get(j);

            if (noSide != deSide) break;

            answer = noList.get(i);

        }
        return answer;
    }

    private static List<Integer> getAncestors(int node, int[] p) {
        List<Integer> ancestList = new ArrayList<Integer>();
        
        // 자기 자신부터 넣어야 함.
        ancestList.add(node);
        
        // 1번 루트 노드 만날 때까지 부모 노드를 전부 리스트에 담아줌.
        while(node != 1) {
            int parent = p[node];
            ancestList.add(parent);
            node = parent;
        }

        return ancestList;
    }
}
