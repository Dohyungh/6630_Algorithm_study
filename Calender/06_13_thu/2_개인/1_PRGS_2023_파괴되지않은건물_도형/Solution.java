package PRGS._2022Kakao_파괴되지않은건물;
// 처음 누적합을 접했던 그 문제
// 2차원 누적합의 또 다른 응용을 제시하는 갓 문제
// 아래 링크는 카카오 풀이
// https://tech.kakao.com/posts/488
class Solution {
    public int solution(int[][] board, int[][] skills) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;

        // 원본배열의 pre 누적합 배열을 생성
        int[][] protoArr = new int[N+1][M+1];
        protoArr[0][0] = board[0][0];
        for (int i = 0 ; i <M-1; i++) {
            protoArr[0][i+1] = board[0][i+1] - board[0][i];
        }
        for (int i = 0; i < N-1; i++) {
            protoArr[i+1][0] = board[i+1][0] - board[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j< M; j++) {
                protoArr[i][j] = board[i][j] - board[i-1][j] - board[i][j-1] + board[i-1][j-1];
            }
        }

        // Pre 누적합 배열에 Skill들을 적용
        for (int[] skill : skills) {
            int type = 2*skill[0]-3;
            int r1 = skill[1];
            int c1 = skill[2];
            int r2 = skill[3];
            int c2 = skill[4];
            int degree = type * skill[5];

            protoArr[r1][c1] += degree;
            protoArr[r2+1][c1] -= degree;
            protoArr[r1][c2+1] -= degree;
            protoArr[r2+1][c2+1] += degree;
        }

        // Pre 누적합 배열을 다시 누적합해서 Skill 적용 후의 원본 배열 생성
        int[][] oriArr = new int[N][M];

        oriArr[0][0] = protoArr[0][0];
        for (int i = 1; i < M; i++) {
            oriArr[0][i] = protoArr[0][i] + oriArr[0][i-1];
        }
        for (int i = 1; i < N; i++) {
            oriArr[i][0] = protoArr[i][0] + oriArr[i-1][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                oriArr[i][j] = oriArr[i-1][j] + oriArr[i][j-1] - oriArr[i-1][j-1] + protoArr[i][j];
            }
        }

        // 1보다 큰 것 Count
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (oriArr[i][j] >=1) answer++;
            }
        }


        return answer;
    }
}