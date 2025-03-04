import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long lft = (long) times[0];
        long rgt = (long) times[0] * n;
        
        long answer = rgt;
        
        while (lft <= rgt) {
            long mid = (lft + rgt) / 2;        
            long people = 0;
            
            for (int i = 0; i < times.length; i++) {
                people += (long) mid / times[i];
            }
            
            if (people >= n) {
                rgt = mid - 1;
                answer = mid;
            } else {
                lft = mid + 1;
            }
        }
        
        return answer;
    }
}