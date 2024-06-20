package prgs_리코쳇로봇;

import java.util.*;

class Solution {
    
    static int[][] map; // 맵 정보를 저장할 배열
    static boolean[][] visited; // 방문 여부를 저장할 배열
    static int[][] dist; // 각 위치까지의 최단 거리를 저장할 배열
    
    // 이동 방향을 나타내는 배열 (우, 좌, 하, 상)
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    
    public int solution(String[] board) {
        
        int rows = board.length;
        int cols = board[0].length();
        
        map = new int[rows][cols]; // 맵 초기화
        visited = new boolean[rows][cols]; // 방문 여부 배열 초기화
        dist = new int[rows][cols]; // 거리 배열 초기화
        
        // 맵 정보 입력
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                char c = board[i].charAt(j);
                if (c == '.'){
                    map[i][j] = 0; // 빈 공간은 0으로
                }
                else if (c == 'D'){
                    map[i][j] = -1; // 장애물은 -1로
                }
                else if (c == 'R'){
                    map[i][j] = -2; // 로봇의 시작 위치는 -2로
                }
                else if (c == 'G'){
                    map[i][j] = -3; // 목표 지점은 -3으로
                }
            }
        }
        
        // 로봇의 시작 위치(map[i][j] == -2인 경우)에서 DFS 탐색 시작
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (map[i][j] == -2){
                    dfs(i, j, 0);
                }
            }
        }
        
        // 목표 지점까지의 최단 거리 찾기
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
            	// 목표 지점까지의 최단 거리가 만약 존재한다면(0 이상이라면)
                if (map[i][j] == -3 && dist[i][j] != 0){
                    return dist[i][j]; // 목표 지점까지의 최단 거리를 반환
                }
            }
        }
        
        return -1; // 목표 지점에 도달할 수 없는 경우 -1 반환
    }
    
    // 벽 or 장애물에 부딪힐 때까지 델타 배열을 증가 & DFS로 맵을 탐색하며 시작부터의 최단 거리(depth+1)를 계산
    static void dfs(int row, int col, int depth) {
        for (int d = 0; d < 4; d++) {
            int nr = row;
            int nc = col;

            // 현재 방향으로 이동할 수 있을 때까지 반복
            while (nr + dr[d] >= 0 && nr + dr[d] < map.length && nc + dc[d] >= 0 && nc + dc[d] < map[0].length && map[nr + dr[d]][nc + dc[d]] != -1) {
                nr += dr[d];
                nc += dc[d];
            }

            // while문에서 한 번도 증가되지 않을 수도 있음
            // 즉, 이동이 불가능한 경우 다음 방향으로
            if (nr == row && nc == col) {
                continue;
            }
            
            // 목표 지점에 도달한 경우 거리를 갱신
            if (map[nr][nc] == -3) {
            	// 도달한 적이 없거나(dist가 0), dist가 최단 거리가 아닌 경우에 갱신
                if (dist[nr][nc] == 0 || dist[nr][nc] > depth + 1) {
                    dist[nr][nc] = depth + 1;
                }
                return;
            }
            
            // 아직 방문하지 않았거나 더 짧은 경로를 찾은 경우 거리 업데이트 및 재귀 호출
            if (dist[nr][nc] == 0 || dist[nr][nc] > depth + 1) {
                dist[nr][nc] = depth + 1;
                // 이제 다음 최단 거리를 계산하기 위해 dfs로 재귀!
                dfs(nr, nc, depth + 1);
            }
        }
    }
}
