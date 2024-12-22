package boj_14500_테트로미노;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        visited = new boolean[N][M];
        
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        ans = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                visited[r][c] = true;
                sol(r, c, 1, arr[r][c]);
                visited[r][c] = false;
                checkSpecialShape(r, c);
            }
        }
        
        System.out.println(ans);
    }
    
    public static void sol(int r, int c, int cnt, int sum) {
        if (sum + (4 - cnt) * 1000 <= ans) return;
        
        if (cnt >= 4) {
            ans = Math.max(sum, ans);
            return;
        }
        
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
                visited[nr][nc] = true;
                sol(nr, nc, cnt + 1, sum + arr[nr][nc]);
                visited[nr][nc] = false;
            }
        }
    }
    
    public static void checkSpecialShape(int r, int c) {
        // 'ㅜ' 모양 체크 로직
        if (r + 1 < N && c + 2 < M) {
            ans = Math.max(ans, arr[r][c] + arr[r][c+1] + arr[r][c+2] + arr[r+1][c+1]);
        }
        if (r + 2 < N && c + 1 < M) {
            ans = Math.max(ans, arr[r][c] + arr[r+1][c] + arr[r+2][c] + arr[r+1][c+1]);
        }
        if (r > 0 && c + 2 < M) {
            ans = Math.max(ans, arr[r][c] + arr[r][c+1] + arr[r][c+2] + arr[r-1][c+1]);
        }
        if (r + 2 < N && c > 0) {
            ans = Math.max(ans, arr[r][c] + arr[r+1][c] + arr[r+2][c] + arr[r+1][c-1]);
        }
    }
}