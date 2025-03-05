import java.util.*;
import java.lang.*;

class Solution {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[][] store;
    static int n;
    static int m;
    static final char EMP = '\u0000';
    public int solution(String[] storage, String[] requests) {
        
        n = storage.length + 2;
        m = storage[0].length() + 2;
        store = new char[n][m];
        
        for (int i = 1; i < n-1; i++) {
            char[] tmp = storage[i-1].toCharArray();
            for (int j = 1; j < m-1; j++) store[i][j] = tmp[j-1];
        }
        
        int answer = (n-2)*(m-2);
        for (String req : requests) {
            char c = req.charAt(0);
            
            // 지게차
            if (req.length() == 1) {
                boolean[][] ready = bfs();
                List<Integer> delete = new ArrayList<>();
                
                for (int i = 1; i < n-1; i++) {
                    for (int j = 1; j < m-1; j++) {
                        if (ready[i][j] && store[i][j] == c) {
                            store[i][j] = EMP;
                            answer--;
                        }
                    }
                }
                
            // 크레인
            } else {
                for (int i = 1; i < n-1; i++) {
                    for (int j = 1; j < m-1; j++) {
                        if (store[i][j] == c) {
                            store[i][j] = EMP;
                            answer--;
                        }
                    }
                }
            }
            System.out.println();
        }
        
        return answer;
    }
    
    public static boolean[][] bfs() {
        boolean[][] poss = new boolean[n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, 0});
        poss[0][0] = true;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                if (!poss[nr][nc] && store[nr][nc] == EMP) {
                    poss[nr][nc] = true;
                    queue.add(new int[] {nr, nc});
                } else poss[nr][nc] = true;
            }
        }
        
        return poss;
    }
}