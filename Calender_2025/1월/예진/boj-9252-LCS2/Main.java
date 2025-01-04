/**
 * Author: yngbao97, Yuk Yejin
 * Problem: LCS 2_9252
 * Date: 2025.01.04
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

        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();
        int[][] lcs = new int[A.length+1][B.length+1];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) lcs[i+1][j+1] = lcs[i][j] + 1;
                else lcs[i+1][j+1] = Math.max(lcs[i][j+1], lcs[i+1][j]);
            }
        }

        Stack<Character> word = new Stack<>();
        int i = A.length-1;
        int j = B.length-1;

        while (i >= 0 && j >= 0) {
            if (A[i] == B[j]) {
                word.push(A[i]);
                i--;
                j--;
            } else {
                if (lcs[i+1][j] >= lcs[i][j+1]) j--;
                else i--;
            }
        }

        bw.write(String.valueOf(word.size()) + "\n");
        while (!word.isEmpty()) bw.write(word.pop());
		bw.flush();
		bw.close();
		br.close();
	}
}