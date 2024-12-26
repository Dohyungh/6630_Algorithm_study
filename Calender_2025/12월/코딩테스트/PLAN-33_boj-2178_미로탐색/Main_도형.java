package BOJ._2178_미로탐색;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Node {
		int row;
		int col;
		
		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		boolean isEnd(int N, int M) {
			if (this.row == N-1 && this.col == M-1) return true;
			return false;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] arr = new int[N][M];
		
		boolean[][] visited = new boolean[N][M];
		
		sc.nextLine();
		
		for (int i = 0; i < N; i++) {
			String[] temp = sc.nextLine().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		Queue<Node> q = new LinkedList<Node>();
		
		q.add(new Node(0,0));
		
		int[] dr = new int[] {-1,1,0,0};
		int[] dc = new int[] {0,0,-1,1};
		
		int dist = 1;
		while(!q.isEmpty()) {
			
			int sz = q.size();
			
			
			for (int n = 0; n < sz; n++) {
				Node curr = q.poll();
				visited[curr.row][curr.col]= true; 
				
				if (curr.isEnd(N, M)) {
					System.out.println(dist);
					return;
				}
				
				for (int d = 0; d < 4; d++) {
					int nRow = curr.row + dr[d];
					int nCol = curr.col + dc[d];
					if (nRow >= 0 && nRow < N && nCol >= 0 && nCol < M && arr[nRow][nCol] == 1 && !visited[nRow][nCol]) {
						q.add(new Node(nRow, nCol));
						visited[nRow][nCol] = true;
					}
					
				}
				
			}
			
			dist++;
		}
		
		sc.close();
		
	}

}
