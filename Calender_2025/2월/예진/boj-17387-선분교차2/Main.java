/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 선분 교차 2_17387
 * Date: 2025.02.28
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

        Line l1 = inputLine();
        Line l2 = inputLine();

        int res1 = ccw(l1.p1, l2.p1, l2.p2);
        int res2 = ccw(l1.p2, l2.p1, l2.p2);
        int res3 = ccw(l2.p1, l1.p1, l1.p2);
        int res4 = ccw(l2.p2, l1.p1, l1.p2);

        int answer = 0;
        if (res1 != res2 && res3 != res4) answer = 1;
        else if (res1 == 0 && res2 == 0 && res3 == 0 && res4 == 0) {
            if (l1.p2.compareTo(l2.p1) >= 0 && l2.p2.compareTo(l1.p1) >= 0) {
                answer = 1;
            }
        }

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

    public static int ccw(Point a, Point b, Point c) {
        long[] v1 = {b.x - a.x, b.y - a.y, 0};
        long[] v2 = {c.x - a.x, c.y - a.y, 0};

        long result = v1[0] * v2[1] - v1[1] * v2[0];
        if (result > 0) return 1;
        else if (result < 0) return -1;
        return 0;
    }

    public static Line inputLine() throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        Point[] p = new Point[2];
        for (int j = 0; j < 2; j++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            p[j] = new Point(x, y);
        }
        return new Line(p[0], p[1]);
    }
}

class Line {
    Point p1;
    Point p2;

    Line (Point p1, Point p2) {
        this.p1 = p1.compareTo(p2) <= 0 ? p1 : p2;
        this.p2 = p1.compareTo(p2) <= 0 ? p2 : p1;
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;

    Point () {}
    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if (this.x == o.x) return this.y - o.y;
        return this.x - o.x;
    }
}