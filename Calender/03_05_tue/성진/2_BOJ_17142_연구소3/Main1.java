//package boj_17142_연구소3;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//
//	public static void main(String[] args) {
//		
//		Scanner sc = new Scanner(System.in);
//		
//		
//		int N = sc.nextInt();
//		
//		int M = sc.nextInt();
//		
//		
//		int[][] map = new int[N+1][N+1];
//		
//		
//		for (int i=0; i<map.length; i++) {
//			for (int j=0; j<map.length; j++) {
//				// 1로 패딩
//				if (i==0 || j==0 || i==N || j==N) {
//					map[i][j] = -1;
//				}
//				else{
//					int num = sc.nextInt();
//					if (num == 1) {
//						map[i][j] = -1;
//					}
//					else if (num == 2) {
//						map[i][j] = -2;
//					}
//					else {
//						map[i][j] = num;
//					}
//					
//				}
//			}
//		}
//		
//		
//		int sec = 0;
//		
//		
//		
//		
//		
//		
//	}
//	
//	
//	static void infectVirus(int sec, int r, int c, int[][] map) {
//		
//		int nr = r;
//		int nc = c;
//		
//		int[] dr = {1, -1, 0, 0};
//		int[] dc = {0, 0, 1, -1};
//		
//		for (int d=0; d<4; d++) {
//			int depth = 0;
//			while(map[nr+dr[d]][nc+dc[d]] != -1) {
//				depth++;
//				nr += dr[d];
//				nc += dc[d];
//				
//				if (map[nr][nc]==0) {
//					map[nr][nc] = depth;
//				}
//				else if (map[nr][nc] > depth) {
//					map[nr][nc] = depth;
//				}
//				else if (map[nr][nc] == -2) {
//					
//				}
//			}
//			
//		}
//		
//		
//	}
//	
//	
//	static void check
//	
//}
package boj_17142_연구소3;


