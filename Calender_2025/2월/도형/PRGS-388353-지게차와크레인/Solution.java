package PRGS._2025CodeChallenge_지게차와크레인;

import java.util.*;
class Solution {
    
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    
    public int solution(String[] storage, String[] requests) {
        
        int N = storage.length;
        int M = storage[0].length();
        
        char[][] map = new char[N + 2][M + 2];
        
        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(map[i], '-');
        }
        
        for (int i = 1; i <= N; i++) {
            char[] arr = storage[i-1].toCharArray();
            for (int j = 1; j <= M; j++) {
                map[i][j] = arr[j-1];
            }
        }
        
        int R = requests.length;
        
        for (int idx = 0; idx < R; idx++) {
            String ord = requests[idx];
            char chr = ord.charAt(0);
            boolean isCrane = ord.length() == 2 ? true : false;
            
            if (isCrane) {
                for (int i = 0; i < N+2; i++) {
                    for (int j = 0; j < M+2; j++) {
                        if (map[i][j] == chr) map[i][j] = '-';
                    }
                }
                continue;
            }
            
            Queue<int[]> q = new LinkedList<int[]>();
            
            q.add(new int[] {0,0});
            boolean[][] visited = new boolean[N+2][M+2];
            visited[0][0] = true;
            while(!q.isEmpty()) {
                
                int[] curr = q.poll();
                
                for (int d = 0; d < 4; d++) {
                    int nr = curr[0] + dr[d];
                    int nc = curr[1] + dc[d];
                    
                    if (nr >= 0 && nr < N+2 && nc >= 0 && nc < M+2 && !visited[nr][nc]) {
                        if (map[nr][nc] == '-') {
                            visited[nr][nc] = true;
                            q.add(new int[] {nr, nc});
                        }
                        if (map[nr][nc] == chr) {
                            map[nr][nc] = 'x';
                            visited[nr][nc] = true;
                        }
                    }
                    
                    
                }
            }
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < M + 2; j++) {
                    if (map[i][j] == 'x') map[i][j] = '-';
                }
            }
            
            
            
        }
        int cnt = 0;
        for (int i = 0; i < N + 2; i++) {
            System.out.println(Arrays.toString(map[i]));
            for (int j = 0; j < M + 2; j++) {
                if (map[i][j] != '-') cnt++;
            }
        }
        
        
        return cnt;
    }
}