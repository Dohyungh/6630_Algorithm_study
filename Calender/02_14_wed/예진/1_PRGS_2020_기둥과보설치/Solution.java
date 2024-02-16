package KAKAO_2020_기둥과보설치;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
	
	static int[][] wall;
	static int wallSize;
	static final int pillar = 4;
	static final int shelfLeft = 2;
	static final int shelfRight = 1;

	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("src/KAKAO_2020_기둥과보설치/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		wallSize = sc.nextInt();
		int A = sc.nextInt();
		
		int[][] build_frame = new int[A][4];
		
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < 4; j++) {
				build_frame[i][j] = sc.nextInt();
			}
		}
		
		int[][] answer = solution(wallSize, build_frame);
		
		for (int i = 0; i < answer.length; i++) {
			System.out.println(Arrays.toString(answer[i]));
		}
		sc.close();

	}
		
		public static int[][] solution(int n, int[][] build_frame) {
	        int[][] answer = {};
	        wallSize = n+1;
	        wall = new int[wallSize][wallSize];
	        List<int[]> built = new ArrayList<>();
	        
	        for (int i = 0; i < build_frame.length; i++) {
	            int x = build_frame[i][0];
	            int y = build_frame[i][1];
	            int thing = build_frame[i][2];
	            int build = build_frame[i][3];

	            if (build == 1) {
	                if (thing == 0) {
	                    if (pillarBuildable(x,y)) {
	                        wall[x][y+1] += pillar;
	                        int[] temp = {x,y,thing};
	                        built.add(temp);
	                    }
	                } else {
	                    if (shelfBuildable(x,y)) {
	                        wall[x][y] += shelfLeft;
	                        wall[x+1][y] += shelfRight;
	                        int[] temp = {x,y,thing};
	                        built.add(temp);
	                    }
	                }
	            } else {
	                if (thing == 0) {
	                    if (pillarRemovable(x,y)) {
	                        wall[x][y+1] -= pillar;
	                        int[] temp = {x,y,thing};
	                        built.removeIf(a -> Arrays.equals(a,temp));
	                    }
	                } else {
	                    if (shelfRemovable(x,y)) {
	                        wall[x][y] -= shelfLeft;
	                        wall[x+1][y] -= shelfRight;
	                        int[] temp = {x,y,thing};
	                        built.removeIf(a -> Arrays.equals(a,temp));
	                    }
	                }
	            }
	        }

	        Collections.sort(built, (o1, o2) -> {
	            if (o1[0] == o2[0]) {
	                if (o1[1] == o2[1]) {
	                    return o1[2] - o2[2];
	                }
	                return o1[1] - o2[1];
	            }
	            return o1[0] - o2[0];
	        });

	        answer = new int[built.size()][3];
	        for (int i = 0; i < answer.length; i++) {
	            answer[i] = built.get(i);
	        }
	        
	        return answer;
	    }
	
	public static boolean pillarBuildable(int x, int y) {
		return y == 0 || wall[x][y] != 0;
	}
	
	public static boolean shelfBuildable(int x, int y) {
		return wall[x][y] + wall[x+1][y] >= 3;
	}
	
	public static boolean pillarRemovable(int x, int y) {
		if (x-1 >= 0 && x+1 <= wallSize) {
			if (wall[x-1][y+1] == 2 || wall[x+1][y+1] == 1) return false; 
			else if (wall[x-1][y+1] == 3 && wall[x][y+1] == 5) return false;
			else if (wall[x+1][y+1] == 3 && wall[x][y+1] == 6) return false;
		} else if (x == 0 && wall[x+1][y+1] == 1)return false;
		else if (x == wallSize && wall[x-1][y+1] == 2)return false;
		
		if (y+1 < wallSize) {
			if (wall[x][y+2] >= 4 && (wall[x][y+1] == 4)) return false;
		}
		return true;
	}
	
	public static boolean shelfRemovable(int x, int y) {
		if (x-1 >= 0 && x+2 <= wallSize) {
			if ((wall[x-1][y] < 4 && wall[x][y] < 4) || (wall[x+1][y] < 4 && wall[x+2][y] < 4)) return false;
		} else if (x == 0 && (wall[x+1][y] < 4 && wall[x+2][y] < 4)) return false;
		else if (x+1 == wallSize && (wall[x-1][y] < 4 && wall[x][y] < 4)) return false;
		
		if (wall[x][y+1] >= 4 && wall[x][y] == 2) return false;
		else if (wall[x+1][y+1] >= 4 && wall[x+1][y] == 1) return false;
		return true;
	}

}
