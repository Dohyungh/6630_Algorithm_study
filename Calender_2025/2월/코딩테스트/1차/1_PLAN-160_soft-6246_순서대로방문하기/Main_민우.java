package day_05.pro1;

import java.io.*;
import java.util.*;

// 60분 Sol

public class Main {

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static int N;
    private static int M;
    private static int[][] map;
    private static boolean[][] visited;
    private static final List<int[]> pathList = new ArrayList<>();
    private static boolean[][] isPath;
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        isPath = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            isPath[r][c] = true;
            pathList.add(new int[]{r, c});
        }

        dfs(pathList.get(0)[0], pathList.get(0)[1]);

        System.out.println(cnt);
    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;

        if (r == pathList.get(M - 1)[0] && c == pathList.get(M - 1)[1]) {
            int visitedCnt = 0;
            for (int[] path : pathList) {
                if (visited[path[0]][path[1]]) visitedCnt++;
            }

            if (visitedCnt == M) cnt++;
            visited[r][c] = false;
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (map[nr][nc] == 1 || visited[nr][nc]) continue;
            if (isPath[nr][nc]) {
                visited[nr][nc] = true;

                int changed = 0;
                boolean flag = visited[pathList.get(0)[0]][pathList.get(0)[1]];
                for (int[] path : pathList) {
                    if (flag != visited[path[0]][path[1]]) changed++;
                    flag = visited[path[0]][path[1]];
                }

                visited[nr][nc] = false;

                if (changed > 1) continue;
            }

            dfs(nr, nc);
            visited[nr][nc] = false;
        }
    }

}
