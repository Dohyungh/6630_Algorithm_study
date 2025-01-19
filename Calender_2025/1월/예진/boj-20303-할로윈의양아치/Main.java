/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 할로윈의 양아치_20303
 * Date: 2025.01.19
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static Group[] kids;
    static int[] p;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);
        kids = new Group[n+1];
        p = new int[n+1];
        Set<Group> realS = new HashSet<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            kids[i] = new Group(1, Integer.parseInt(st.nextToken()));
            realS.add(kids[i]);
            p[i] = i;
        }

        for (int i = 0; i < m; i++) {
            String[] rel = br.readLine().split(" ");
            int a = Integer.parseInt(rel[0]);
            int b = Integer.parseInt(rel[1]);

            int bossA = findSet(a);
            int bossB = findSet(b);

            if (bossA != bossB) {
                realS.remove(kids[bossB]);
                kids[bossA].children += kids[bossB].children;
                kids[bossA].candy += kids[bossB].candy;
                p[bossB] = bossA;
            }
        }

        List<Group> groups = new ArrayList<>(realS);
        Collections.sort(groups);

        int[] dp = new int[k];
        for (Group g : groups) {
            for (int i = k-1; i > 0; i--) {
                if (i >= g.children) dp[i] = Math.max(dp[i], dp[i - g.children] + g.candy);
            }
        }

        bw.write(String.valueOf(dp[k-1]));
		bw.flush();
		bw.close();
		br.close();
	}

    private static int findSet(int x) {
        if (p[x] == x) return x;
        return p[x] = findSet(p[x]);
    }
}

class Group implements Comparable<Group> {
    int children;
    int candy;

    Group(int children, int candy) {
        this.children = children;
        this.candy = candy;
    }

    @Override
    public int compareTo(Group o) {
        return Integer.compare(this.children, o.children);
    }

    @Override
    public String toString() {
        return children + "명 " + candy + "개";
    }
}