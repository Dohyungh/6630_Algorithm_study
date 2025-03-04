class Solution {
    public int solution(int[] players, int m, int k) {
        
        int answer = 0;
        int[] servers = new int[24];
        for (int i = 0; i < 24; i++) {
            int need = players[i] / m;
            if (need > servers[i]) {
                int more = need - servers[i];
                for (int j = i; j < i+k && j < 24; j++) servers[j] += more;
                answer += more;
            }
        }
        return answer;
    }
}