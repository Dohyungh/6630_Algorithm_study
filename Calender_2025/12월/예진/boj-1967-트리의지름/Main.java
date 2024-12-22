/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 트리의 지름_1967
 * Date: 2024.12.22
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static int n;
    static List<Node>[] adj;
    static int start;
    static int max;

    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine()) + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        while (br.ready()) {
            st = new StringTokenizer(br.readLine(), " ");
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[parent].add(new Node(child, w));
            adj[child].add(new Node(parent, w));
        }

        dijkstra(1);
        dijkstra(start);

        bw.write(String.valueOf(max));
		bw.flush();
		bw.close();
		br.close();
	}

    public static void dijkstra(int s) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Node(s, 0));
        dist[s] = 0;

        while(!pq.isEmpty()) {

            Node curr = pq.poll();

            if (max < curr.dist) {
                max = curr.dist;
                start = curr.num;
            }

            for (Node child : adj[curr.num]) {
                if (dist[child.num] > curr.dist + child.dist) {
                    dist[child.num] = curr.dist + child.dist;
                    pq.add(new Node(child.num, dist[child.num]));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int num;
    int dist;

    Node() {}
    Node(int num, int dist) {
        this.num = num;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.dist, o.dist);
    }
}