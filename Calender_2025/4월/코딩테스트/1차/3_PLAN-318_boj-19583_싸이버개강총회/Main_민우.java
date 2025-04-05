package day_31.p3;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = parser(st.nextToken());
        int E = parser(st.nextToken());
        int Q = parser(st.nextToken());

        Map<String, Boolean> map = new HashMap<>();

        String input;

        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            int time = parser(st.nextToken());
            String name = st.nextToken();

            if (time <= S) map.put(name, false);
            else if (map.containsKey(name) && time >= E && time <= Q) map.put(name, true);
        }

        int cnt = 0;
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) cnt++;
        }

        System.out.println(cnt);
    }

    private static int parser(String str) {
        StringTokenizer st = new StringTokenizer(str, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }

}
