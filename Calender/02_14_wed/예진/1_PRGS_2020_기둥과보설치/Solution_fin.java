package KAKAO_2020_기둥과보설치;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution_fin {
	
	static final int pillar = 4;
	static final int shelfLeft = 2;
	static final int shelfRight = 1;
	static int[][] wall;
	static List<int[]> built;

	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("src/KAKAO_2020_기둥과보설치/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int wallSize = sc.nextInt();
		int A = sc.nextInt();
		
		int[][] build_frame = new int[A][4];
		
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < 4; j++) {
				build_frame[i][j] = sc.nextInt();
			}
		}
		
		int[][] answer = solution(wallSize, build_frame);
		
		for (int i = 0; i < answer.length; i++) {
			System.out.print(Arrays.toString(answer[i]));
		}
		System.out.println();
		sc.close();

	}
		
	public static int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        wall = new int[n+3][n+3];
        built = new ArrayList<>();
        
        for (int[] order : build_frame) {
        	int x = order[0];
        	int y = order[1];
        	int thing = order[2];
        	int how = order[3];
        	
        	if (how == 1) {
				if (thing == 0) {
					if (pillarBuildable(x, y)) {
						wall[x][y+1] += pillar;
						int[] temp = {x, y, thing};
						built.add(temp);
					}
				} else if (thing == 1) {
					if (shelfBuildable(x, y)) {
						wall[x][y] += shelfLeft;
						wall[x+1][y] += shelfRight;
						int[] temp = {x, y, thing};
						built.add(temp);
					}
				}
			} else {
				if (remove(x, y, thing)) {
					int[] temp = {x, y, thing};
					built.removeIf(o -> Arrays.equals(o, temp));
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
        for (int i = 0; i < built.size(); i++) {
        	answer[i] = built.get(i);
        }
        
        return answer;
    }
	
	static boolean pillarBuildable(int x, int y) {
		return y == 0 || wall[x][y] > 0;
	}
	
	static boolean shelfBuildable(int x, int y) {
		return wall[x][y] + wall[x+1][y] >= 3;
	}
	
	static boolean remove(int x, int y, int thing) {
		boolean flag = true;
		
		if (thing == 0) {
			wall[x][y+1] -= pillar;
			int[] temp = {x, y, thing};
			built.removeIf(o -> Arrays.equals(o, temp));
		} else {
			wall[x][y] -= shelfLeft;
			wall[x+1][y] -= shelfRight;
			int[] temp = {x, y, thing};
			built.removeIf(o -> Arrays.equals(o, temp));
		}
		
		for (int[] struc : built) {
			int a = struc[0];
			int b = struc[1];
			if (struc[2] == 0) {
				wall[a][b+1] -= pillar;
				if (!pillarBuildable(a, b)) flag = false;
				wall[a][b+1] += pillar;
			} else {
				wall[a][b] -= shelfLeft;
				wall[a+1][b] -= shelfRight;
				if (!shelfBuildable(a, b)) flag = false;
				wall[a][b] += shelfLeft;
				wall[a+1][b] += shelfRight;
			}
		}
		
		if (!flag) {
			if (thing == 0) {
				wall[x][y+1] += pillar;
				int[] temp = {x, y, thing};
				built.add(temp);
			} else {
				wall[x][y] += shelfLeft;
				wall[x+1][y] += shelfRight;
				int[] temp = {x, y, thing};
				built.add(temp);
			}
		}
		
		return flag;
	}

}
