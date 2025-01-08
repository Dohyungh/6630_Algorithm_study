/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 사이클 게임_20040
 * Date: 2025.01.08
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static int[] p;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;

        int answer = 0;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = findSet(Integer.parseInt(st.nextToken()));
            int b = findSet(Integer.parseInt(st.nextToken()));

            if (a == b) {
                answer = i;
                break;
            }

            unionSet(a, b);
        }

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

    private static int findSet(int num) {
        if (p[num] == num) return num;
        return p[num] = findSet(p[num]);
    }

    private static void unionSet(int a, int b) {
        p[a] = b;
    }
}