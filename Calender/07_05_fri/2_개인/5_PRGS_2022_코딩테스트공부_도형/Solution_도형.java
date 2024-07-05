package PRGS._2022Kakao_코딩테스트공부;
/*
 * [문제 설명]
 * 알고력 과 코딩력 두가지 변수가 있음 (x, y 축 좌표평면으로 생각하면 쉬움)
 * 문제를 풀면 시간이 드는 대신에 알고력과 코딩력을 큰 폭으로 증가시킬 수 있음
 * 문제를 안풀고 1 시간을 들여서 알고력 or 코딩력을 1씩 증가시킬 수 있음
 * 
 * 모든 문제를 풀 수 있는 알고력/코딩력을 갖출 때까지의 최단시간을 구해라
 * 
 * [풀이 포인트]
 * 알고력과 코딩력을 각각 행/ 열로 하는 dp 테이블을 만들고 
 * 각 point 마다 문제를 풀지 (푼다면 어떤 문제를 풀지)
 * 1 시간 써서 행+1 or 열+1 할지를 재귀로 반복해서 모든 경우를 완탐함
 * 
 * 백 트래킹 : dp 테이블위의 동일한 점에 더 많은 시간을 써서 도착했다면 백트래킹
 *          : 내가 아직 풀 수 없는 문제면 제외
 * 
 * 
 */
class Solution {
    
	// 알고력과 코딩력의 상한으로 dp 테이블을 생성
    static int[][] dp = new int[151][151];
    
    public int solution(int alp, int cop, int[][] problems) {
    	// answer의 최솟값을 찾는 것이 목표기 때문에, 최댓값으로 초기화 해놓고 시작
        int answer = Integer.MAX_VALUE;
        
        // 모든 문제를 풀 수 있는 알고력/코딩력을 찾기 위해
        // 문제목록에 있는 문제들의 최대 알고력과 최대 코딩력을 찾는다.
        int max_alp = alp;
        int max_cop = cop;
        
        for (int[] problem : problems) {
            max_alp = Math.max(max_alp, problem[0]);
            max_cop = Math.max(max_cop, problem[1]);
        }
        
        // dp 테이블 초기화
        for (int i = 0; i < 151; i++) {
            for (int j = 0; j < 151; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        // 최초 시작 지점의 dp 값 0으로 초기화
        dp[alp][cop] = 0;

        // 모든 문제 목록을 들고 출발
        DP(alp, cop, problems);

        // 원하는 최대 알고/코딩력을 만족한 모든 dp 테이블 값 중에서 최솟값을 정답으로 반환
        // 그냥 dp[max_alp][max_cop] 하면 안되는 이유:
        // 문제를 풀다보면 정확하게 max_alp, max_cop에 맞춰지지가 않고 overshooting(?) 될 수 있어서
        for (int i = max_alp; i < 151; i++) {
            for (int j = max_cop; j < 151; j++) {
                answer = Math.min(answer, dp[i][j]);
            }
        }
        
        return answer;
    }
    
    public static void DP(int alp, int cop, int[][] problems) {
    	// 알고력과 코딩력이 ""모두"" 상한을 넘으면 그냥 리턴
        if (alp > 150 && cop > 150) return;
        
        // 문제를 안 풀고 코딩력을 1 올린다.
        // 그 후에 해당 지점에서 다시 DP 호출
        if (cop <= 149 && dp[alp][cop+1] > dp[alp][cop] + 1) {
            dp[alp][cop+1] = dp[alp][cop] +1;
            DP(alp, cop+1, problems);
        }
        
        // 문제를 안 풀고 코딩력을 1 올린다.
        // 그 후에 해당 지점에서 다시 DP 호출
        if (alp <= 149 && dp[alp+1][cop] > dp[alp][cop] + 1) {
            dp[alp+1][cop] = dp[alp][cop] +1;
            DP(alp+1, cop, problems);
        }
        
        // 문제 목록의 모든 문제들에 대해서
        // 현재 위치(alp, cop) 에서 문제를 풀 수 있으면 문제를 푸는 경우를 테스트.
        // DP 호출
        for (int[] problem : problems) {
            int alp_req = problem[0];
            int cop_req = problem[1];
            int alp_rwd = problem[2];
            int cop_rwd = problem[3];
            int cost = problem[4];
            
            // 이 두 줄이 일종의 예외(엣지)케이스인데,
            // 예를 들어, 알고력은 150이 넘었어도, 코딩력이 150이 안넘었다면, 계속 DP를 해나가야 함.
            int nextAlp = Math.min(150, alp+alp_rwd);
            int nextCop = Math.min(150, cop+cop_rwd);

            if (alp >= alp_req && cop >= cop_req && dp[nextAlp][nextCop] > dp[alp][cop] + cost) {
                dp[nextAlp][nextCop] = dp[alp][cop] + cost;
                DP(nextAlp, nextCop, problems);
            }
            
        }

        
        
    }
}