package PRGS._2022Kakao_등산코스정하기;
/*
시간초과 + 마구 틀림.

모든 Gate 로부터 BFS를 돌려서 봉우리가 찾아지는지를 체크했는데

이제 생각해보니.. 그냥 Summit 으로부터 BFS를 돌려서 Gate가 나오는지를 찾는게 훨씬 쉬웠을 듯.

라고 생각이 들어서 다시 푸니까 바로 맞았쥬?
 */


import java.util.*;
class Solution_BFS {

    class Node {
        int num;
        int dist;

        Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        // 정렬이 안 되어있음;;
        Arrays.sort(summits);

        // 인접리스트
        List<Node>[] adjList = new List[1+n];

        // 인접리스트 초기화
        for (int i = 1; i < adjList.length; i++) adjList[i] = new ArrayList<Node>();

        // 인접리스트 생성
        for (int[] path : paths) {
            int no = path[0];
            int de = path[1];
            int dist = path[2];
            adjList[no].add(new Node(de,dist));
            adjList[de].add(new Node(no,dist));

        }

        boolean[] isSummit = new boolean[1+n];
        boolean[] isGate = new boolean[1+n];

        for (int gate : gates) isGate[gate] = true;
        for (int summit : summits) isSummit[summit] = true;

        int left = 1;
        int right = 10000001;
        int mid = -1;

        int[] answer = {-1, -1};

        while (left < right) {
            mid = (left + right) / 2;

            boolean flag = false;
            for (int start : summits) {
                if (BFS(n, adjList, mid, start, isSummit, isGate)) {
                    flag = true;
                    answer[0] = start;
                    answer[1] = mid;
                    break;
                }
            }

            if (!flag) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        return answer;
    }

    // BFS
    // summit 에서 출발해서
    // Gate가 보이면 true 를 반환
    // 안 보이면 False 를 반환
    public boolean BFS (int n, List<Node>[] adjList, int mid, int start, boolean[] isSummit, boolean[] isGate) {

        boolean found = false;

        Queue<Integer> queue = new LinkedList<>();

        boolean[] visited = new boolean[1+n];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();

            for (Node nextNode : adjList[nowNode]) {

                // 출입구가 보이면 바로 그냥 true 리턴
                if (isGate[nextNode.num] && nextNode.dist <=mid) {
                    return true;
                }

                // 아니면 봉우리가 아니고, 거리가 갈 수 있는 거리면 queue에 넣어준다.
                if (!isSummit[nextNode.num] && !visited[nextNode.num] && nextNode.dist <=mid) {
                    queue.add(nextNode.num);
                    visited[nextNode.num] = true;
                }
            }
        }
        return found;
    }
}