package PRGS._2023Kakao_123떨어뜨리기;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import PRGS._2023Kakao_123떨어뜨리기.Solution2.Node;



public class Solution {
	static int answer;
	static int targetSum;
	static int[] answerArr;
	
	static int numFallen;
	public static void main(String[] args) {
		
		int[][] edges = {{2, 4}, {1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}};
		int[] target = {0,0,0,3,0,0,5,1,2,3};
		int[][] edges1 = {{1, 2}, {1, 3}};
		int[] target1 = {0,7,3};
		int[][] edges2 = {{1, 3}, {1, 2}};
		int[] target2 = {0,7,1};
		
		System.out.println(Arrays.toString(solution(edges,target)));
		System.out.println(Arrays.toString(solution(edges1,target1)));
//		System.out.println(Arrays.toString(solution(edges2,target2)));
		
	}
	
    public static int[] solution(int[][] edges, int[] target) {
        
        int N = edges.length+1;
        
        Node[] nodes = new Node[N+1];
        for (int i = 1; i < N+1; i++) nodes[i] = new Node(i);
        for (int[] edge : edges) nodes[edge[0]].add(nodes[edge[1]]);
        
        int[] result = simul(nodes,target);
        
        
        
        
        
        
        return answerArr==null ? new int[] {-1} : answerArr;
    }
    
    // 중복 요소 ( 떨어지는 위치는 정해져 있음) 제거!!
    public static int[] simul(Node[] nodes, int[] target) {
    	int answer = 1;
    	numFallen = 0;
    	int[] result = new int[nodes.length];
    	int idx = 0;
    	out:
    	while(true) {
    		
	    	Node nowNode = nodes[1];
	    	
	    	int id = 1;
	    	while (nowNode != null) {
	    		id = nowNode.idx;
	    		nowNode = nowNode.nextNode(1);
	    	}
	    	result[idx++] = id;
	    	for (int i = 0; i < target.length; i++) {
	    		if (target[i] < result[i] || target[i] > 3*result[i]) break;
	    		if (i == target.length-1) break out;
	    	}
	    	numFallen++;
	    	
    	}
    	
    	return result;
    }
    
    public static int[] getAnswer(int[] result, int[] target) {
    	
    	int[] output = new int[target.length];
    	int ball = 1;
    	int idx = 1;
    	
    	while(true) {
    		
    		output[result[idx-1]] += ball;
    		if (target[result[idx-1]] - output[result[idx-1]] > 3*(numFallen - idx)) {
    			//rewind
    		}
    		
    		
    		
    		
    	}
    	
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