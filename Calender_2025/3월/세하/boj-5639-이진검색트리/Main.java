package boj_5639_이진검색트리;

import java.io.*;

public class Main {
	static class Node {
		int value;
		Node left, right;

		Node(int value) {
			this.value = value;
			this.left = this.right = null;
		}
	}

	static class BST {
		Node root;

		// 이진 검색 트리에 노드를 삽입하는 함수
		void insert(int value) {
			root = insertRec(root, value);
		}

		private Node insertRec(Node node, int value) {
			if (node == null) {
				return new Node(value);
			}
			if (value < node.value) {
				node.left = insertRec(node.left, value);
			} else {
				node.right = insertRec(node.right, value);
			}
			return node;
		}

		// 후위 순회
		void postorderTraversal(Node node) {
			if (node == null)
				return;
			postorderTraversal(node.left);
			postorderTraversal(node.right);
			System.out.println(node.value);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		BST tree = new BST();
		String input;

		// 입력을 받아 BST 구성
		while ((input = br.readLine()) != null && !input.isEmpty()) {
			tree.insert(Integer.parseInt(input));
		}

		// 후위 순회 결과 출력
		tree.postorderTraversal(tree.root);
	}
}
