package KAKAO_2020_기둥과보설치;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution2 {
	
	static boolean[][] pillar;
	static boolean[][] board;
	static int N;

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
        pillar = new boolean[n+1][n+1];
        board = new boolean[n+1][n+1];
        int cnt = 0;
        
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int thing = build_frame[i][2];
            int build = build_frame[i][3];

            if (build == 1) {
                if (thing == 0) {
                    if (pillarBuildable(x, y)) {
                    	pillar[x][y] = true;
                    	cnt++;
					}
                } else {
                	if (shelfBuildable(x, y)) {
                		board[x][y] = true;
                		cnt++;
					}
                }
            } else {
                if (remove(x,y,thing)) {
                	cnt--;
                } 
            }
        }

        answer = new int[cnt][3];
        int idx = 0;
        for (int i = 0; i <= N; i++) {
        	for (int j = 0; j <= N; j++) {
        		if (pillar[i][j]) {
					answer[idx][0] = i;
					answer[idx][1] = j;
					answer[idx][2] = 0;
					idx++;
				}
        		if (board[i][j]) {
        			answer[idx][0] = i;
        			answer[idx][1] = j;
					answer[idx][2] = 1;
					idx++;
        		}
        	}
        }
        return answer;
    }
	
	static boolean pillarBuildable(int x, int y) {
		if (y == 0) return true;
		if (pillar[x][y-1]) return true;
		if (x > 0 && board[x-1][y]) return true;
		if (board[x][y]) return true;
		return false;
	}
	
	static boolean shelfBuildable(int x, int y) {
		if (pillar[x][y-1] || pillar[x+1][y-1]) return true;
		if (x > 0 && board[x-1][y] && board[x+1][y]) return true;
		return false;
	}
	
	static boolean remove(int x, int y, int thing) {
		boolean flag = true;
		if (thing == 0) {
			pillar[x][y] = false;
		} else {
			board[x][y] = false;
		}
		
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				if(pillar[i][j]) {
					pillar[i][j] = false;
					if (!pillarBuildable(i,j)) flag = false;
					pillar[i][j] = true;
				}
				if(board[i][j]) {
					board[i][j] = false;
					if (!shelfBuildable(i,j)) flag = false;
					board[i][j] = true;
				}
			}
		}
		
		if (!flag) {
			if (thing == 0) {
				pillar[x][y] = true;
			} else {
				board[x][y] = true;
			}
		}
		return flag;
	}

}
