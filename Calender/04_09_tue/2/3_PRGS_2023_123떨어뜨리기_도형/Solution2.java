package PRGS._2023Kakao_123떨어뜨리기;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;



public class Solution2 {
	static int answer;
	static int targetSum;
	static int[] answerArr;
	public static void main(String[] args) {
		
		int[][] edges = {{2, 4}, {1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}};
		int[] target = {0,0,0,3,0,0,5,1,2,3};
		int[][] edges1 = {{1, 2}, {1, 3}};
		int[] target1 = {0,7,3};
		int[][] edges2 = {{1, 3}, {1, 2}};
		int[] target2 = {0,7,1};
		
		System.out.println(Arrays.toString(solution(edges,target)));
		System.out.println(Arrays.toString(solution(edges1,target1)));
		System.out.println(Arrays.toString(solution(edges2,target2)));
		
	}
	
    public static int[] solution(int[][] edges, int[] target) {
    	answer = Integer.MAX_VALUE;
    	targetSum = 0;
    	answerArr = null;
    	for (int i = 0; i < target.length; i++) targetSum += target[i];
        
        Arrays.sort(edges, (int[] o1, int[] o2) -> {
        	if(o1[0] == o2[0]) return o1[1] - o2[1];
        	return o1[0] - o2[0];
        });
        
        int N = edges.length+1;
        
        Node[] nodes = new Node[N+1];
        for (int i = 1; i < N+1; i++) nodes[i] = new Node(i);
        for (int[] edge : edges) nodes[edge[0]].add(nodes[edge[1]]);
        int[] result = new int[N];
        int[] output = new int[targetSum];
        
        DP(1, result, nodes, 0, 0, target, output);

        DP(2, result, nodes, 0, 0, target, output);

        DP(3, result, nodes, 0, 0, target, output);
        
        
        return answerArr==null ? new int[] {-1} : answerArr;
    }
    
    
    
    
    private static void DP(int i, int[] oriResult, Node[] oriNodes, int numFallen, int sumFallen, int[] target, int[] oriOutput) {
    	
    	sumFallen += i;
    	numFallen += 1;
    	if (numFallen>=answer) return;
    	if (sumFallen > targetSum) return;
    	// 이쯤되면 복사는 그냥 무조건 시작할 때 하는게 맞아.
    	int[] result = Arrays.copyOf(oriResult, oriResult.length);
    	int[] output = Arrays.copyOf(oriOutput, oriOutput.length);
    	Node[] node = new Node[oriNodes.length];
    	for (int j = 1; j< node.length; j++) {
    		node[j] = oriNodes[j].copy();
    	}
    	output[numFallen] = i;
    	
    	Node nowNode = node[1];
    	
    	int id = 1;
    	while (nowNode != null) {
    		id = nowNode.idx;
    		nowNode = nowNode.nextNode(i);
    	}
    	result[id-1] += i;
    	if (result[id-1] > target[id-1]) return;
    	
    	
    	if (sumFallen == targetSum) {
    		for (int j = 0; j < target.length; j++) {
    			if (result[j] != target[j]) break;
    			if (j == target.length-1) {
    				answerArr = Arrays.copyOf(output, numFallen);
    				answer = numFallen;
    			}
    		}
    		return;
    	}
    	
    	DP(1, result, node, numFallen, sumFallen, target, output);
    	DP(2, result, node, numFallen, sumFallen, target, output);
    	DP(3, result, node, numFallen, sumFallen, target, output);
    	
	}




	static class Node {
    	int idx;
    	Queue<Node> childs;
    	int sum;
    	
    	Node(int idx) {
    		this.idx = idx;
    		this.childs = new LinkedList<Node>();
    		sum = 0;
    	}
    	Node(int idx, Queue<Node> childs, int sum) {
    		this.idx = idx;
    		this.childs = childs;
    		this.sum = sum;
    	}
    	
    	public void add(Node node) {
    		this.childs.add(node);
    	}
    	
    	public Node nextNode(int num) {
    		if (childs.size()==0) {
    			this.sum+=num;
    			return null;
    		}
    		Node nextNode = childs.poll();
    		childs.add(nextNode);
    		return nextNode;
    	}
    	
    	public Node copy() {
    		Queue<Node> newChilds = new LinkedList<Node>();
    		int sz = childs.size();
    		
    		for (int i = 0; i < sz; i++) {
    			Node node = childs.poll();
    			Node newNode = node.copy(); // 이 아름다운 재귀를 봐 정말 아름다워
    			newChilds.add(newNode);
    			childs.add(node);
    		}
    		
    		Node newNode = new Node(this.idx, newChilds, this.sum);
    		return newNode;
    	}
    }
    
    
}