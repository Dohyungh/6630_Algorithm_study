
import java.util.*;

class Solution {
    static int length;
    static String[] answer;
    static Map<String, List<Integer>> map;
    static boolean[] visited;

    public String[] solution(String[][] tickets) {
        map = new HashMap<>();

        length = tickets.length; // 티켓의 개수

        for (int i = 0; i < length; i++) {
            String airport = tickets[i][0];
            List<Integer> indexList = new ArrayList<>(); // airport를 출발지로 하는 티켓의 index list

            // map에 이미 airport가 존재하면, 기존의 index list를 반환
            if (map.containsKey(airport)) {
                indexList = map.get(airport);
            }

            indexList.add(i); // 현재 index를 list에 추가
            map.put(airport, indexList);
        }

        answer = new String[length + 1];

        // ICN이 출발지인 모든 티켓에 대해 dfs 시행 
        for (Integer i : map.get("ICN")) {
            visited = new boolean[length];
            String[] route = new String[length + 1];

            visited[i] = true;
            route[0] = tickets[i][0];

            dfs(i, 1, route, tickets);
        }

        return answer;
    }

    public void dfs(int departure, int pick, String[] route, String[][] tickets) {
        String arrival = tickets[departure][1]; // 해당 티켓으로 방문할 수 있는 다음 도착지
        route[pick] = arrival; // 경로에 도착지 삽입

        // 티켓을 모두 골랐으면 queue에 경로 삽입 
        if (pick == length) {
            // answer에 저장된 값이 없거나 현재 경로가 사전순으로 더 앞설 경우, answer에 현재 경로 삽입 
            if (answer[0] == null || compareStr(route)) {
                answer = Arrays.copyOf(route, length + 1);
            }
            return;
        }

        // arrival을 출발지로 하는 티켓이 있을 경우
        if (map.get(arrival) != null) {
            List<Integer> indexList = map.get(arrival); // 해당 출발지가 포함되는 티켓의 index list 반환

            for (int i = 0; i < indexList.size(); i++) {
                int index = indexList.get(i);

                // 사용하지 않은 티켓일 경우
                if (!visited[index]) {
                    visited[index] = true; // 해당 티켓 방문 체크
                    dfs(index, pick + 1, route, tickets);
                    visited[index] = false; // 방문 체크 취소 
                }
            }
        }
    }

    public boolean compareStr(String[] route) {
        for (int i = 0; i < length + 1; i++) {
            // 현재 경로가 answer보다 사전순으로 앞서는 순간 true 반환
            if (route[i].compareTo(answer[i]) < 0) {
                return true;
            }
            // answer가 현재 경로보다 사전순으로 앞서는 순간 break 후 false 반환
            else if (route[i].compareTo(answer[i]) > 0) {
                break;
            }
        }
        return false;
    }
}
