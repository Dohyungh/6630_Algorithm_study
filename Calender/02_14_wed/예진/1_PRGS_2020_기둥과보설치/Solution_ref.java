class Solution {
    boolean[][] pillars = new boolean[103][103];
    boolean[][] boards = new boolean[103][103];
    int N;
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        N = n;
        int cnt = 0;     
        for(int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2];
            int b = build_frame[i][3]; 
            if(b == 1) {        //설치
                if(a == 0 && buildPillar(x, y)) {   //기둥
                    pillars[x][y] = true;
                    cnt++;
                }
                else if(a == 1 && buildBoard(x, y)) {           //보
                    boards[x][y] = true;
                    cnt++;
                }
            }
            else {              //삭제
                if(remove(x, y, a)) cnt--;
            }
        }

        // 최종적으로 설치된 구조물 개수만큼 배열 생성
        answer = new int[cnt][3];

        // 구조물 정보를 추가할 때마다 인덱스 증가할 것
        int idx = 0;

        // 행 우선 순회, 기둥 먼저 확인해서 오름차순 정렬
        // 설치되어있는 구조물의 정보만 배열에 추가
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(pillars[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx][2] = 0;
                    idx++;
                }
                if(boards[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx][2] = 1;
                    idx++;
                }
            }
        }
        return answer;
    }    

   boolean buildPillar(int x, int y) {
        if(y == 0) return true;             // 바닥에 설치하려고 할 때
        if(pillars[x][y-1]) return true;    // 바로 아래 기둥이 있을 때
        // 바로 아래 보가 있을 때
        if(boards[x][y]) return true;
        if(x > 0 && boards[x-1][y]) return true;

        // 모두 아니면 불가
        return false;
    }

    boolean buildBoard(int x, int y) {
        // 설치하려는 보 아래 양쪽 중 하나라도 기둥이 있을 때
        if(pillars[x][y-1]) return true;
        if(pillars[x+1][y-1]) return true;

        // 설치하려는 보 양쪽으로 보가 있을 때
        if(x > 0 && boards[x-1][y] && boards[x+1][y]) return true;
        return false;
    }

    boolean remove(int x, int y, int type) {
        // flag 기본값: 참
        boolean flag = true;

        // 검사대상 삭제
        if(type == 0) {
            pillars[x][y] = false;
        }
        else {
            boards[x][y] = false;
        }

        // 전체 벽을 돌며 설치되어있는 구조물을 삭제하고 다시 설치해보면서 유효성 검증
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                if(pillars[i][j]) {
                    pillars[i][j] = false;
                    if(!buildPillar(i, j)) flag = false;
                    pillars[i][j] = true;
                }
                if(boards[i][j]) {
                    boards[i][j] = false;
                    if(!buildBoard(i, j)) flag = false;
                    boards[i][j] = true;
                }
            }
        }

        // 다시 설치하려고 할 때, 불가한 구조물이 있다면 대상 구조물 복귀, false반환
        // 모두 문제 없다면 삭제한 채로 true 반환
        if(!flag) {
            if(type == 0) {
                pillars[x][y] = true;
            }
            else {
                boards[x][y] = true;
            }
        }
        return flag;
    }
}