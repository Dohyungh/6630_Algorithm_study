
import java.util.*;

class Solution {

    static int[] p;

    public int solution(int n, int[][] computers) {
        p = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = i;
        }

        for (int r = 0; r < n; r++) {
            for (int c = r + 1; c < n; c++) {
                if (computers[r][c] == 1) {
                    union(r, c);
                }
            }
        }

        Set<Integer> set = new HashSet<>();

        // 대표의 개수 (네트워크의 개수 세기)
        for (int i = 0; i < n; i++) {
            set.add(findset(i));
        }

        int answer = set.size();

        return answer;
    }

    public void union(int a, int b) {
        int p_a = findset(a);
        int p_b = findset(b);

        if (p_a != p_b) {
            p[p_b] = p_a;
        }
    }

    public int findset(int a) {
        if (p[a] != a) {
            p[a] = findset(p[a]);
        }
        return p[a];
    }
}
