package 코테_boj_2178_미로탐색;

/**
 * Author: yngbao97, Yuk Yejin
 * Date: 2024.12.26
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        
        int[][] miro = new int[n][m];
        
        for (int i = 0; i < n; i++) {
        	char[] line = br.readLine().toCharArray();
        	for (int j = 0; j < m; j++) {
        		miro[i][j] = line[j] - '0';
        	}
        }
        
        boolean[][] visited = new boolean[n][m];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));
        visited[0][0] = true;
        int answer = 1;
        
        on: while(!queue.isEmpty()) {
        	
        	int size = queue.size();
        	
        	for (int i = 0; i < size; i++) {
        		
        		Node curr = queue.poll();
        		int row = curr.r;
        		int col = curr.c;
        		
        		if (row == n-1 && col == m-1) break on;
        		
        		for (int d = 0; d < 4; d++) {
        			int nr = row + dr[d];
        			int nc = col + dc[d];
        			
        			if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || miro[nr][nc] == 0) continue;
        			queue.offer(new Node(nr, nc));
        			visited[nr][nc] = true;
        		}
        	}
        	answer++;
        }

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}

class Node {
	int r;
	int c;
	
	Node() {}
	Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	@Override
	public String toString() {
		return r + ", " + c;
	}
}