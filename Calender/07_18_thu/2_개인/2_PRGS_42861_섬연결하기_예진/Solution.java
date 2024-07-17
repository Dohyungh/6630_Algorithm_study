/*
 * 크루스칼 기본 문제였다.
 * 
 * [풀이 방법]
 * 1. 주어진 costs 배열({지점1, 지점2, 간선비용})을 간선비용(인덱스 2) 기준으로 오름차순 정렬한다.
 * 2. 해당 지점이 속한 집합의 우두머리를 나타내는 p배열을 초기화 한다. (초기에는 모두 자기자신)
 * 3. 간선정보를 앞에서부터 순서대로 순회하며 간선의 양끝 지점이 같은 집합이 아니라면 합집합 처리 후 간선 비용을 answer에 합한다.
 */

import java.util.*;

class Solution {
    
    static int[] p;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        p = new int[n];
        
        for (int i = 0; i < n; i++){
            p[i] = i;
        }
        
        Arrays.sort(costs, (o1, o2) -> {
            return o1[2] - o2[2];
        });

        for (int[] edge : costs) {
            int a = findSet(edge[0]);
            int b = findSet(edge[1]);
            if (a != b) {
                union(a, b);
                answer += edge[2];
            }
        }
        
        return answer;
    }
    
    static public int findSet(int x) {
        if (p[x] != x) p[x] = findSet(p[x]);
        return p[x];
    }
    
    static public void union(int x, int y) {
        p[y] = p[x];
    }
}