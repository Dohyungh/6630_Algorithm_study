package boj_16928_뱀과사다리게임;

import java.util.*;

public class Main {
    static class Pair {
        int position;
        int rolls;

        Pair(int position, int rolls) {
            this.position = position;
            this.rolls = rolls;
        }
    }

    public static int BFS(List<Integer>[] board, int[] ladders, int[] snakes) {
        int n = 100;
        boolean[] visited = new boolean[n + 1];
        Queue<Pair> queue = new LinkedList<>();
        
        queue.offer(new Pair(1, 0)); // 시작점 추가
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int currentPosition = current.position;
            int currentRolls = current.rolls;
            
            // 주사위 눈금 1부터 6까지 이동 가능한 경우 탐색
            for (int dice = 1; dice <= 6; dice++) {
                int nextPosition = currentPosition + dice;
                
                if (nextPosition <= n) {
                    // 사다리나 뱀을 타서 이동한 경우 처리
                    if (ladders[nextPosition] != 0 || snakes[nextPosition] != 0) {
                        nextPosition = board[nextPosition].get(0); // 사다리나 뱀이 여러 개인 경우는 없으므로 첫 번째 원소만 처리
                    }
                    
                    if (!visited[nextPosition]) {
                        visited[nextPosition] = true;
                        
                        if (nextPosition == n) {
                            return currentRolls + 1;
                        }
                        
                        queue.offer(new Pair(nextPosition, currentRolls + 1));
                    }
                }
            }
        }
        
        return -1; // 에러 케이스
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt(); // 사다리의 수
        int M = scanner.nextInt(); // 뱀의 수
        
        @SuppressWarnings("unchecked")
        List<Integer>[] board = new ArrayList[101];
        
        // 보드판 초기화
        for (int i = 1; i <= 100; i++) {
            board[i] = new ArrayList<>();
        }
        
        // 사다리 정보 입력
        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            board[x].add(y);
        }
        
        // 뱀 정보 입력
        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            board[u].add(v);
        }
        
        // 사다리와 뱀 정보를 보드에 반영
        int[] ladders = new int[101];
        int[] snakes = new int[101];
        
        for (int i = 1; i <= 100; i++) {
            if (board[i].size() > 0) {
                int destination = board[i].get(0); // 사다리나 뱀이 여러 개인 경우는 없으므로 첫 번째 원소만 처리
                if (i < destination) { // 사다리
                    ladders[i] = destination;
                } else { // 뱀
                    snakes[i] = destination;
                }
            }
        }
        
        // 최소 주사위 굴린 횟수 출력
        int result = BFS(board, ladders, snakes);
        System.out.println(result);
        
        scanner.close();
    }
}

