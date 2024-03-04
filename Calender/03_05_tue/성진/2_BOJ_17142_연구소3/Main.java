package boj_17142_연구소3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    
    static int N, M;
    static int[][] lab;
    static List<int[]> viruses = new ArrayList<>();
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        lab = new int[N][N];
        
        // 입력받으며 virus들의 위치를 찾아 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                lab[i][j] = sc.nextInt();
                if (lab[i][j] == 2) {
                	// 중괄호로 배열 새롭게 생성 후 add하기
                    viruses.add(new int[]{i, j});	
                }
            }
        }
        
        // selectViruses 메소드로 활성 바이러스를 고르고, 해당 바이러스들로 전염 시작
        boolean[] selected = new boolean[viruses.size()];
        selectViruses(selected, 0, 0);
        
        // selectViruses로 갱신된 answer를 출력
        // answer가 정수 최댓값에서 갱신이 한 번도 안됐다면 전염을 모두 시키지 못한 것이므로 -1로 바꾸기
        if (answer == Integer.MAX_VALUE) {
        	answer = -1;
        }
        
        System.out.println(answer);
        
        sc.close();
    }

    // 전체 바이러스 중 어떤 것을 활성 바이러스로 선택할 것인지 택하는 메소드
    static void selectViruses(boolean[] selected, int start, int count) {
        if (count == M) {
        	// count가 M일 때(활성화 바이러스 조합이 끝났을 때) 즉시 해당 조합으로 bfs를 실행
            bfs(selected);
            return;
        }
        
        // 조합을 이용하여 활성 바이러스의 경우의 수를 생성
        for (int i = start; i < viruses.size(); i++) {
            if (!selected[i]) {
                selected[i] = true;
                selectViruses(selected, i + 1, count + 1);
                selected[i] = false;
            }
        }
    }

    // 조합된 selected 배열을 파라미터로 하는 bfs 실행
    static void bfs(boolean[] selected) {
    	// 배열을 담을 수 있는 큐를 생성
        Queue<int[]> queue = new LinkedList<>();
        
        // 시간을 담기 위한 timeMap을 정의
        int[][] timeMap = new int[N][N];
        // timeMap의 모든 원소를 -1로 초기화
        for (int i = 0; i < N; i++) {
        	for (int j=0; j<N; j++) {
        		timeMap[i][j] = -1;
        	}
        }


        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                // 선택된 활성 바이러스들을 virus라는 배열을 새로 생성하고 담아준다.
                int[] virus = viruses.get(i);
                // x좌표, y좌표를 queue에 더해준다.(배열은 참조형....)
                queue.add(new int[]{virus[0], virus[1]});
                // 활성 바이러스의 시작 시점을 0으로 초기화
                timeMap[virus[0]][virus[1]] = 0;
            }
        }

        // bfs 시작
        while (!queue.isEmpty()) {
        	// queue에서 꺼낸 배열을 cur에 저장
            int[] cur = queue.poll();
            // x, y값을 저장
            int r = cur[0], c = cur[1];

            // 델타 배열로 하/상/우/좌 순서대로 돌며 주변을 갱신(바이러스 전염 검사)
            for (int d = 0; d < 4; d++) {
            	// 이동한 후의 위치를 nr, nc로 저장
                int nr = r + dr[d];
        		int nc = c + dc[d];
        		// 맵 범위 내에 있는지, 벽이 아닌 경우, timeMap이 -1일 때(아직 방문하지 않은 공간)만 실행
                if (nr >= 0 && nc >= 0 && nr < N && nc < N && lab[nr][nc] != 1 && timeMap[nr][nc] == -1) {
                	// 현재 위치의 time을 1만큼 늘려주고, queue에 삽입.
                    timeMap[nr][nc] = timeMap[r][c] + 1;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        
        // 시간을 계산할 것임
        int maxTime = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	// 연구실 중 감염 가능한 공간(비활성 바이러스 공간은 시간을 따지지 않는다!!!!!!!)이었고, 
                if (lab[i][j] == 0) {
                	// 감염되지 않은 곳이 경우(방문하지 않은 공간이 존재하는 경우) 끝내버리기
                    if (timeMap[i][j] == -1) {
                    	return;
                    }
                    // 전부 감염에 성공했다면 timeMap에서 max값을 갱신
                    maxTime = Math.max(maxTime, timeMap[i][j]);
                }
            }
        }
        // 최소 감염시간을 갱신
        answer = Math.min(answer, maxTime);
    }
}