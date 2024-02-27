package BOJ._15683_감시;

import java.util.Scanner;

public class Main {
	static int answer = 0;
	public static void main(String[] args) {
		// 최대 4^64(8x8)?
		// 최대 4^8 = 65536 이래서 최대 cctv 개수를 줬구만?
		
		// dfs 너무 복잡해 포기!
		
		// 애초에 '연결'관계랄게 없는데 굳이 depth 넣어 가면서 트리로 구현하려 하는게 에바같음.
		// 근데 질문들보면 그렇게 푼사람들도 꽤 되는 듯.
		// 백트랙킹? 과 DFS 는 비슷하지만 다르다.
		// 근데 이문제가 백트랙킹(가지치기)가 되나?
		// 전부다 봐야 하지 않나.
		
		
		
//		Scanner sc = new Scanner(System.in);
//		
//		int N = sc.nextInt();
//		int M = sc.nextInt();
//		
//		int[][] arr = new int[N][M];
//		
//		
//		int[][][] nodes = new int[8][][];
//		// 1 이면 column이 4개
//		// 2 이면 column이 2개
//		// 3 이면 column이 4개
//		// 4 이면 column이 4개
//		// 5 이면 column이 1개
//		int numNodes = 0;
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				int num = sc.nextInt();
//				arr[i][j] = num;
//				if (num != 0 && num != 6) {
//					if (num == 1) {
//						int[][] node = new int[4][3]; // i, j, num
//						
//					}
//					numNodes++;
//					
//				}
//				
//			}
//		}
//		
//		//arr 복사를 안하려면
//		//boolean 배열을 대신 만들고 매번 초기화 해야 할듯?
//		
//		boolean[][] monitoring = new boolean[N][M];
//		boolean[][] visited = new boolean[numNodes][];
//		// 1 이면 column이 4개
//		// 2 이면 column이 2개
//		// 3 이면 column이 4개
//		// 4 이면 column이 4개
//		// 5 이면 column이 1개
//		
//		
//		
//		
//		sc.close();
//		
//	}
//	
//	public static int countZero(boolean[][] monitoring) {
//		int N = monitoring.length;
//		int M = monitoring[0].length;
//		
//		int cnt = 0;
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				if (!monitoring[i][j]) cnt++;
//			}
//		}
//		return cnt;
//	}
//	
//	public static void DFS(int[][] nodes, int depth, int r, boolean[][] visited, boolean[][] monitoring) {
//		if (depth == r+1) {
//			answer = Math.min(answer, countZero(monitoring));
//			return;
//		}
//		
//		int type = nodes[depth][2];
//		for (int i = 0; i < nodes[depth].length; i++) {
//			if (!visited[depth][i]) {
//				visited[depth][i] = true;
//				
//				monitoring = monitor()
//				
//				DFS(nodes,depth+1,r,visited,monitoring);
//				
//				visited[depth][i] = false;
//
//				
//			}
//			
//			
//		}
//		
	}
	

}
