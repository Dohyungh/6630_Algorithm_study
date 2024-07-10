/*
 * 힌트를 보고도 통과까지 이틀 걸림ㅠ
 * 
 * [풀이방법]
 * 1. 시간별 각 온도(희망온도)의 최소전력을 구할 수 있도록 dp테이블 생성
 * 2. 각 온도와 실외온도 차이에 따라 온도변화별 전력소비가 달라짐
 * 		- 목표온도 == 희망온도 이면 모든 방향 전력 0
 * 		- 목표온도 > 희망온도 이면 온도상승(0), 온도하강(a), 온도유지(b)
 * 		- 목표온도 < 희망온도 이면 온도상승(a), 온도하강(0), 온도유지(b)
 * 3. dp테이블을 채우는데 해당 칸의 온도를 희망온도라고 가정하고, 이전 시간에서의 온도 상승/하강/유지 여부로 최소비용 판단
 * 		- 단, 해당 시간에 그 온도에 도달할 수 있는지 여부를 판단하면서 가야함(나는 -1처리했다)
 * 4. 종료시간에 모든 온도를 순회하며 가장 적은 비용을 찾아낸다.
 * 		- 종료시간에 승객이 없다면 목표온도 범위 안에서 끝날 필요가 없으므로, 모든 온도를 확인해야 한다. 
 * 			(목표온도만 확인해서 계속 틀렸음ㅠ 인덱스 오류인줄 알고 애꿎은 곳에 디버깅하고 더 틀리고,,
 */

package prog_hyundai_에어컨;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = Integer.MAX_VALUE;

        // 행 : 주어진 시간만큼, 열 : 가능한 온도범위
        int[][] dp = new int[onboard.length][51];
        
        // 실외온도와 실내온도 차이에 따라 온도방향별 전력사용이 달라져서 배열화
        int[][] cost = {{0, 0, 0}, {0, b, a},{a, b, 0}};
        
        // dp테이블 0분째 실외온도를 제외한 모든 온도 불가능 표시, -1이면 불가능
        for (int i = 0; i <= 50; i++){
            if (i != temperature + 10) dp[0][i] = -1;
        }
        
        // 모든 시간을 순회하며
        for (int i = 1; i < onboard.length; i++) {
        	
        	// 각 온도에 도달하기까지의 최소전력 계산
            for (int j = 0; j <= 50; j++){

                // 손님이 있는데, 절대 도달할 수 없는 온도라면 불가능 처리
                if (onboard[i] == 1 && (j < t1 + 10 || j > t2 + 10)) {
                    dp[i][j] = -1;
                    continue;
                }
                
                int min = Integer.MAX_VALUE;	// 해당 온도에 최종적으로 저장할 최소전력 변수
                int k;							// cost 배열 인덱스를 결정 하기 위한 변수
                
                // 실외온도와 현재온도 비교에 따라 cost 배열 인덱스 결정
                if (temperature + 10 == j) k = 0;
                else if (temperature + 10 > j) k = 1;
                else k = 2;
                
                // 온도가 올라갔을 때
                if (j > 0 && dp[i-1][j-1] != -1) min = Math.min(min, dp[i-1][j-1] + cost[k][0]);

                // 온도가 유지되었을 때
                if (dp[i-1][j] != -1) min = Math.min(min, dp[i-1][j] + cost[k][1]);

                // 온도가 내려갔을 때
                if (j < 50 && dp[i-1][j+1] != -1) min = Math.min(min, dp[i-1][j+1] + cost[k][2]);
                
                // 세가지 경우의 수를 확인했는데 여전히 MAX_VALUE이면 불가능한 값
                if (min == Integer.MAX_VALUE) dp[i][j] = -1;
                
                // 최소전력이 나왔다면 저장
                else dp[i][j] = min;
            }
        }
        
        // 마지막 시간에 저장된 모든 온도의 최소전력을 순회하며 최소값 찾기
        for (int i = 0; i <= 50; i++) {
            if (dp[onboard.length-1][i] != -1) {
                answer = Math.min(answer, dp[onboard.length-1][i]);
            }
        }
        
        return answer;
    }
}