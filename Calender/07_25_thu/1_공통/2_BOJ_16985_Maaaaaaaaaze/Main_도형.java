package BOJ._16985_Maaaaaaaaaaaze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int[][] d = {{1,0,0},{0,1,0},{0,0,1},{0,-1,0},{0,0,-1},{-1,0,0}};
	static boolean[][][] map;
	static int answer = Integer.MAX_VALUE;
	static List<int[]> permutList = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		map = new boolean[5][5][5];
		
		for (int i = 0; i<5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					map[i][j][k] = sc.nextInt() == 1 ? true : false;
				}
			}
		}
		// 여기까지 입력
		
		// 순열 경우의 수 (판의 순서) 를 모두 구해놓음 5!
		int[] output = new int[5];
		boolean[] visited = new boolean[5];
		getPermut(0, output, visited);
		
		// 회전 부분집합 4 ^ 5
		int[] aCase = new int[5];
		getCases(0,aCase);
		
		System.out.println(answer == Integer.MAX_VALUE? -1 : answer);
		sc.close();
	}
	
	// 회전판 5개를 배치하는 순서를 미리 구해놓음
	// [1,2,3,4,5] 부터 [5,4,3,2,1] 까지
	private static void getPermut(int depth, int[] output, boolean[] visited) {
		if (depth == 5) {
			permutList.add(Arrays.copyOf(output, output.length));
			return;
		}
		for (int i = 0; i < 5; i++) {
			if (!visited[i]) {
				
				output[depth] = i;
				
				visited[i] = true;
				getPermut(depth+1, output, visited);
				visited[i] =false;
			}
		}
		
	}
	
	// 회전의 경우의 수 (부분집합) 를 구하고,
	// 구한 경우에 대해 BFS 돌리는 함수
	private static void getCases(int depth, int[] aCase) {
		if (depth == 5) {
			BFS(new int[] {0,0,0},new int[] {4,4,4}, aCase);
			BFS(new int[] {0,0,4},new int[] {4,4,0}, aCase);
			BFS(new int[] {0,4,0},new int[] {4,0,4}, aCase);
			BFS(new int[] {0,4,4},new int[] {4,0,0}, aCase);
			
			return;
		}
		for (int i = 0; i <4; i++) {
			aCase[depth] = i;
			getCases(depth+1, aCase);
		}
	}
	
	// BFS
	// 3차원 BFS 를 위에서 선언한 델타배열을 이용해 돈다.
	// 순열이 있는 줄 뒤늦게 알아서 어쩔 수 없이 미리 생성해 놓은 순열들을 모두 테스트 해본다.
	private static void BFS(int[] start, int[] end, int[] aCase) {
		
		boolean[][][] rotateMap = getRotateMap(aCase);
		for (int[] permut : permutList) {
			boolean[][][] permutMap = new boolean[5][][];
			for (int i = 0; i < 5; i++) {
				permutMap[i] = rotateMap[permut[i]];
				
			}
			// 여기까지 회전된 map을 다시 순열모음 리스트의 각 경우대로 재배치
			
			
			// 여기서부터는 그냥 우리가 아는 depth 를 저장하는 BFS
			Queue<int[]> queue = new LinkedList<int[]>();
			if (permutMap[start[0]][start[1]][start[2]]) {
				queue.add(start);
			}
			int depth = 0;
			int sz = 1;
			int cnt = 0;
			boolean[][][] visited = new boolean[5][5][5];
			
			while(!queue.isEmpty()) {
				int[] nowNode = queue.poll();
				visited[nowNode[0]][nowNode[1]][nowNode[2]] = true;
				cnt++;
				
				next:
				for (int i = 0; i <6; i++) {
					int[] nextNode = new int[3];
					for (int j = 0; j<3; j++) {
						nextNode[j] = nowNode[j] + d[i][j];
						if (nextNode[j] >=5 || nextNode[j] <0) continue next;
					}
					
					if (permutMap[nextNode[0]][nextNode[1]][nextNode[2]] && !visited[nextNode[0]][nextNode[1]][nextNode[2]]) {
						queue.add(nextNode);
						visited[nextNode[0]][nextNode[1]][nextNode[2]] = true;
						if (nextNode[0] == end[0] && nextNode[1] == end[1] && nextNode[2] == end[2]) {
							answer = Math.min(answer, depth+1);
							return;
						}
					}
				}
				if(cnt == sz) {
					depth++;
					sz = queue.size();
					cnt = 0;
					
					// 백트래킹
					if (depth +1 >= answer) return;
					
				}
			}
		}
	}
	
	// 모든 층에 대해서 aCase에 들어있는 각 층의 회전횟수만큼 회전시켜서 새로운 3차원 배열을 리턴해줌
	private static boolean[][][] getRotateMap(int[] aCase) {
		boolean[][][] newMap = new boolean[5][5][5];
		
		for (int i = 0; i < 5; i++) {
			newMap[i] = map[i];
			for (int rotate = 0; rotate < aCase[i]; rotate++) {
				newMap[i] = rotate(newMap[i]);
			}
			
		}
		
		return newMap;
	}
	
	// 1층에 대해서 1회 시계방향 회전
	private static boolean[][] rotate(boolean[][] map) {
		boolean[][] newMap = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				newMap[i][j] = map[4-j][i];
			}
		}
		return newMap;
	}

}
