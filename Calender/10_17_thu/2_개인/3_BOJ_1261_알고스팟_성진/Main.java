package boj_1261_알고스팟;

import java.util.*;

public class Main {
    // Point 클래스 생성
    static class Point implements Comparable<Point>{
        int r;  // 좌표 r
        int c;  // 좌표 c
        int cnt;    // 현재 포인트까지 부신 벽의 수

        public Point(int r, int c, int cnt){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
        // pq 사용을 위한 정렬 조건 추가
        @Override
        public int compareTo(Point p){
            return this.cnt - p.cnt;
        }
    }

    static int[][] map;     // map 생성
    static int[][] dist;    // 지금까지 부신 벽의 수를 담을 배열

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N;   // 가로
    static int M;   // 세로

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();

        map = new int[N][M];
        dist = new int[N][M];

        // dist 배열 큰 값으로 초기화
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }


        // 입력 받기
        for (int i=0; i<N; i++){
            String line = sc.next();
            char[] chArr = line.toCharArray();
            for (int j=0; j<M; j++){
                if (chArr[j] == '0'){
                    map[i][j] = 0;
                }
                else{
                    map[i][j] = 1;
                }
            }
        }

        // (0, 0)에서 벽 부수기 실행
        bfs(0, 0, 0);

        // 끝 방의 벽 부순 최소 개수 출력
        System.out.println(dist[N-1][M-1]);
    }

    static void bfs(int row, int col, int count){

        // 포인트 객체 생성
        Point point = new Point(row, col, count);

        // 우선순위 큐 생성
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(point);
        
        // 시작 지점(0, 0)의 벽 부순 횟수 0으로 초기화
        dist[row][col] = 0;

        // bfs
        while (!pq.isEmpty()){
            Point p = pq.poll();

            int r = p.r;
            int c = p.c;
            int cnt = p.cnt;

            // 포인트 객체 내의 벽 부순 횟수(cnt)와 현재 dist 배열 내의 값을 비교한다.
            // 비교했을 때, cnt가 더 크다면 더 진행(사방 탐색)할 필요가 없으므로 continue
            if (cnt > dist[r][c]){
                continue;
            }

            // 사방 탐색 진행
            for (int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M){
                    // 현재까지의 벽 부순 횟수(cnt)에 (nr, nc)의 값을 더해 newCnt를 생성
                    // 즉, 벽으로 막혀있다면(1) 1을 더하고, 벽이 없다면(0) 0을 더한다.
                    int newCnt = cnt + map[nr][nc];
                    // 벽 부순 횟수 갱신하고, pq에 더함
                    if (newCnt < dist[nr][nc]) {
                        dist[nr][nc] = newCnt;
                        pq.add(new Point(nr, nc, newCnt)); // 새로운 경로 추가
                    }
                }
            }
        }
    }
}
