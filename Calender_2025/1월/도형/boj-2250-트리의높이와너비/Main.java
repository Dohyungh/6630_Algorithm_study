package BOJ._2250_트리의높이와너비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * ...네? 중위순회요..? 그게 머죠..?
 * 
 */

public class Main {
	
	static class Node {
		Node p;
		Node left;
		Node right;
		int childCnt;
		int depth;
		int column;
		
		Node() {
			this.p = null;
			this.left= null;
			this.right = null;
			this.childCnt = 0;
			this.depth = 0;
			this.column = 0;
		}
		
		Node (Node p, Node left, Node right, int childCnt, int depth, int column) {
			this.p = p;
			this.left = left;
			this.right = right;
			this.childCnt = childCnt;
			this.depth = depth;
			this.column = column;
		}
	}
	static Node[] tree;
	
		public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		tree = new Node[N+1];
		
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int p = Integer.parseInt(st.nextToken());
			
			int left = Integer.parseInt(st.nextToken());
			
			int right = Integer.parseInt(st.nextToken());
			
			if (tree[p] == null) tree[p] = new Node();
			if (left != -1) {
				
				if (tree[left] == null) {
					tree[left] = new Node(tree[p], null, null, 0, 0, 0);					
				}
				tree[left].p = tree[p];
				tree[p].left = tree[left];
				
			}
			
			if (right != -1) {
				
				if (tree[right] == null) {
					tree[right] = new Node(tree[p], null, null, 0, 0, 0);					
				}
				tree[right].p = tree[p];
				tree[p].right = tree[right];
				
			}
			
		}
		
		Node root = null;
		
		for (int i = 1; i <= N; i++) {
			Node curr = tree[i];
			
			while (curr.p != null) {
				curr.p.childCnt++;
				curr = curr.p;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (tree[i].childCnt == N-1) root = tree[i];
		}
		
		root.depth = 1;
		getDepth(root);
		
		// 나의 왼자식
		// 모든 왼 부모의 왼자식의 개수 + 1(부모) 를 합한 `것
		
		for (int i = 1; i <=N; i++) {
			Node curr = tree[i];
			int myLeftChild = curr.left==null? 0: curr.left.childCnt+1;
			int allLeftParents = 0;
			while (curr.p != null) {
				if (curr.p.right != null && curr.p.right == curr) {
					allLeftParents += curr.p.left == null? 1 : curr.p.left.childCnt + 2;
				}
				curr = curr.p;
			}
			tree[i].column = myLeftChild + allLeftParents + 1;
		}
		
		int[][] arr = new int[N+1][2];
		
		for (int i = 1; i <= N; i++) {
			arr[i][0] = Integer.MAX_VALUE;
			arr[i][1] = Integer.MIN_VALUE;
		}
		
		for (int i = 1; i <=N; i++) {
			Node curr = tree[i];
			int currDepth = curr.depth;
			
			arr[currDepth][0] = Math.min(arr[currDepth][0], curr.column);
			arr[currDepth][1] = Math.max(arr[currDepth][1], curr.column);
		}
		
		int answerIdx = 1;
		int answer = 1;
		
		for (int i = 1; i <= N; i++) {
			if (arr[i][0] == Integer.MAX_VALUE && arr[i][1] == Integer.MIN_VALUE) break;
			
			if (arr[i][1] - arr[i][0] +1 > answer) {
				answerIdx = i;
				answer = arr[i][1] - arr[i][0] +1;
			}
			
		}
		System.out.println(answerIdx + " " + answer);
	}
		
	static void getDepth(Node curr) {
		if (curr.left != null) {
			curr.left.depth = curr.depth+1;
			getDepth(curr.left);
		}
		if (curr.right != null) {
			curr.right.depth = curr.depth+1;
			getDepth(curr.right);
		}
		
	}

}
