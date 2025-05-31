import java.util.*;
import java.lang.*;

class Solution {
    static boolean[] visited;
    static int[] in;
    static int[] out;
    static List<Integer>[] adj;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        in = new int[1_000_001];
        out = new int[1_000_001];
        int n = 0;
        for (int[] edge : edges) {
            n = Math.max(n, Math.max(edge[0], edge[1]));
            in[edge[1]]++;
            out[edge[0]]++;
        }
        
        adj = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
            if (in[i] == 0 && out[i] > 1) answer[0] = i;
        }
        
        int[] out = new int[n+1];
        for (int[] edge : edges) adj[edge[0]].add(edge[1]);
        
        visited = new boolean[n+1];
        for (int gragh : adj[answer[0]]) {
            answer[check(gragh)]++;
        }
        
        return answer;
    }
    
    private static int check(int num) {
        if (out[num] > 1) return 3;
        if (out[num] == 0) return 2;
        if (visited[num]) return 1;
        visited[num] = true;
        
        return check(adj[num].get(0));
    }
}