package PRGS._2020Kakao_블록이동하기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	//정답률 1.4퍼
	public static void main(String[] args) { // 테스트 함수
		int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 0},{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 0}};
		System.out.println(solution(board));
		
	}
	
    public static int solution(int[][] board) {
    	int answer = 0;
    	
        int N = board.length;
        
        int numNodes = N*N - N; //20, 목적지는 19(가로) / 39(세로)
        
        int[][] hor_nodes = new int[N][N-1]; // 4*5 // 가로 노드들의 생성행렬(생성1, 비생성0) //boolean 으로 했어도 될듯..
        int[][] ver_nodes = new int[N-1][N]; // 5*4 // 세로 노드들의 생성행렬(생성1, 비생성0) //boolean 으로 했어도 될듯..
        
        int[][] adj = new int[2*numNodes][2*numNodes]; // 40*40
        
        
        
        
        // 노드 생성
        for (int i = 0; i<N; i++) { // node 생성 N * N-1
        	for (int j = 0; j<N-1; j++) {
        		if (board[i][j]==0 && board[i][j+1]==0) hor_nodes[i][j] = 1;
        	}
        }
        for (int i = 0; i<N-1; i++) { // node 생성 N-1 * N
        	for (int j = 0; j<N; j++) {
        		if (board[i][j]==0 && board[i+1][j]==0) ver_nodes[i][j] = 1;
        	}
        }
        
        
        
        
        // 인접행렬 생성
        for (int i = 0 ; i<N; i++) { //가로 노드들의 인접행렬 업데이트
        	for (int j = 0; j<N-1; j++) { 
        		if (hor_nodes[i][j] ==1) { //생성된 노드들에 대해서만 하면 된다.
        			
	        		int adjIndex = (N-1)*i + j; //인접행렬에서 해당 가로 노드의 행번호
	        		
	        		if (i>0 && hor_nodes[i-1][j]==1) adj[adjIndex][adjIndex-(N-1)] = 1; //위
	        		if (i<N-1 && hor_nodes[i+1][j]==1) adj[adjIndex][adjIndex+(N-1)] = 1; //아래
	        		if (j>0 && hor_nodes[i][j-1]==1) adj[adjIndex][adjIndex-1] =1; // 왼쪽
	        		if (j<N-2 && hor_nodes[i][j+1]==1) adj[adjIndex][adjIndex+1] = 1; //오른쪽
	        		
	        		//세로 노드와의 인접행렬업데이트, 앞에 가로노드들을 먼저 썼기 때문에 그 수 (numNodes)를 더해주고, 인덱스를 찾아야함
	        		if (i>0 && ver_nodes[i-1][j+1]==1) adj[adjIndex][numNodes+(i-1)*N+j] = 1; //좌상 우상에 세로 노드가 있어야.
	        		if (i>0 && ver_nodes[i-1][j]==1) adj[adjIndex][numNodes+(i-1)*N+j+1] = 1; //우상 좌상에 세로 노드가 있어야.
	        		if (i<N-1 && ver_nodes[i][j+1]==1) adj[adjIndex][numNodes+i*N+j] = 1; //좌하 우하에 세로 노드가 있어야.
	        		if (i<N-1 && ver_nodes[i][j]==1) adj[adjIndex][numNodes+i*N+j+1] = 1; //우하 좌하에 세로 노드가 있어야.
        		}
        	}
        }
        
        for (int i = 0 ; i<N-1; i++) { //세로 노드들의 인접행렬 업데이트
        	for (int j = 0; j<N; j++) { 
        		if (ver_nodes[i][j] ==1) { //생성된 노드들에 대해서만 하면 된다.
        			
	        		int adjIndex = numNodes + N*i + j; //인접행렬에서 해당 세로 노드의 행번호
	        		
	        		if (i>0 && ver_nodes[i-1][j]==1) adj[adjIndex][adjIndex-N] = 1; //위
	        		if (i<N-2 && ver_nodes[i+1][j]==1) adj[adjIndex][adjIndex+N] = 1; //아래
	        		if (j>0 && ver_nodes[i][j-1]==1) adj[adjIndex][adjIndex-1] =1; // 왼쪽
	        		if (j<N-1 && ver_nodes[i][j+1]==1) adj[adjIndex][adjIndex+1] = 1; //오른쪽
	        		
	        		//가로 노드와의 인접행렬업데이트 // 여긴 대칭성을 이용
        		}	
        	}
        }
        
        //대칭성!
        for(int i = numNodes; i<adj.length; i++) { // 인접행렬의 왼쪽 아래 사각형 ( 세로노드들의 각 가로노드에 대한 연결여부 )
        	for (int j = 0; j<numNodes; j++) {
        		adj[i][j] = adj[j][i];
        	}
        }
        
        // BFS
        answer = BFS(0,adj,numNodes);
    
        return answer;
    }
    
    
    //인접행렬에서 BFS로 최단경로(=depth) 구하기!
    //BFS 가 같은 depth 내의 모든 node를 탐색하니까 DFS 가 아니라 BFS 인것임!!
    //BFS에서 depth를 저장하는 트릭을 잘 볼 것.
    
    // 목표는 numNodes-1 혹은 2*numNodes -1
    public static int BFS(int start, int[][] adj, int numNodes) { //numNodes를 스태틱으로 선언하면 굳이 넣어줄 필요 없음
    	Queue<Integer> queue = new LinkedList<>();
    	int pNode = start;
    	int depth = 1;
    	
    	// 0 과 연결된 노드들 먼저 넣어줘
    	for (int j = 0; j<adj.length; j++) { //adj[i].length? 인접행렬은 정사각행렬이니까 상관없음
    		if (adj[pNode][j] == 1) queue.add(j); // 연결된 노드들을 다 넣어줘
    	}
    	
    	
    	// BFS에서 depth를 저장하는 트릭 (DFS 는 함수를 호출할 때마다 parameter에 depth+1 하면 되는데..)
    	// 큐에 다음 depth의 node들을 모두 추가 하고, 전체 queue의 길이를 재자.
    	// 그럼 그 길이를 다 검사하고 나면, 한 depth를 모두 탐색했다는 뜻이므로, depth+1
    	int sz = queue.size(); 
    	
    	boolean[] visited = new boolean[adj.length]; // 없으면 막 역주행 해버린다구
    	visited[start] = true; // 시작노드 방문
    	
    	out:
    	while (!queue.isEmpty()) { // 그냥 true 해도 됨. 어차피 break 할 거임. 일반적으로 BFS에서 이렇게 씀.
//    		System.out.println(queue);
    		
    		// "sz 만큼 반복해라"
    		for (int num = 0; num<sz; num++) { // num 은 그냥 아무 의미없는 반복변수. python에서 _(언더바) 같은 것. 
    			
    			pNode = queue.poll(); // queue 가장 앞 Node로 이동
    			
    			visited[pNode] = true; // 방문 표시
    			
    			for (int j = 0; j < adj.length; j++) { //인접행렬은 정사각행렬이니까 상관없음
    				
    				if (adj[pNode][j] == 1 && !visited[j]) queue.add(j); // 연결된 친구들을 다 넣어줘
    				
    				if (adj[pNode][j] == 1 && (j == numNodes-1 || j == 2*numNodes-1)) { // 최대로 시간을 줄이자! // 그냥 queue에 목적지가 들어오면 queue에 넣기도 전에 바로 그냥 break.
    					depth++;
    					break out;
    				}
    			}
    		}
    		
    		sz = queue.size(); // queue에 들어간 Node의 개수 = 다음 depth에 있는 node의 총 개수.
    		depth++; // 다음 층으로
    	
    	}
    	return depth;

    	
    }
    
}

