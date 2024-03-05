package BOJ._17779_게리맨더링2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_fin {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		int[][] prefixSum = new int[N][N]; // padding 못생겨써
		
		// 생성
		prefixSum[0][0] = map[0][0];
		for (int i = 1; i <N; i++) {
			prefixSum[0][i] = prefixSum[0][i-1] + map[0][i];
			prefixSum[i][0] = prefixSum[i-1][0] + map[i][0];
			
		}
		
		for (int i = 1; i <N; i++) {
			for (int j = 1; j<N; j++) {
				prefixSum[i][j] = prefixSum[i][j-1] + prefixSum[i-1][j] - prefixSum[i-1][j-1] + map[i][j];
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for (int x = 0; x <N; x++) {
			for (int y = 0; y <N; y++) {
				for (int d1 = 1; d1 <=y; d1++) {
					for (int d2 = 1; d2 <= N-1-y; d2++) {
						if (d1+d2 <=N-1-x) {
	                        int one = getOne(x, y, d1, d2, map, prefixSum);
	                        int two = getTwo(x, y, d1, d2, map, prefixSum);
	                        int three = getThree(x, y, d1, d2, map, prefixSum);
	                        int four = getFour(x, y, d1, d2, map, prefixSum);
	                        int five = prefixSum[N-1][N-1] - one - two - three -four;
	
	                        int[] list = {one, two, three, four, five};
	                        //							System.out.println(Arrays.toString(list));
	                        Arrays.sort(list);
	                        //							System.out.println("x: "+x+" y: "+y+" d1: "+d1+" d2: "+d2);
	                        answer = Math.min(answer, list[4] - list[0]);
						}
					}
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	
	public static int getOne(int x, int y, int d1, int d2, int[][] map, int[][] prefixSum) {
		int sum = prefixSum[x+d1-1][y];
		
		for (int r = x, left = y; r<x+d1; r++,left--) {
			for (int c = left; c <= y; c++) {
				sum -= map[r][c];
			}
		}

		return sum;
	}
	
	public static int getTwo(int x, int y, int d1, int d2, int[][] map, int[][] prefixSum) {
		int N = map.length;
		int sum = prefixSum[x+d2][N-1] - prefixSum[x+d2][y];
//		System.out.println("sum: "+sum);
		
		for (int r = x+1, right = y+1; r<=x+d2; r++,right++) {
			for (int c = y+1; c <= right; c++) {
				sum -= map[r][c];
			}
		}
		
		return sum;
	}
	
	public static int getThree(int x, int y, int d1, int d2, int[][] map, int[][] prefixSum) {
		int N = map.length;
		int sum = prefixSum[N-1][y-d1+d2-1] - prefixSum[x+d1-1][y-d1+d2-1];
		for (int r = x+d1, left = y-d1; r<x+d1+d2; r++,left++) {			
			for (int c = left; c <= y-d1+d2-1; c++) {
				sum -= map[r][c];
			}
		}
		
		return sum;
	}
	
	public static int getFour(int x, int y, int d1, int d2, int[][] map, int[][] prefixSum) {
		int N = map.length;
		int sum = prefixSum[N-1][N-1] - prefixSum[x+d2][N-1] - prefixSum[N-1][y-d1+d2-1] + prefixSum[x+d2][y-d1+d2-1];
//		System.out.println("sum: "+sum);
		for (int r = x+d1+d2, right = y-d1+d2; r>=x+d2+1; r--,right++) {
			for (int c = y-d1+d2; c <= right; c++) {
				sum -= map[r][c];
			}
		}
		
		return sum;
	}
	

}
