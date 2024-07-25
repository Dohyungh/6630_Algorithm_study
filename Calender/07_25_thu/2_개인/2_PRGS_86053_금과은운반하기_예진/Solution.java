/*
 * [참고풀이]
 * 이분탐색을 통해 특정 시간동안 필요한만큼의 자원을 모두 이동시킬 수 있는지 판단해가며 해를 구한다.
 * 시간의 최대값은 20억(a+b의 최대값) * 20만(운반시간*왕복2회) = 400조 (그렇다고 한다)
 */
 
class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        
        long left = 0;
        long right = 400_000_000_000_000L;
        
        // 여기 오류나서 고치다가 나왔는데 왜 이렇게 하는건지 모르겠음
        // 왜 반환이 right?
        while (left+1 < right) {
            
            long mid = (left + right) / 2L;
            
            if (isPossible(mid, a, b, g, s, w, t)) {
                right = mid;
            } else left = mid;
        }
        
        return right;
    }
    
    public static boolean isPossible(long time, int a, int b, 
                              int[] g, int[] s, int[] w, int[] t) {
        
        // 해당 시간에 도시에 도착할 수 있는 금, 은, 종합의 양을 구할거임
        long gold = 0L;
        long silver = 0L;
        long together = 0L;
        
        // 모든 도시를 돌면서
        for (int i = 0; i < g.length; i++) {
        
		        // 해당 시간까지 운반 가능한 횟수
            long count = time / (t[i] * 2L);
            if (time % (t[i] * 2L) >= t[i]) count++;
            
            // 해당 시간까지 운반 가능한 양
            long weight = Math.min(g[i] + s[i], w[i] * count);
            
            // 해당 시간까지 금, 은, 종합의 합을 각각 구한다. 
            // 운반가능한 양과, 실제 존재하는 양 중 더 작은 값을 선택
            gold += Math.min(g[i], weight);
            silver += Math.min(s[i], weight);
            together += weight;
        }
        
        // 모든 자원을 운반할 수 있으면 가능
        if (gold >= a && silver >= b && together >= a + b) return true;
        
        return false;
    }
}