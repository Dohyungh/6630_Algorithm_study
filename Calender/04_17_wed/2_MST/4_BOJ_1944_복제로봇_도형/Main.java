package BOJ._1944_복제로봇;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[][] map;
    static boolean[][] adj; 
    static boolean[] visited;
    static int[] dr = {-1,1,0,0}; //상 하 좌 우
    static int[] dc = {0,0,-1,1}; //상 하 좌 우
    static int[] dAdj; // {-N,+N,-1,+1} N에 따라 달라지기 때문에 여기서 초기화 시킬 수는 없다.
    // 170번째 줄
    
    static int[] dist; // 프림 알고리즘의 거리 배열
    static boolean[] globalUsed; // 프림 알고리즘의 visited
    static int N;
    
    public static void main(String[] args) {
        
        // 250 * 100 * 250 = 625 0000
        
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();
        
        map = new int[N][N];
        int[][] keys = new int[M][2]; // 열쇠들의 좌표
        int[] start = new int[2]; // 시작 지점
        
        
        //인접행렬 쓰고싶어!!
        //메모리초과가 걱정돼!!
        //4방 연결이니까 인접행렬을 압축해서 쓰자
        
        // 상, 하, 좌, 우로 이동 가능한지 체크
        adj = new boolean[N*N][4];
        
        // getDist(=BFS) 에서 쓸 visited
        visited = new boolean[N*N];
        
        // 프림에서 쓸 visited
        globalUsed = new boolean[M+1];
        
        // 인접행렬 (N*N)에서 움직이려면
        // i * N + j 를 계속 해주면 됐는데,
        // 인접행렬을 압축시키면서
        // 열 번호 = 행 번호가 아니게 됐음 (대칭이 아니게 됐음)
        // 따라서 연결된 노드로 가기 위해 
        dAdj = new int[] {-N,+N,-1,+1};
        
        int idx = 0;
        
        for (int i = 0; i < N; i++) {
            String[] str = sc.nextLine().split("");
            
            for (int j = 0; j < N; j++) {
                if(str[j].equals("1")) map[i][j] = 1;
                else map[i][j] = 0; // 0, S, K 는 모두 갈 수 있는 곳. (벽이 아닌 곳)
                if(str[j].equals("S")) {
                    start[0] = i;
                    start[1] = j;
                }
                if(str[j].equals("K")) {
                    keys[idx][0] = i;
                    keys[idx++][1] = j;
                }
            }
        }
        
        
        adjUpdate();
        
        
        // 가장 짧은 간선을 포함한 노드를 선택해 나가자
        
        // dist에 0(시작점)을 포함시켰는데,
        // 그렇다 보니 인덱스가 계속 1씩 차이나서 디버깅 하기 힘들었다.
        
        // 다시 생각해보니 keys 배열 첫줄에 한 행 추가해서 start를 포함시켰으면 편했을듯
        // 근데 그럼 이해하기 힘드니까..
        dist = new int[M+1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[0] = 0; // start;
        //여기 까지 프림 준비 끝
        
        // 굳이 start 와 keys 를 분리해 갖구 이렇게 따로 써야함..
        for (int i = 1; i <= M; i++) {
            visited = new boolean[N*N];
            getDist(start, keys);
        }

        for (int i = 1; i <= M; i++) {
            visited = new boolean[N*N];
            
            // 아직 정보를 다 못 털어낸 노드 중에 가장 가까운 것 선택
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            
            for (int j =  1; j < dist.length; j++) {
            	if (!globalUsed[j-1] && dist[j] < min) {
            		min = dist[j];
            		minIdx = j-1;
            	}
            }
            
            // 종료 조건.
            // 아직 간선을 M-1개 다 못 골랐는데
            // -1이라는 건 갈 수 없는 열쇠가 있다는 뜻.
            if (minIdx == -1) {
            	System.out.println(-1);
            	return;
            }
            
            globalUsed[minIdx] = true;
            getDist(keys[minIdx], keys); 

        }

        int answer = 0;
        for (int i = 1; i <= M; i++) {
            answer += dist[i];
        }
        
        System.out.println(answer);
        sc.close();
    }
    
    
    // N*N by N*N 이 아니라,
    // N*N by 4 (4방탐색)    
    public static void adjUpdate() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                
                int adjIdx = i*N+j;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    
                    if (nr < N && nr >=0 && nc < N && nc >= 0 && map[i][j] == 0 && map[nr][nc] == 0) {
                        adj[adjIdx][d] = true;
                    }
                }
            }
        }
    } 
    
    // BFS로 거리 구하기
    public static void getDist(int[] start, int[][] keys) {
        
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(start[0]*N+start[1]);
        int sz = 1;
        int depth = 0; // 거리
        
        while(!queue.isEmpty()) {
            for (int c = 0; c < sz; c++) {
                int nowNode = queue.poll();
                
                for (int i = 0; i < keys.length; i++) {
                    if (nowNode == keys[i][0] * N + keys[i][1] && nowNode != start[0]*N+start[1] && !globalUsed[i]) {
                        dist[i+1] = Math.min(dist[i+1], depth);
                    }
                }        
                
                for (int d = 0; d < 4; d++) {
       
                    int nextNode= nowNode +dAdj[d];
                    if (adj[nowNode][d] && !visited[nextNode]) {
                        queue.add(nextNode);
                        visited[nextNode] = true;
                    }
                }
            }
            sz = queue.size();
            depth++;
            
        }
        
    }

}