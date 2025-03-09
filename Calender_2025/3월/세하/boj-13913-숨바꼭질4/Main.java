package boj_13913_숨바꼭질4;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100000; // 좌표 범위
    static int[] visited = new int[MAX + 1]; // 방문한 위치를 저장 (초 기록)
    static int[] prev = new int[MAX + 1]; // 이전 위치 기록

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }

        bfs(N, K);
    }

    static void bfs(int N, int K) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = 1; // 시작 위치 방문 표시 (초 단위, 1부터 시작)

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : new int[]{current - 1, current + 1, current * 2}) {
                if (next >= 0 && next <= MAX && visited[next] == 0) {
                    queue.add(next);
                    visited[next] = visited[current] + 1; // 이동 시간 갱신
                    prev[next] = current; // 경로 저장

                    if (next == K) { // 목적지 도착
                        System.out.println(visited[next] - 1); // 시작을 1로 초기화했으므로 -1
                        printPath(N, K);
                        return;
                    }
                }
            }
        }
    }

    static void printPath(int N, int K) {
        List<Integer> path = new ArrayList<>();
        for (int at = K; at != N; at = prev[at]) {
            path.add(at);
        }
        path.add(N);
        Collections.reverse(path);
        for (int pos : path) {
            System.out.print(pos + " ");
        }
        System.out.println();
    }
}
