/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 걷기_1459
 * Date: 2025.03.12
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
        long x = Integer.parseInt(input[0]);
        long y = Integer.parseInt(input[1]);

        long side = Integer.parseInt(input[2]);
        long diag = Integer.parseInt(input[3]);

        long answer = 0;
        // 대각선 이동
        answer += Math.min(x, y) * Math.min(side*2, diag);
        
        long rest = Math.abs(x-y);
        // 수평 이동
        answer += (rest / 2) * Math.min(side*2, diag*2);
        answer += (rest % 2) * side;

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}