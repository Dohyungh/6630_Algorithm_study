package PRGS._2020Kakao_기둥과보설치;

import java.util.Arrays;

public class Solution_fin {

	static boolean[][] ver = new boolean[102][102];
	
	static boolean[][] hor = new boolean[102][102];
	
    public static int[][] solution(int n, int[][] build_frame) {
        
        for (int[] build : build_frame) {
        	
        	int y = build[0];
        	int x = build[1];
        	int what = build[2]; //0 ver 1 hor
        	int order = build[3]; //0 del 1 const

        	if (what == 0 && order ==0) {
        		if(verDeleteCheck(x, y)) ver[x][y] = false;
        	}
        	if (what == 0 && order ==1) {
        		if(verConstCheck(x, y)) ver[x][y] = true;
        	}
        	
        	if (what == 1 && order ==0) {
        		if(horDeleteCheck(x, y)) hor[x][y] = false;
        		
        	}
        	if (what == 1 && order ==1) {
        		if(horConstCheck(x, y)) hor[x][y] = true;
        	}

        }
        
        
        
        int cnt = 0;
        for (int i = 0; i<ver.length; i++) {
        	for (int j = 0; j<ver.length; j++) {
        		if (ver[i][j]) cnt++;
        	}
        }
        for (int i = 0; i<hor.length; i++) {
        	for (int j = 0; j<hor.length; j++) {
        		if (hor[i][j]) cnt++;
        	}
        }
        
        
        int[][] answer = new int[cnt][3];
        int idx = 0;
        for (int i = 0; i<102; i++) {
        	for (int j = 0; j<102; j++) {
        		if (ver[i][j]) {
        			answer[idx][0] = j;
        			answer[idx][1] = i;
        			answer[idx][2] = 0;
        			idx++;
        		}
        		if (hor[i][j]) {
        			answer[idx][0] = j;
        			answer[idx][1] = i;
        			answer[idx][2] = 1;
        			idx++;
        		}
        	}
        }
         Arrays.sort(answer, (o1,o2)->{
         	if (o1[0]==o2[0] && o1[1] == o2[1]) {
         		return o1[2]-o2[2];
         	} else if (o1[0] == o2[0]) {
         		return o1[1]-o2[1];
         	} else {
         		return o1[0]-o2[0];
         	}
         });
        
        
        return answer;
    }
    
    public static boolean verConstCheck (int x, int y) {
    	boolean ok = false;
    	
    	if (  x==0 || ver[x-1][y] || hor[x][y] || (y>=1&&hor[x][y-1])) {
    		ok = true;
    	}
    	return ok;
    }
    public static boolean verDeleteCheck (int x, int y) {
    	boolean ok = true;
    	
    	ver[x][y] = false;

    	for (int i = 0; i < ver.length; i++) {
            for (int j = 0; j < ver.length; j++) {
                if (hor[i][j]) {
                    if(!horConstCheck(i,j)) ok = false;
                }
                if (ver[i][j]) {
                    if(!verConstCheck(i,j)) ok = false;
                }
            }
        }
    	if (!ok) ver[x][y] = true;
    	return ok;
    	
    }
    public static boolean horConstCheck (int x, int y) {
    	boolean ok = false;
    	
    	if (  (x>=1&&ver[x-1][y]) || (x>=1&&ver[x-1][y+1]) ) { 
    		ok = true;
    	}else if( (y>=1&&hor[x][y-1]) && hor[x][y+1]  ) ok = true;
    		

    	return ok;
    }
    public static boolean horDeleteCheck (int x, int y) {
    	boolean ok = true;
    	
    	hor[x][y] = false;

    	for (int i = 0; i < ver.length; i++) {
            for (int j = 0; j < ver.length; j++) {
                if (hor[i][j]) {
                    if(!horConstCheck(i,j)) ok = false;
                }
                if (ver[i][j]) {
                    if(!verConstCheck(i,j)) ok = false;
                }
            }
        }
    	if (!ok) hor[x][y] =true;
    	return ok;
    	
    }
}


