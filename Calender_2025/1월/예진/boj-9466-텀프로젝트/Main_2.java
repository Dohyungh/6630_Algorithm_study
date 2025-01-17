/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 텀 프로젝트_9466
 * Date: 2025.01.17
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int[] next;
    static boolean[] visited;
    static int notTeam;
    static Set<Integer> passed;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {

            int n = Integer.parseInt(br.readLine());
            next = new int[n+1];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= n; i++) next[i] = Integer.parseInt(st.nextToken());

            visited = new boolean[n+1];
            notTeam = n;

            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                passed = new HashSet<>();
                passed.add(i);
                if(checkNext(next[i]) == i) notTeam--;
                passed.clear();
            }
            bw.write(String.valueOf(notTeam)+ "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int checkNext(int curr) {
        if (visited[curr]) return curr;

        passed.add(curr);
        visited[curr] = true;

        int last = checkNext(next[curr]);
        if (passed.contains(last)) {
            notTeam--;
            if (last == curr) passed.remove(last);
        }
        return last;
    }
}