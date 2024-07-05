package PROG_2022KAKAO_양과늑대;

import java.util.*;

class Solution {
    
    static List<Integer>[] adj;
    static int[] who;
    static boolean[] visited;
    static int max;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        
        int N = info.length;
        who = info;  // 전역변수로 쓰려고 옮김
        visited = new boolean[N];
        
        // 인접리스트 초기화
        adj = new List[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // 인접리스트 입력
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
        }
        
        // 루트에서 양이 이미 한마리여서 초기값 1
        max = 1;
        
        // 루트부터, 양은 1마리, 늑대는 0마리로 시작
        dfs(0, 1, 0);
        
        return answer = max;
    }
    
    public void dfs(int node, int sheep, int wolf) {
        // 양 최대수 계속 업데이트
        if (sheep > max) max = sheep;
        
        // 자식노드 방문할건데
        for (int next : adj[node]) {
            
            // 가려는 곳이 이미 갔던 곳이면 그대로 통과
            if (visited[next]) dfs(next, sheep, wolf);
            
            // 가려는 곳이 양이면 양 추가해서 처음부터
            else if (who[next] == 0) {
                visited[next] = true;
                dfs(0, sheep+1, wolf);
                visited[next] = false;
            
            // 가려는 곳이 늑대면 늑대 추가해서 처음부터
            } else if (who[next] == 1) {
                // 근데 양이랑 늑대랑 수가 같아지는 지점이면 패스
                if (sheep <= wolf+1) continue;
                
                visited[next] = true;
                dfs(0, sheep, wolf+1);
                visited[next] = false;
            }
        }
    }
}