/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 트리 순회_1991
 * Date: 2024.12.18
 */

#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

class Node {
    public :
        char c;
        int left;
        int right;

        Node() : c('-'), left(-1), right(-1) {}
        Node(char input) : c(input), left(-1), right(-1) {}
};

vector<Node> tree;

void preorder (Node& curr) {
    cout << curr.c;
    if (curr.left != -1) preorder(tree[curr.left]);
    if (curr.right != -1) preorder(tree[curr.right]);
}

void inorder (Node& curr) {
    if (curr.left != -1) inorder(tree[curr.left]);
    cout << curr.c;
    if (curr.right != -1) inorder(tree[curr.right]);
}

void postorder (Node& curr) {
    if (curr.left != -1) postorder(tree[curr.left]);
    if (curr.right != -1) postorder(tree[curr.right]);
    cout << curr.c;
}

int main() {

    int n;
    cin >> n;
    tree.resize(n);

    for (int i = 0; i < n; i++)
    {
        char c, left, right;
        cin >> c >> left >> right;

        int idx = c - 'A';
        tree[idx].c = c;
        if (left != '.') tree[idx].left = left - 'A';
        if (right != '.') tree[idx].right = right - 'A'; 
    }
    
    preorder(tree[0]);
    cout << "\n";
    inorder(tree[0]);
    cout << "\n";
    postorder(tree[0]);
    cout << "\n";

    return 0;
}