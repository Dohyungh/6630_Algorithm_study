package day_31.p1;

import java.io.*;
import java.util.*;

public class Main {

    private static final int MAX = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String result = bfs(N, K);
        System.out.println(result);
    }

    private static String bfs(int N, int K) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);

        boolean[] visited = new boolean[1 + MAX];
        visited[N] = true;

        int[] before = new int[1 + MAX];
        Arrays.fill(before, -1);

        int dist = 0;

        out:
        while (!q.isEmpty()) {
            int len = q.size();

            for (int i = 0; i < len; i++) {
                int node = q.remove();

                if (node == K) break out;

                int next1 = node - 1;
                if (next1 >= 0 && !visited[next1]) {
                    q.add(next1);
                    visited[next1] = true;
                    before[next1] = node;
                }

                int next2 = node + 1;
                if (next2 <= MAX && !visited[next2]) {
                    q.add(next2);
                    visited[next2] = true;
                    before[next2] = node;
                }

                int next3 = node * 2;
                if (next3 <= MAX && !visited[next3]) {
                    q.add(next3);
                    visited[next3] = true;
                    before[next3] = node;
                }
            }

            dist++;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(dist).append("\n");

        Deque<Integer> stack = new ArrayDeque<>();

        int curr = K;

        while (curr != -1) {
            stack.push(curr);
            curr = before[curr];
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        return sb.toString();
    }

}
