/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 공항_10775
 * Date: 2025.01.24
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static int[] next;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int G = Integer.parseInt(br.readLine());
        next = new int[G+1];
        for (int i = 1; i <= G; i++) next[i] = i;
        int P = Integer.parseInt(br.readLine());
        int[] planes = new int[P];
        int answer = 0;
        for (int i = 1; i <= P; i++) {

            int plane = Integer.parseInt(br.readLine());
            int spot = findSet(plane);
            if (spot == 0) {
                answer = i - 1;
                break;
            }
            int to = findSet(spot-1);
            next[spot] = next[to];
        }
        if (answer == 0) answer = P;
        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

    private static int findSet(int x) {
        if (next[x] == x) return x;
        return next[x] = findSet(next[x]);
    }
}