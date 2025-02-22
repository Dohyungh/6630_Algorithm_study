// 대실패,,,

import java.util.*;
import java.lang.*;

class Solution {
    
    static int[] full;
    static int max;
    static int[] answer;
    static int[] lion;
    public int[] solution(int n, int[] info) {
        init(info);
        
        answer = new int[11];
        max = 0;
        lion = new int[11];
        dfs(0, 0, 0, 10, n, info);
        
        if (max == 0) return new int[] {-1};
        return answer;
    }
    
    public boolean check() {
        for (int i = 10; i >= 0; i--) {
            if (answer[i] > lion[i]) return false;
        }
        return true;
    }
    
    public void dfs(int lSum, int aSum, int cnt, int target, int n, int[] info) {
        int idx = 10-target;
        System.out.println("-------target: " + target + ", idx: " + idx + ", cnt: " + cnt + ", lion: " + lSum + ", apeach: " + aSum);

        if (target == 0 && (lSum - (aSum + full[idx]) >= max)) {
            // if (!check()) return;
            max = lSum - (aSum + full[idx]);
            if (max == 0) return;
            lion[idx] = n - cnt;
            answer = Arrays.copyOf(lion, 11);
            System.out.println("max: " + max + ", " + Arrays.toString(answer));
            return;
        }
        
        if (target == 0) return;
        
        // if (cnt > 0 && full[10-lion[0]] - full[(10-lion[0]) + (n-cnt)])
        
        // int idx = 10 - target;
        if (info[idx] < n - cnt) {
            lion[idx] = info[idx] + 1;
            dfs(lSum + target, aSum, cnt + lion[idx], target - 1, n, info);
        }
        
        lion[idx] = 0;
        if (info[idx] == 0) dfs(lSum, aSum, cnt, target - 1, n, info);
        else dfs(lSum, aSum + target, cnt, target - 1, n, info);
    }
    
    public void init(int[] info) {
        full = new int[11];
        int idx = 9;
        
        for (int i = 9; i >= 0; i--) {
            if (info[i] > 0) full[i] = full[i+1] + (10-i);
            else full[i] = full[i+1];
        }
        System.out.println(Arrays.toString(full));
    }
}