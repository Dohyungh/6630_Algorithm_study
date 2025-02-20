import java.util.*;
import java.lang.*;

class Solution {
    
    static Node[] ns;
    static Set<Integer> trees;
    static Set<Integer> holjjak;
    static Set<Integer> yeok;
    static boolean flag;
    
    public int[] solution(int[] nodes, int[][] edges) {

        ns = new Node[1_000_001];
        holjjak = new HashSet<>();
        yeok = new HashSet<>();
        trees = new HashSet<>();
        
        // 노드 초기화, 트리 초기화
        for (int n : nodes) {
            ns[n] = new Node(n);
            trees.add(n);
        }
        
        // 간선 입력, 트리 구분
        for (int[] e : edges) {
            ns[e[0]].child.add(e[1]);
            ns[e[1]].child.add(e[0]);
            
            union(findSet(e[0]), findSet(e[1]));
        }
        
        // 모든 노드에 대해 자신이 루트일 때 홀짝 트리 또는 역홀짝 트리가 되는지 확인 
        // 속한 트리가 이미 홀짝/역홀짝 확인이 됐다면 중복 확인 안함
        for (int n : nodes) {
            // 자신이 루트라면 홀짝인지 역홀짝인지
            boolean isHol = Math.abs(n - ns[n].child.size()) % 2 == 0;
            
            // 트리를 구분하는 우두머리 노드를 구해서 이미 확인됐는지 확인 후 아니라면 순회 시작
            int head = findSet(n);
            if ((isHol && !holjjak.contains(head))      // 자신이 홀짝노드이고 홀짝 트리에 목록에 아직 없는지
                || (!isHol && !yeok.contains(head))) {  // 자신이 역홀짝 노드이고 역홀짝 트리 목록에 아직 없는지
                
                flag = true;    // flag 초기화(dfs 순회중 하나라도 false가 나오면 순회 중단)
                // dfs에서 최종 true가 반환된다는 것은 모두 같은 종류의 노드라는 것(홀짝 또는 역홀짝)
                if (dfs(0, n, isHol)) {
                    if (isHol) holjjak.add(head);
                    else yeok.add(head);
                };
            }
        }
        
        return new int[] {holjjak.size(), yeok.size()};
    }
    
    private static boolean dfs(int before, int node, boolean isHol) {
        // 순회 중 이미 다른 유형의 노드가 나왔거나, 내가 다른 유형의 노드인 경우 리턴
        if (!flag || (before != 0 
            && (Math.abs(node - (ns[node].child.size() - 1)) % 2 == 0) != isHol)) return false;
        
        for (int next : ns[node].child) {
            if (next == before) continue;
            
            if (!dfs(node, next, isHol)) {
                flag = false;
                break;
            }
        }
        
        return flag;
    }
    
    private static int findSet(int x) {
        if (x == ns[x].p) return x;
        return ns[x].p = findSet(ns[x].p);
    }
    
    private static void union(int x, int y) {
        ns[x].p = y;
        trees.remove(x);
    }
}

class Node {
    List<Integer> child;
    int p;
    
    Node (int p) {
        child = new ArrayList<>();
        this.p = p;
    }
}