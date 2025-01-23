package BOJ._1389_케빈베이컨의6단계법칙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] bacon;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] adj = new boolean[N+1][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int no = Integer.parseInt(st.nextToken());
            int de = Integer.parseInt(st.nextToken());

            adj[no][de] = true;
            adj[de][no] = true;
        }

        bacon= new int[N+1];
        Arrays.fill(bacon, Integer.MAX_VALUE);
        for (int i = 1; i<=N; i++) {
            bfs(i, adj, N);
        }
//        System.out.println(Arrays.toString(bacon));
        int answerNum = Integer.MAX_VALUE;
        int answer = -1;
        for (int i = 1; i <= N; i++) {
            if (bacon[i] < answerNum) {
                answerNum = bacon[i];
                answer = i;
            }
        }

        System.out.println(answer);

    }
    static void bfs(int i, boolean[][] adj, int N) {
        Queue<Integer> q = new LinkedList<Integer>();
        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N+1];

        q.add(i);
        int depth = 0;

        while(!q.isEmpty()) {
            int sz = q.size();

            for (int j = 0; j < sz; j++) {
                int curr = q.poll();
                dist[curr] = depth;
                visited[curr] = true;

                for (int k = 1; k <= N; k++) {
                    if (adj[curr][k] && !visited[k]) {
                        q.add(k);
                        visited[k] = true;
                    }
                }

            }

            depth++;
        }
//        System.out.println(i + "번째 노드 bfs");
//        System.out.println(Arrays.toString(dist));
        int val = 0;
        for (int j = 1; j <= N; j++) {
            val += dist[j];
        }
        bacon[i] = val;

    }
}
