package 코테_boj_2630_색종이만들기;

/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 코테_boj_2630_색종이만들기
 * Date: 2025.01.09
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[][] rec;
	static int blue;
	static int white;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		rec = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				rec[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		search(0, 0, n);

		bw.write(String.valueOf(white) + "\n");
		bw.write(String.valueOf(blue));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void search(int r, int c, int length) {
		
		int color = findColor(r, c, length);
		if (color == 1) blue++;
		else if (color == 0) white++;
		else {
			int half = length / 2;
			search(r, c, half);
			search(r + half, c, half);
			search(r, c + half, half);
			search(r + half, c + half, half);
		}
		
	}

	private static int findColor(int r, int c, int length) {
		if (length == 1) return rec[r][c];
		
		int blue = 0;
		
		for (int i = r; i < r + length; i++) {
			for (int j = c; j < c + length; j++) {
				if(rec[i][j] == 1) blue++;
			}
		}
		
		if (blue == length*length) return 1;
		else if (blue == 0) return 0;
		
		return -1;
	}
}