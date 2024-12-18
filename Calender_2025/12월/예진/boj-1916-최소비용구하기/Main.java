/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 최소비용 구하기_1916
 * Date: 2024.12.18
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<City>[] adj = new List[n+1];
        int[] dist = new int[n+1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[start].add(new City(end, cost));
        }

        String[] input = br.readLine().split(" ");
        int start = Integer.parseInt(input[0]);
        int dest = Integer.parseInt(input[1]);

        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(start, 0));
        dist[start] = 0;
        int answer = 0;

        while (!pq.isEmpty()) {

            City curr = pq.poll();
            if (curr.num == dest) {
                answer = curr.dist;
                break;
            }

            for (City city : adj[curr.num]) {
                if (dist[city.num] > curr.dist + city.dist) {
                    dist[city.num] = curr.dist + city.dist;
                    pq.add(new City(city.num, dist[city.num]));
                }
            }
        }

        bw.write(String.valueOf(answer));
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class City implements Comparable<City> {
    int num;
    int dist;

    City () {}
    City (int num, int dist) {
        this.num = num;
        this.dist = dist;
    }

    @Override
    public int compareTo(City o) {
        return Integer.compare(this.dist, o.dist);
    }
}