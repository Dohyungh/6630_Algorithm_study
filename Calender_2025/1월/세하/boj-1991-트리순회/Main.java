package boj_1991_트리순회;

import java.util.*;
import java.io.*;

public class Main {
	static class Node {
		char left;
		char right;

		public Node(char left, char right) {
			this.left = left;
			this.right = right;
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static Map<Character, Node> tree;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int n = 0;

		tree = new HashMap<>();

		while (n++ < N) {
			String str = br.readLine().replaceAll(" ", "");

			tree.put(str.charAt(0), new Node(str.charAt(1), str.charAt(2)));
		}

		VLR('A');
		bw.write("\n");
		LVR('A');
		bw.write("\n");
		LRV('A');
		bw.write("\n");

		bw.flush();

	}

	public static void VLR(char vertex) throws IOException {
		bw.write(vertex);

		char left = tree.get(vertex).left;
		char right = tree.get(vertex).right;

		if (left != '.') {
			VLR(left);
		}

		if (right != '.') {
			VLR(right);
		}
	}

	public static void LVR(char vertex) throws IOException {
		char left = tree.get(vertex).left;
		char right = tree.get(vertex).right;

		if (left != '.') {
			LVR(left);
		}
		
		bw.write(vertex);

		if (right != '.') {
			LVR(right);
		}
	}

	public static void LRV(char vertex) throws IOException {
		char left = tree.get(vertex).left;
		char right = tree.get(vertex).right;

		if (left != '.') {
			LRV(left);
		}

		if (right != '.') {
			LRV(right);
		}
		
		bw.write(vertex);
	}
}
