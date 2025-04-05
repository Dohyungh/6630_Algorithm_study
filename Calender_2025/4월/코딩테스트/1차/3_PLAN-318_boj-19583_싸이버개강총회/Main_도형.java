package BOJ._19583_싸이버개강총회;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class Time {
        int hour;
        int minute;
        Time(String str) {


            this.hour = Integer.parseInt(str.substring(0, 2));
            this.minute = Integer.parseInt(str.substring(3,5));
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        Time start = new Time(st.nextToken());
        Time end = new Time(st.nextToken());
        Time finale = new Time(st.nextToken());

        Map<String, Integer> map = new HashMap<String, Integer>();

        int answer = 0;

        while(br.ready()) {
            st = new StringTokenizer(br.readLine());
            if (st == null) break;
            if (!st.hasMoreTokens()) break;

            Time currTime = new Time(st.nextToken());
            String id = st.nextToken();

            if (isAEarlierThanB(currTime, start) >= 0) {
                map.put(id, 1);
            }

            if (isAEarlierThanB(currTime, end) <= 0 && isAEarlierThanB(currTime, finale) >= 0) {
                if (map.containsKey(id)) {
                    if (map.get(id) == 1) {
                        map.put(id, 2);
                        answer++;
                    }
                }
            }



        }


        System.out.println(answer);
    }

    // 0 이면 같은 시간
    // 1 이면 A가 더 빠름
    // -1 이면 B가 더 빠름
    static int isAEarlierThanB(Time A, Time B) {
        if (A.hour == B.hour && A.minute == B.minute) return 0;
        if (A.hour < B.hour) return 1;
        if (A.hour > B.hour) return -1;

        if (A.hour == B.hour && A.minute < B.minute) return 1;
        return -1;
    }
}
