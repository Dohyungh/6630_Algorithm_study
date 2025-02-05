package day_05.pro3;

import java.io.*;
import java.util.*;

// 35분

public class Main {

    private static int N;
    private static int K;
    private static int min = 0;
    private static final Map<Integer, List<int[]>> map = new HashMap<>();
    private static final List<int[]> sel = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            map.put(i, new ArrayList<>());
            sel.add(null);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            map.get(k).add(new int[]{r, c});
        }

        combination(0, 0);

        System.out.println(min);
    }

    private static void combination(int idx, int selIdx) {
        if (selIdx == K) {
            int result = solve();
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < map.get(idx).size(); i++) {
            sel.set(selIdx, map.get(idx).get(i));
            combination(i + 1, selIdx + 1);
        }
    }

    private static int solve() {
        Collections.sort(sel, (o1, o2) -> {
            if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
            return Integer.compare(o1[0], o2[0]);
        });

        return (sel.get(K - 1)[0] - sel.get(0)[0]) * ((sel.get(K - 1)[1] - sel.get(0)[1]));
    }

}
