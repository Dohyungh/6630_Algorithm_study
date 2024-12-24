/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 별 찍기 - 11_2448
 * Date: 2024.12.24
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static boolean[][] star;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = n / 3;
        int k = (int) (Math.log(m) / Math.log(2));

        int col = (m * 5) + (m - 1);
        star = new boolean[n][col];
        star[0][col / 2] = star[1][col / 2 - 1] = star[1][col / 2 + 1] = true;
        for (int i = col / 2 - 2; i <= col / 2 + 2; i++) {
            star[2][i] = true;
        }

        int start = col / 2 - 2;
        int end = col / 2 + 2;
        for (int i = 0; i <= k; i++) {
            if (i == 0) {
                for (int j = 0; j < 3; j++) {
                    for (int c = 0; c < col; c++){
                        if (star[j][c]) bw.write("*");
                        else bw.write(" ");
                    }
                    bw.write("\n");
                }
            } else {
                int gap = (int) Math.pow(2, i - 1) * 3;
                start -= gap;
                end += gap;

                for (int j = gap; j < Math.pow(2, i) * 3; j++) {
                    for (int c = 0; c < col; c++) {
                        if (c == col / 2) bw.write(" ");
                        else if (c >= start && c < col / 2) {
                            if (star[j-gap][c+gap]) {
                                star[j][c] = true;
                                bw.write("*");
                            } else bw.write(" ");
                        } else if (c > col / 2 && c <= end) {
                            if (star[j-gap][c-gap]) {
                                star[j][c] = true;
                                bw.write("*");
                            } else bw.write(" ");
                        } else bw.write(" ");
                    }
                    bw.write("\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}