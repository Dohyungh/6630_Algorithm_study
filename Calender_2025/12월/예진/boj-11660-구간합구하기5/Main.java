/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 구간 합 구하기 5_11660
 * Date: 2024.12.18
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

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int arr[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int prefixSum[][] = new int[n][n];
        prefixSum[0][0] = arr[0][0];
        for (int i = 1; i < n; i++) {
            prefixSum[0][i] = prefixSum[0][i - 1] + arr[0][i];
            prefixSum[i][0] = prefixSum[i - 1][0] + arr[i][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + arr[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int stX = Integer.parseInt(st.nextToken()) - 1;
            int stY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;

            int answer = prefixSum[endX][endY];
            if (stX > 0) answer -= prefixSum[stX - 1][endY];
            if (stY > 0) answer -= prefixSum[endX][stY - 1];
            if (stX > 0 && stY > 0) answer += prefixSum[stX - 1][stY - 1];

            bw.write(String.valueOf(answer) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}