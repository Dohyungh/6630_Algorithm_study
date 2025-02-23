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
        int idx = 10;
        while (idx > 0 && answer[idx] == lion[idx]) idx--;
        return answer[idx] < lion[idx];
    }
    
    public void dfs(int lSum, int aSum, int cnt, int target, int n, int[] info) {
        int idx = 10-target;

        int gap = lSum - (aSum + full[idx]);
        if (target == 0 && (gap >= max)) {
            if (gap == max && !check()) return;
            max = gap;
            if (max == 0) return;
            lion[idx] = n - cnt;
            answer = Arrays.copyOf(lion, 11);

            return;
        }
        
        if (target == 0) return;
        
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
    }
}