package boj_14725_개미굴;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	static class TreeNode implements Comparable<TreeNode> {

		String feed;
//		TreeNode parent;
		List<TreeNode> cList;

		TreeNode() {
			this.cList = new ArrayList<TreeNode>();
		}

		TreeNode(String feed) {
			this.feed = feed;
			this.cList = new ArrayList<TreeNode>();
//			this.parent = null;
		}

		void addChild(TreeNode node) {
			this.cList.add(node);
		}

		@Override
		public int compareTo(TreeNode o) {
			return feed.compareTo(o.feed);
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		TreeNode root = new TreeNode();

		for (int i = 0; i < N; i++) {

			int K = sc.nextInt();

			TreeNode curr = new TreeNode();
			curr = root;

			for (int k = 0; k < K; k++) {

				String feed = sc.next();

				TreeNode newNode = new TreeNode(feed);

//				if (!curr.cList.contains(feed)) {
//					curr.addChild(newNode);
//				}

				boolean visited = false;

				for (int l = 0; l < curr.cList.size(); l++) {
					if (curr.cList.get(l).feed.equals(feed)) {
						visited = true;
						newNode = curr.cList.get(l);
						break;
					}
				}

				if (!visited) {
//					System.out.println("----");
//					System.out.println(curr.feed);
//					System.out.println(newNode.feed);
					curr.cList.add(newNode);
				}

				curr = newNode;

			}

		}

		printTree(root, 0);

//		for (int i=0; i<root.cList.size(); i++) {
//			System.out.println("----");
//			System.out.println(root.cList.get(i).feed);
//			TreeNode child = root.cList.get(i);
//			System.out.println(child.cList.size());
//			for (int j=0; j<child.cList.size(); j++) {
//				System.out.println("j=" + j);
//				System.out.println(child.cList.get(j).feed);
//			}
//		}

	}

	static void printTree(TreeNode node, int depth) {
		// 자식 노드를 사전순으로 정렬
		Collections.sort(node.cList);

		for (TreeNode child : node.cList) {
			for (int i = 0; i < depth; i++) {
				System.out.print("--");
			}
			System.out.println(child.feed);
			printTree(child, depth + 1);
		}
	}

}
