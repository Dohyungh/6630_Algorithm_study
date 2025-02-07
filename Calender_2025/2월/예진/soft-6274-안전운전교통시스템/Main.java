import java.io.*;
import java.util.*;

public class Main {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] direc = {{}, {0, 1, 2},{3, 0, 1},{2, 3, 0},{3, 2, 1},{0, 1},{3, 0},{3, 2},{2, 1},{1, 2},{0, 1},{0, 3},{3, 2}};
    static int[] entrance = {0, 1, 0, 3, 2, 1, 0, 3, 2, 1, 0, 3, 2};

    static int n;
    static int t;
    static int answer;
    static boolean[][] visited;
    static int[][][] signal;
    static boolean[][][][] entered;
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        t = Integer.parseInt(input[1]);

        signal = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int s = 0; s < 4; s++) {
                    signal[i][j][s] = Integer.parseInt(st.nextToken());
                }
            }
        }

        visited = new boolean[n][n];
        entered = new boolean[n][n][4][4];
        answer = 0;
        dfs(0, 0, 0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int time, int r, int c, int from) {
        if (time > t) return;
    
        if (!visited[r][c]) {
            visited[r][c] = true;
            answer++;
        }

        int nt = time % 4;
        if (entered[r][c][nt][from]) return;
        entered[r][c][nt][from] = true;
        
        int currentSignal = signal[r][c][nt];
        if (entrance[currentSignal] != from) return;
        for (int d : direc[currentSignal]) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                dfs(time + 1, nr, nc, d);
            }
        }
    }
}