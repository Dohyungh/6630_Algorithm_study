class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        int low = 1;
        int high = 100_000;
        int mid;
        int answer = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            
            if(isPossible(mid, diffs, times, limit)) {
                // System.out.print(mid + " ");
                answer = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return answer;
    }
    
    public static boolean isPossible(int level, int[] diffs, int[] times, long limit) {
        
        long sum = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) sum += times[i];
            else sum += ((times[i] + times[i-1]) * (diffs[i] - level)) + times[i];
            if (sum > limit) return false;
        }
        
        return true;
    }
}