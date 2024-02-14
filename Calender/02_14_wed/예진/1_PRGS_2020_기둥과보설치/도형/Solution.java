package PRGS._2020Kakao_기둥과보설치;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		int n = 5;
		int[][] build_frame = {
				{0,0,0,1},
				{2,0,0,1},
				{4,0,0,1},
				{0,1,1,1},
				{1,1,1,1},
				{2,1,1,1},
				{3,1,1,1},
				{2,0,0,0},
				{1,1,1,0},
				{2,2,0,1},
				
		};
		int[][] answer = solution(n,build_frame);
		for (int i = 0; i< answer.length; i++) {
			System.out.println(Arrays.toString(answer[i]));
			
		}
	}

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
//        	System.out.println("ver check:");
//        	for (int i = 0; i<6; i++) {
//        		for (int j = 0; j< 6; j++) {
//        			System.out.print(ver[i][j]+" ");
//        			
//        		}
//        		System.out.println();
//        	}
//        	System.out.println();
//        	System.out.println("hor check:");
//        	for (int i = 0; i<6; i++) {
//        		for (int j = 0; j< 6; j++) {
//        			System.out.print(hor[i][j]+" ");
//        			
//        		}
//        		System.out.println();
//        	}
//        	System.out.println();
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
    	
    	

    	if (ver[x+1][y]) {
    		if(!verConstCheck(x+1, y)) ok = false; 
    	}
    	if (x>=1 && y>=1 && hor[x-1][y-1]) {
    		if(!horConstCheck(x-1, y-1)) ok = false; 
    	}
    	if (x>=1 && hor[x-1][y]) {
    		if(!horConstCheck(x-1, y)) ok = false; 
    	}
    	if (y>=1 && hor[x][y-1]) {
    		if(!horConstCheck(x, y-1)) ok = false; 
    	}
    	if (hor[x][y]) {
    		if(!horConstCheck(x, y)) ok = false; 
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
    	
    	if(x>=1&&ver[x-1][y]) {
    		if(!verConstCheck(x-1, y)) ok = false;
    	}
    	if(x>=1&&ver[x-1][y+1]) {
    		if(!verConstCheck(x-1, y+1)) ok = false;
    	}
    	if(ver[x][y]) {
    		if(!verConstCheck(x, y)) ok = false;
    	}
    	if(ver[x][y+1]) {
    		if(!verConstCheck(x, y+1)) ok = false;
    	}
    	if(y>=1&&hor[x][y-1]) {
    		if(!horConstCheck(x, y-1)) ok = false;
    	}
    	if(hor[x][y+1]) {
    		if(!horConstCheck(x, y+1)) ok = false;
    	}
    	
    	if (!ok) hor[x][y] =true;
    	return ok;
    	
    }
}
