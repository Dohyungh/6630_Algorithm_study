/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 싸이버개강총회_19583
 * Date: 2025.04.05
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static int[][] time;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        time = new int[3][2];
        String[] times = br.readLine().split(" ");
        for (int i = 0; i < 3; i++) {
            String[] t = times[i].split(":");
            time[i][0] = Integer.parseInt(t[0]);
            time[i][1] = Integer.parseInt(t[1]);
        }

        Set<String> entered = new HashSet<>();
        Set<String> exit = new HashSet<>();
        while (br.ready()) {
            String[] input = br.readLine().split(" ");
            String[] t = input[0].split(":");
            int[] logT = new int[2];
            logT[0] = Integer.parseInt(t[0]);
            logT[1] = Integer.parseInt(t[1]);

            if (enterCheck(logT)) entered.add(input[1]);
            else if (exitCheck(logT) && entered.contains(input[1])) exit.add(input[1]);

        }

        bw.write(String.valueOf(exit.size()));
		bw.flush();
		bw.close();
		br.close();
	}

    public static boolean enterCheck(int[] t) {
        if (t[0] < 0 || (t[0] == 0 && t[1] < 0)) return false;
        if (t[0] > time[0][0] || (t[0] == time[0][0] && t[1] > time[0][1])) return false;
        return true;
    }

    public static boolean exitCheck(int[] t) {
        if (t[0] < time[1][0] || (t[0] == time[1][0] && t[1] < time[1][1])) return false;
        if (t[0] > time[2][0] || (t[0] == time[2][0] && t[1] > time[2][1])) return false;
        return true;
    }
}