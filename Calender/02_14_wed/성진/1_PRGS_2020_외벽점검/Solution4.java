package prgs_외벽점검;

import java.util.Arrays;

class Solution4 {
	
	
	
	public static void main(String[] args) {
		
		int n = 12;
		int[] weak = {1, 5, 6, 10};
		int[] dist = {1, 2, 3, 4};
		
		solution(n, weak, dist);
	}
	
	
	
    public static int solution(int n, int[] weak, int[] dist) {
    	
    	
    	
    	
    	int weakLen = weak.length;
    	

		int[] weakExtend = new int[weak.length * 2];
    	
		// 확장 배열
		for (int i = 0; i < weakExtend.length; i++) {
			weakExtend[i] = weak[i % weak.length] + n * (i / weak.length);
		}

    	
		// answer 초기화
        int answer = dist.length + 1;
        
        
        
//        for (int start=0; start<weakLen; start++){
//        	
//        	
//        	
//        	
//        }
        
        int[] output = new int[dist.length];
        boolean[] visited = new boolean[dist.length]; 
        
        for (int i=0; i<dist.length; i++) {
        	output = permutation(dist, output, visited, 0, dist.length);
        	
        	System.out.println(Arrays.toString(output));
        }
        
        
        
        return answer;
    }
    
    
    
    
    
    
    
    public static int[] permutation(int arr[], int output[], boolean visited[], int depth, int r) {
    	
    	
    	if (depth == r) {
            for(int i = 0; i<r; i++) { // 출력을 r 까지만 함
                return output;
            }
        }
    	
    	
    	for(int i = 0; i<arr.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr,output, visited, depth+1,r);
                visited[i] = false;
            }
        }
		return output;
    	
    	
    	
    }
    
    
    
}