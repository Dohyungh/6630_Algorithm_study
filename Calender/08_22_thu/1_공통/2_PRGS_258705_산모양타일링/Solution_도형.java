package PRGS._2024Kakao_산모양타일링;

class Solution {
    
    public int solution(int n, int[] tops) {
        int[] dpA = new int[n+1];
        int[] dpB = new int[n+1];
        
        dpA[0] = 0;
        dpB[0] = 1;
        
        for (int i = 1; i < n+1; i++) {
            
            dpA[i] = dpA[i-1] + dpB[i-1];
            
            // tops가 0 혹은 1이니까 깔끔하게 안으로 넣어줌
            dpB[i] = (1+tops[i-1]) * (dpA[i-1] + dpB[i-1]) + dpB[i-1];
            
            dpA[i] %= 10007;
            dpB[i] %= 10007;
        }
        
        return (dpA[n] + dpB[n]) % 10007;
    }
    
    
}