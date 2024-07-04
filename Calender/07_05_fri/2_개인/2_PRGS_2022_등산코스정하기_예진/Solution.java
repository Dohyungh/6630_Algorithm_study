/*
 * [풀이방법]
 * 등산코스에는 출입구가 1개, 산봉우리가 1개씩만 포함되어야 한다.
 * 출입구에서 산봉우리까지 가는 최저 intensity 코스를 구하면 반대로는 똑같이 내려와도 되니까 단방향만 확인해도 된다.
 * 
 * 1. 길 정보를 인접리스트로 입력해둔다. (양방향)
 * 2. 출입구와 산봉우리 인덱스로 여부를 알수있도록 각각의 boolean배열을 만든다.(반복문 시간복잡도 방지)
 * 3. 출입구 목록을 기준으로 순회한다.
 * 4. 출입구가 정해지면 프림 알고리즘을 통해 각 산봉우리까지의 최저 intensity와 산봉우리 번호를 비교하며 answer를 갱신한다.
 * 		이때, 다른 출입구라면 경로를 선택할 수 없다.
 * 
 * 프림 알고리즘인데, 지나온 길에서 가장 높은 intensity를 저장하면서 길 가중치를 비교해야 한다. (크루스칼도 프림도 다익스트라도 아닌 성질인듯해서,,)
 * 
 * [실패요인]
 * 1. 출입구와 산봉우리를 각각 하나씩 매칭해서 일일이 구했는데 시간초과가 떴다.
 * 2. 출입구만 정하고 모든 산봉우리를 한번에 구하는데, 각 산봉우리에 도달하면 테이블을 갱신하고 더 나아가지 않아야 한다.(중요)
 * 		코스에 산봉우리는 하나만 갈 수 있어서! 그리고 이거 안끊으면 시간초과도 난다.
 * 3. 각 출입구에서 모든 산봉우리의 경로를 구할때마다 내부에서 최저 intensity를 비교해서 answer를 갱신하는게 아니라,
 * 		처음부터 answer의 값과 산봉우리 번호를 동시에 비교해가며 갱신해야 한다. 아니면 같은 로직을 두번해야 함. intensity가 같을때 산봉우리 번호가 더 작아야해서!
 */

package grog_2022KAKAO_등산코스정하기;

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        // 답안 초기화
        int[] answer = new int[] {0, Integer.MAX_VALUE};
        
        // 인접리스트 초기화
        List<Node>[] adj = new List[n+1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // 인접리스트 입력
        for (int[] path : paths) {
            adj[path[0]].add(new Node(path[1], path[2]));
            adj[path[1]].add(new Node(path[0], path[2]));
        }
        
        // 출입구와 산봉우리 인덱스확인용 배열 초기화
        boolean[] isGate = new boolean[n+1];
        for (int i : gates) {
            isGate[i] = true;
        }
        
        boolean[] isSummit = new boolean[n+1];
        for (int i : summits) {
            isSummit[i] = true;
        }
        
        
        // 출입구 선택
        for (int gate : gates) {
            isGate[gate] = false;
                
            // dist 테이블 초기화
            boolean[] visited = new boolean[n+1];
            int[] dist = new int[n+1];
            for (int i = 1; i <= n; i++) {
                dist[i] = Integer.MAX_VALUE;
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            // 1. 출입구(시작점) 추가
            pq.add(new Node(gate, 0));

            while (!pq.isEmpty()) {

            	// 2. 다음 노드 뽑아서
                Node curr = pq.poll();
                
                // 최적경로 확정상태거나, intensity가 이미 answer보다 크거나, 산봉우리면 패스
                // (큐에 들어갈때 이미 최적해는 갱신된 상태) 
                if (visited[curr.next] || curr.w > answer[1] || isSummit[curr.next]) continue;
                // 최적경로 확정체크
                visited[curr.next] = true;

                // 인접한 지점 순회하며 intensity 갱신
                for (Node node : adj[curr.next]) {
                	
                	// 다른 출입구면 패스
                    if (isGate[node.next]) continue;

                    // 지금까지 온 경로의 최대 intensity와 인접한 지점까지의 경로 intensity 중 큰값으로 갱신
                    int in = Math.max(curr.w, node.w);
                    
                    // 이전까지 지점에 표시된 intensity보다 지금 가려는 경로가 더 최적이라면 값 갱신
                    if (dist[node.next] > in) {
                        dist[node.next] = in;
                        
                        // 큐에 넣을때는 경로 중 가장 큰 intensity를 가중치로 추가함
                        pq.add(new Node(node.next, in));
                    }
                }
            }

            // 해당 게이트에서 출발한 경우를 모두 구했으면, 모든 산봉우리를 순회하며 답안 갱신
            for (int summit : summits) {
            	
            	// 답안보다 현재 경로가 더 최적이면 산봉우리와 intensity 갱신
                if (answer[1] > dist[summit]) {
                    answer = new int[] {summit, dist[summit]};
                    
                // 답안과 intensity가 같은데 현재 산봉우리 번호가 더 작다면 갱신
                } else if (answer[1] == dist[summit] && answer[0] > summit) {
                    answer = new int[] {summit, dist[summit]};
                }
            }
            
            // 출입구 선택 해제
            isGate[gate] = true;
        }
        return answer;
    }
}

class Node implements Comparable<Node> {
    int next;
    int w;
    
    Node (int next, int w) {
        this.next = next;
        this.w = w;
    }
    
    @Override
    public int compareTo(Node n) {
        return w - n.w;
    }
}
