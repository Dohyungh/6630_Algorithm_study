import java.util.*;
import java.lang.*;

class Solution {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static int[] sum;
    static int h;
    static int w;
    
    public int solution(int[][] land) {
        h = land.length;
        w = land[0].length;
        
        sum = new int[w];
        visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!visited[i][j] && land[i][j] == 1) check(i, j, land);
            }
        }
        
        int answer = 0;
        for (int i = 0; i < w; i++) answer = Math.max(answer, sum[i]);
        return answer;
    }
    
    public static void check(int r, int c, int[][] land) {
        int left = w-1;
        int right = 0;
        int cnt = 0;
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {r, c});
        visited[r][c] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            
            left = Math.min(left, curr[1]);
            right = Math.max(right, curr[1]);
            cnt++;
            
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];
                
                if (nr < 0 || nr >= h || nc < 0 || nc >= w 
                   || visited[nr][nc] || land[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});
            }
        }
        
        for (int i = left; i <= right; i++) sum[i] += cnt;
    }
}