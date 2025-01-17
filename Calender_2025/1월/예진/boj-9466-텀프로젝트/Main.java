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
 
     public static void main(String[] args) throws Exception {
 
         br = new BufferedReader(new InputStreamReader(System.in));
         bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
         int T = Integer.parseInt(br.readLine());
 
         for (int tc = 0; tc < T; tc++) {
 
             int n = Integer.parseInt(br.readLine());
             int[] next = new int[n+1];
             st = new StringTokenizer(br.readLine(), " ");
             for (int i = 1; i <= n; i++) next[i] = Integer.parseInt(st.nextToken());
 
             boolean[] visited = new boolean[n+1];
             Queue<Integer> queue = new ArrayDeque<>();
             int notTeam = n;
 
             for (int i = 1; i <= n; i++) {
                 if (visited[i]) continue;
 
                 int curr = i;
                 while (true) {
                     visited[curr] = true;
                     queue.add(curr);
 
                     if (next[curr] == curr) {
                         notTeam--;
                         queue.clear();
                         break;
                     } else if (next[curr] == i){
                         notTeam -= queue.size();
                         queue.clear();
                         break;
                     } else if (visited[next[curr]]) {
                         while(!queue.isEmpty() && queue.peek() != next[curr]) queue.poll();
                         notTeam -= queue.size();
                         queue.clear();
                         break;
                     }
                     curr = next[curr];
                 }
             }
             bw.write(String.valueOf(notTeam)+ "\n");
         }
 
         bw.flush();
         bw.close();
         br.close();
     }
 }