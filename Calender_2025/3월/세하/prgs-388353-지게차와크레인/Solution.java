
import java.util.*;

class Solution {

    static int n, m;
    static char[][] storageArr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();

        storageArr = new char[n][m];

        // storage -> storageArr로 복사
        for (int i = 0; i < n; i++) {
            storageArr[i] = storage[i].toCharArray();
        }

        for (int i = 0; i < requests.length; i++) {
            if (requests[i].length() == 1) {
                forklift(requests[i].charAt(0));
            } else {
                crane(requests[i].charAt(0));
            }
        }

        int answer = 0;

        // 남은 컨테이너 개수 세기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (storageArr[i][j] != '0') {
                    answer++;
                }
            }
        }

        return answer;
    }

    // 지게차 사용
    public void forklift(char c) {
        List<int[]> liftedContainers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 외부와 연결된 컨테이너의 index를 list에 삽입
                if (storageArr[i][j] == c && bfs(i, j)) {
                    liftedContainers.add(new int[]{i, j});
                }
            }
        }

        // 컨테이너 제거 
        for (int[] index : liftedContainers) {
            storageArr[index[0]][index[1]] = '0';
        }
    }

    // 크레인 사용
    public void crane(char c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (storageArr[i][j] == c) {
                    storageArr[i][j] = '0';
                }
            }
        }
    }

    // 컨테이너가 외부와 연결되어 있는지 판단하는 bfs
    public boolean bfs(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        queue.add(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            // 외부와 연결될 때 까지 queue에 삽입이 가능하면 true 반환
            if (curr[0] == 0 || curr[0] == n - 1 || curr[1] == 0 || curr[1] == m - 1) {
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];

                // 상하좌우로 storageArr[nr][nc]가 0이면 (컨테이너가 없는 곳이면) queue에 삽입
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && storageArr[nr][nc] == '0') {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }

        // 외부와 연결되지 않으면 false 반환
        return false;
    }
}
