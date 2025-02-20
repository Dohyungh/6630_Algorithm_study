import java.util.*;
import java.lang.*;

class Solution {
    
    static final int MAX = 3_000;
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static char[] dChar = {'d', 'l', 'r', 'u'};
    static int[][] dist;
    static char[] answer;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = new char[k];
        
        // 각 칸에서 탈출로까지의 최단거리, 격자 바깥은 MAX
        dist = new int[n+2][m+2];
        Arrays.fill(dist[0], MAX);
        Arrays.fill(dist[n+1], MAX);
        for (int i = 1; i <= n; i++) {
            dist[i][0] = dist[i][m+1] = MAX;
            for (int j = 1; j <= m; j++) dist[i][j] = Math.abs(i-r) + Math.abs(j-c);
        }
        
        answer = new char[k];
        move(x, y, k, 0);
        
        if (answer[k-1] == '\u0000') return "impossible";
        return String.valueOf(answer);
    }
    
    public static void move(int r, int c, int rest, int idx) {
        if (rest <= 0) return;
        
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (dist[nr][nc] == MAX || dist[nr][nc] > rest-1 
                || (rest-1-dist[nr][nc]) % 2 != 0) continue;

            answer[idx] = dChar[d];
            move(nr, nc, rest - 1, idx + 1);
            break;
        }
    }

}