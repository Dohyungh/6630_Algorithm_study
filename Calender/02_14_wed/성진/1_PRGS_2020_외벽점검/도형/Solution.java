package PRGS._2020Kakao_외벽점검;

import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		int n = 12;
		int[] weak = {0,1,5,6,10};
		int[] dist = {1,2,3,4};
		
		System.out.println(solution(n,weak,dist));
		
		int[] weak2 = {0,1,3,4,9,10};
		int[] dist2 = {3,5,7};
		System.out.println(solution(n,weak2,dist2));
		
	}
	
    public static int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        
        // 거리 매트릭스 // 시계방향 거리와 시계반대방향 거리를 모두 구하자
        //시계,반시계 // 시계,반시계 // ...
        int numWeaks = weak.length;
        
        int[][] CDM = new int[numWeaks][numWeaks];
        int[][] cCDM = new int[numWeaks][numWeaks];
        
        for (int i = 0; i < numWeaks; i++) {
        	for (int j = 0; j < numWeaks; j++) {
        		if (weak[j]-weak[i]<0) {
        			CDM[i][j] = n+weak[j]-weak[i];
        		} else {CDM[i][j] = weak[j]-weak[i];
        		}
        	}
        }
        
//        for (int i = 0; i<numWeaks; i++) {
//        	System.out.println(Arrays.toString(CDM[i]));
//        }
        for (int i = 0; i < numWeaks; i++) {
        	for (int j = 0; j < numWeaks; j++) {
        		if (weak[i]-weak[j]<0) {
        			cCDM[i][j] = n+weak[i]-weak[j];
        		} else {cCDM[i][j] = weak[i]-weak[j];
        		}
        	}
        }
//        
//        for (int i = 0; i<numWeaks; i++) {
//        	System.out.println(Arrays.toString(cCDM[i]));
//        }
        
        
        // 도달 가능 매트릭스
        
        int[][][] reachable = new int[numWeaks][numWeaks][dist.length*2]; // 각 weak(i) 에서 dist(j)를 이용해 갈 수 있는 weak(k) 갈 수 있으면 1, 없으면 0.
        for (int i = 0; i < numWeaks; i++) {
        	for (int j = 0; j < 2 * dist.length; j++) {
        		for (int k = 0; k < numWeaks; k++) {
        			if (CDM[i][k] <= dist[j]) reachable[i][j][k] = 1;
        		}

        	}
        }
        
        int[] allreach = new int[numWeaks];
        
        int workers = 0;
        while(true) {
        	workers++;
        	
        	
        }
        
        
        
        // 탐색
        
//        return answer;
    }
}