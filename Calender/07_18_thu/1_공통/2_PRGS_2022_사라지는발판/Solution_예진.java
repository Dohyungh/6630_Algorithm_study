/*
 * [접근하기 어려웠던 이유]
 * 문제를 보자마자 board가 5x5라서 dfs 완탐이구나 했는데, 기준을 어떻게 정해서 돌아야 할지 몰랐다.
 * 두 플레이어 중 '이길 수 있는' 플레이어는 게임을 최대한 빠르게 끝내고 싶어하고,
 * '질 수 밖에 없는' 플레이어는 게임을 최대한 오래 끌고 싶어하기 때문에
 * 그 중간 지점에서 어떻게 기준을 잡고 답을 찾아내야 하는지 알수가 없었다.
 * 
 * [풀이 방법]
 * 이길 수 있는 -> 한번이라도 이기는 경우가 있는
 * 질 수 밖에 없는 -> 모든 경우에서 지는
 * 
 * 재귀함수를 사용하는데 두 플레이어가 '번갈아' 이동하기 때문에 해당 부분이 적용되어야 하고,
 * 각 순서의 플레이어가 이길 수 있는 경우의 수인지 판단해서 최소횟수, 최대횟수를 고르기 때문에
 * 재귀함수 반환값에 한번이라도 이길 수 있는지 여부와 기준에 따라 최적의 이동횟수를 반환해야 했다. (객체로 반환)
 * 
 * [재귀함수]
 * 인수 : ax, ay, bx, by - 해당 순서 플레이어(a)의 현위치(ax, ay), 다른 플레이어(b)의 현위치(bx, by)
 * 
 * - 기저조건
 * 1. a플레이어가 현위치에서 이동불가한 경우
 * 2. a플레이어가 현위치에서 이동하면 게임이 끝나는 경우 (두 플레이어 위치가 같음)
 * 
 * - 변수 : canGo(한번이라도 이길 수 있는지 여부), 최소이동횟수, 최대이동횟수
 * 
 * - bfs
 * 1. 현재 위치 발판 없애기
 * 2. 사방탐색하여 이동할 수 있으면 재귀
 *      - 다른 플레이어 이동 순서임으로 인수의 순서가 바뀜
 *      - 재귀함수의 반환값은 '상대가 이길수 있는지 여부'와 '이동횟수'를 가지고 있음
 * 3. 상대가 이길 수 있는 플레이어라면 canGo 처리 및 최소이동횟수를 갱신하고, 아니라면 최대이동횟수를 갱신
 * 4. 사방 다 확인했으면 현재 위치 발판 복구
 * 5. 최종 return : 이길 수 있는 플레이어라면 최소이동횟수에 +1, 아니라면 최대이동횟수에 +1 해서 반환
 * 
 * 둘다 이길 수 있는 플레이어라면 두 플레이어 중 더 빨리 끝내는 경우의 이동횟수가 반환될 것이고,
 * 한명만 이길 수 있다면 재귀를 도는 중에 '질수밖에 없는 플레이어의 최대이동횟수값이 반환되는 순간'이 있을 거라서
 * 해당값을 기준으로 최종 반환값에 영향을 줄것이다.
 * 
 * 즉, 서로 최소값과 최대값의 상/하한선을 주고받으며 갱신하기 때문에 최종 반환값에 근거가 있다.
 */


import java.util.*;

class Solution {
    
    // 사방탐색 델타배열
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    static int[][] field;       // board 전역으로 쓸래여
    static int boardX;          // board 행길이
    static int boardY;          // board 열길이
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        
        boardX = board.length;
        boardY = board[0].length;
        field = new int[boardX][boardY];
        
        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                field[i][j] = board[i][j];
            }
        }

        return move(aloc[0], aloc[1], bloc[0], bloc[1]).moveCnt;
    }
    
    public Result move(int ax, int ay, int bx, int by) {

        // 현재 순서의 플레이어가 더이상 이동할 수 없다면 [Result: 0번 이동가능, 질수밖에] 표시
        if (isEnd(ax, ay)) return new Result(0, false);
        
        // 현재 순서의 플레이어가 이동하면 게임이 끝나는 경우 [Result: 1번 이동가능, 이길수있어] 표시
        if (ax == bx && ay == by) return new Result(1, true);
        
        // 변수 초기화
        int min = Integer.MAX_VALUE;
        int max = -1;
        boolean canGo = false;      // 한번이라도 이길 수 있어?
        
        field[ax][ay] = 0;          // 이제 이동해볼거야. 발판 삭제
        
        // 사방탐색
        for (int i = 0; i < 4; i++) {
            int nx = ax + dr[i];
            int ny = ay + dc[i];
            
            // 이동하려는 곳이 범위 안에 있고, 발판이 있으면
            if (nx >= 0 && nx < boardX && ny >= 0 && ny < boardY
               && field[nx][ny] == 1) {
                
                // result에는 내가 해당 위치로 이동하는 경우에 
                // 상대가 이기는지 여부와 이동횟수가 담겨있음
                Result result = move(bx, by, nx, ny);
                
                // 상대가 지는 경우라면 내가 이기니까 최소이동횟수로 갱신
                // 나는 이길 수 있는 플레이어가 됨.
                if (!result.canWin) {
                    min = Math.min(min, result.moveCnt);
                    canGo = true;

                // 상대가 이기는 플레이어라면 나는 지니까 최대이동횟수로 갱신
                } else {
                    max = Math.max(max, result.moveCnt);
                }
                
            } 
        }
        
        field[ax][ay] = 1;      // 4개 다 확인했어 발판 복구~
        
        // 지금 플레이어가 이길 수 있는 플레이어면
        if (canGo) return new Result(min+1, true);
        // 아니라면
        else return new Result(max+1, false);
    }
    
    public boolean isEnd(int x, int y) {

        for (int i = 0; i < 4; i++) {
            int nx = x + dr[i];
            int ny = y + dc[i];
            
            if (nx >= 0 && nx < boardX && ny >= 0 && ny < boardY 
               && field[nx][ny] == 1) return false;
        }
        return true;
    }
}

class Result {
    int moveCnt;
    boolean canWin;
    
    Result (int moveCnt, boolean canWin) {
        this.moveCnt = moveCnt;
        this.canWin = canWin;
    }
}