import java.util.*;

class Solution {
    
    static int[] apeach;    // 어피치가 맞힌 과녁 정보
    static int[] ryan;      // 라이언이 맞힐 과녁 정보
    static int[] answer;    // 정답 배열
    static int totalArrows; // 전체 화살 수
    static int max;         // 점수 최댓값 갱신 위한 max값
    
    public int[] solution(int n, int[] info) {
        
        // 초기화
        totalArrows = n;
        answer = new int[11];
        apeach = info;
        ryan = new int[11];
        
        // 어피치가 획득한 총 점수 계산
        int apeachScore = 0;
        for (int i = 0; i < 11; i++) {
            if (apeach[i] > 0) {
                apeachScore += 10 - i;
            }
        }

        // 초기 max 값을 어피치의 점수로 설정
        max = apeachScore;
        
        // DFS 탐색 시작
        dfs(ryan, 0, 0);
        
        // 어피치의 점수를 이길 수 없는 경우
        if (max == apeachScore) {
            int[] impossible = {-1};
            return impossible;
        }
        
        return answer;
    }
    
    static void dfs(int[] ryan, int idx, int arrows) {
        
        // 모든 과녁을 다 탐색하거나 화살을 다 쓴 경우에 if문 실행
        if (idx == 11 || arrows == totalArrows) {
            
            // 라이언의 점수 계산
            int score = 0;
            for (int i = 0; i < 11; i++) {
                if (ryan[i] == 1) {
                    if (apeach[i] == 0) {
                        score += 10 - i;
                    } else {
                        score += 2 * (10 - i);
                    }
                }
            }
            
            // 라이언의 점수가 현재 최대 점수와 같거나 큰 경우
            if (score >= max) {
                
                // 동일 점수인 경우 더 낮은 점수에서 화살을 더 많이 맞힌 배열 선택
                if (score == max) {
                    int idx1 = 0;
                    int idx2 = 0;
                    for (int i = 0; i < ryan.length; i++) {
                        if (ryan[i] > 0) {
                            idx1 = i;
                        }
                        if (answer[i] > 0) {
                            idx2 = i;
                        }
                    }
                    if (idx1 < idx2) {
                        return;
                    }
                }

                // 최대 점수 갱신, 정답 배열 업데이트
                max = score;
                
                for (int i = 0; i < answer.length; i++) {
                    if (ryan[i] == 1) {
                        answer[i] = apeach[i] + 1;
                    } else {
                        answer[i] = 0;
                    }
                }
                
                // 남은 화살이 있으면 0점 과녁에 추가
                if (totalArrows - arrows > 0) {
                    answer[10] = totalArrows - arrows;
                }
                
            }
            
            return;
        }

        // 새로운 배열로 복사
        int[] newArr = new int[11];
        for (int i = 0; i < ryan.length; i++) {
            newArr[i] = ryan[i];
        }
        
        // 현재 과녁을 맞히지 않고 다음 과녁으로 이동
        dfs(newArr, idx + 1, arrows);

        // 현재 과녁을 맞힌 경우
        newArr[idx] = 1;
        if (arrows + apeach[idx] + 1 <= totalArrows) {
            dfs(newArr, idx + 1, arrows + apeach[idx] + 1);
        }
        
    }
    
}
