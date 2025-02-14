package PRGS._2025CodeChallenge_비밀코드해독;

import java.util.*;
class Solution {
    static int cnt = 0;
    static int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {
        
        
        int m = q[0].length;
        
        int[] curr = new int[5];
        solve(n,m,q,ans,0, curr);

        return answer;
    }
    
    void solve(int n, int m, int[][]q, int[] ans, int depth, int[] curr) {
        if (depth == 5) {
            for (int i = 0; i < q.length; i++) {
                int cnt = 0;
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {                   
                        if (q[i][j] == curr[k]) cnt++;
                    }
                }
                if (cnt != ans[i]) return;
            }
            
            answer++;
            return;
        }
        
        int before = depth == 0 ? 0 : curr[depth - 1];
        
        for (int i = before + 1; i<=n; i++) {
            curr[depth] = i;
            solve(n,m,q,ans,depth+1,curr);
        }
        
    }
    
}