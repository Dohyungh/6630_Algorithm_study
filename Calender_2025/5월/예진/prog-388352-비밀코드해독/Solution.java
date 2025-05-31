import java.util.*;
import java.lang.*;

class Solution {
    static Set<Integer> nums;
    static int answer;
    static int[][] ques;
    static int[] an;
    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        nums = new HashSet<>();
        ques = new int[q.length][];
        an = new int[q.length];
        for (int i = 0; i < q.length; i++) {
            ques[i] = Arrays.copyOf(q[i], q[i].length);
            an[i] = ans[i];
        }
        
        dfs(1, 0, n);
        return answer;
    }
    
    public static void dfs(int idx, int cnt, int n) {
        // 5개 다 고르면
        if (cnt >= 5) {
            if (check()) {
                answer++;
                // for (int i : nums) System.out.print(i + " ");
                // System.out.println();
            }
            return;
        }

        // 선택할 수 있는 수가 모자라면
        if (idx > n - (5 - 1 - cnt)) return;
        
        nums.add(idx);
        dfs(idx + 1, cnt + 1, n);
        nums.remove(idx);
        dfs(idx + 1, cnt, n);

    }
    
    public static boolean check() {
        
        for (int i = 0; i < ques.length; i++) {
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (nums.contains(ques[i][j])) cnt++;
            }
            if (cnt != an[i]) return false;
        }
        return true;
    }
}