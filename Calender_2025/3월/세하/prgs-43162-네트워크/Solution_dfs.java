
class Solution {

    static int[] p;

    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];

        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, visited, computers);
                answer++;
            }
        }

        return answer;
    }

    public void dfs(int r, boolean[] visited, int[][] computers) {
        visited[r] = true;

        for (int c = 0; c < visited.length; c++) {
            if (computers[r][c] == 1 && !visited[c]) {
                dfs(c, visited, computers);
            }
        }
    }
}
