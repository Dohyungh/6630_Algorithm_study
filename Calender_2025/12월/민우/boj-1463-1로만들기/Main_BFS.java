package day_27.BOJ_1463;

import java.io.*;
import java.util.*;

public class Main_BFS {

    static class Node implements Comparable<Node> {
        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(BFS(N));
    }

    private static int BFS(int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(N, 0));

        while (!pq.isEmpty()) {
            Node item = pq.poll();

            if (item.num == 1) return item.cnt;

            if (item.num % 3 == 0) pq.offer(new Node(item.num / 3, item.cnt + 1));
            if (item.num % 2 == 0) pq.offer(new Node(item.num / 2, item.cnt + 1));
            pq.offer(new Node(item.num - 1, item.cnt + 1));
        }

        return -1;
    }

}
