package PRGS._2025CodeChallenge_홀짝트리;

import java.util.*;
class Solution {
    
    static int[] p;
    
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = {0, 0};
        
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        
        int N = nodes.length;
        int E = edges.length;
        
        int max = -1;
        for (int i = 0; i < N; i++) {
            map.put(nodes[i], 0);
            max = Math.max(max, nodes[i]);
            
        }
        p = new int[max + 1];
        
        for (int i = 0; i < max+1; i++) {
            p[i] = i;
        }
        
        for (int i = 0; i < E; i++) {
            int no = edges[i][0];
            int de = edges[i][1];
            
            p[find(no)] = find(de);
            
            
            
            map.put(no, map.get(no) + 1);
            map.put(de, map.get(de) + 1);
        }
        
        boolean[] visited = new boolean[max + 1];
        
        for (int i = 0; i < N; i++) {
            if (visited[nodes[i]]) continue;
            
            int parent = find(nodes[i]);
            
            List<Integer> tree = new ArrayList<Integer>();
            List<Integer> reverseTree = new ArrayList<Integer>();
            
            if (map.get(nodes[i]) % 2 == 0 ^ nodes[i] % 2 == 0) {reverseTree.add(nodes[i]);}
            else {
                tree.add(nodes[i]);
            }
            
            int cnt = 1;
            for (int j = i + 1; j < N; j++) {
                if (find(nodes[j]) == parent) {
                    cnt++;
                    if (map.get(nodes[j]) % 2 == 0 ^ nodes[j] % 2 == 0) {reverseTree.add(nodes[j]);}
                    else {
                        tree.add(nodes[j]);
                    }
                    visited[nodes[j]] = true;
                }
            }
            
            if (tree.size() == 1 && tree.size() + reverseTree.size() == cnt) {
                answer[0]++;
            }
            if (reverseTree.size() == 1 && tree.size() + reverseTree.size() == cnt) {
                answer[1]++;
            }
            visited[nodes[i]] = true;
        }
        
        
        return answer;
    }
    int find(int x) {
        if (p[x] == x) return x;
        return p[x] = find(p[x]);
    }
}