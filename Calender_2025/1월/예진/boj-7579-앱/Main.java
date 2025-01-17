/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 앱_7579
 * Date: 2025.01.16
 */

 import java.util.*;
 import java.lang.*;
 import java.io.*;
 
 public class Main {
     static BufferedReader br;
     static BufferedWriter bw;
     static StringTokenizer st;
     static final int INFINITE = 2_000_000_000;
 
     public static void main(String[] args) throws Exception {
 
         br = new BufferedReader(new InputStreamReader(System.in));
         bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
         String[] input = br.readLine().split(" ");
         int n = Integer.parseInt(input[0]);
         int m = Integer.parseInt(input[1]);
 
         Program[] programs = new Program[n];
         st = new StringTokenizer(br.readLine(), " ");
         for (int i = 0; i < n; i++) {
             programs[i] = new Program(Integer.parseInt(st.nextToken()));
         }
         st = new StringTokenizer(br.readLine(), " ");
         for (int i = 0; i < n; i++) {
             programs[i].cost = Integer.parseInt(st.nextToken());
         }
 
         Arrays.sort(programs);
         int[] dp = new int[m+1];
         Arrays.fill(dp, INFINITE);
 
         for (int i = 0; i < n; i++) {
             int memo = programs[i].memory;
             int cost = programs[i].cost;
             for (int j = m; j >= 0; j--) {
                 if (j <= memo) dp[j] = Math.min(dp[j], cost);
                 else dp[j] = Math.min(dp[j-memo] + cost, dp[j]);
             }
         }
 
         bw.write(String.valueOf(dp[m]));
         bw.flush();
         bw.close();
         br.close();
     }
 }
 
 class Program implements Comparable<Program>{
     int memory;
     int cost;
 
     Program() {}
 
     Program(int memory) {
         this.memory = memory;
         this.cost = 0;
     }
 
     @Override
     public int compareTo(Program o) {
         return Integer.compare(this.memory, o.memory);
     }
 }