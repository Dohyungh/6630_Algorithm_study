/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 트리 순회_1991
 * Date: 2024.12.16
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static Node[] tree;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
        tree = new Node[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char c = st.nextToken().charAt(0);
            int idx = c - 'A';
            tree[idx] = new Node(c);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (left != '.') tree[idx].left = left - 'A';
            else tree[idx].left = -1;

            if (right != '.') tree[idx].right = right - 'A';
            else tree[idx].right = -1;
        }

        preorder(tree[0]);
        bw.write("\n");
        inorder(tree[0]);
        bw.write("\n");
        postorder(tree[0]);
		
		bw.flush();
		bw.close();
		br.close();
	}

    public static void preorder (Node curr) throws IOException {

        bw.write(curr.c);
        if (curr.left != -1) preorder(tree[curr.left]);
        if (curr.right != -1) preorder(tree[curr.right]);
    }

    public static void inorder (Node curr) throws IOException {

        if (curr.left != -1) inorder(tree[curr.left]);
        bw.write(curr.c);
        if (curr.right != -1) inorder(tree[curr.right]);
    }

    public static void postorder (Node curr) throws IOException {

        if (curr.left != -1) postorder(tree[curr.left]);
        if (curr.right != -1) postorder(tree[curr.right]);
        bw.write(curr.c);
    }
}

class Node {
    char c;
    int left;
    int right;

    Node() {}
    Node (char c) {
        this.c = c;
    }
}