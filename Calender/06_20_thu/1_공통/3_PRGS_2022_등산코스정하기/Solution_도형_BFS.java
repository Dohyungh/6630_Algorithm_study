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
/*
테스트 1 〉	통과 (1.12ms, 82.1MB)
테스트 2 〉	통과 (1.02ms, 81.9MB)
테스트 3 〉	통과 (0.78ms, 66.8MB)
테스트 4 〉	통과 (0.79ms, 76.1MB)
테스트 5 〉	통과 (0.75ms, 76.3MB)
테스트 6 〉	통과 (1.18ms, 73.6MB)
테스트 7 〉	통과 (1.12ms, 75.8MB)
테스트 8 〉	통과 (0.84ms, 75.6MB)
테스트 9 〉	통과 (1.49ms, 72.6MB)
테스트 10 〉	통과 (1.98ms, 77.8MB)
테스트 11 〉	통과 (2.92ms, 76.3MB)
테스트 12 〉	통과 (2.74ms, 79.4MB)
테스트 13 〉	통과 (4.98ms, 90.2MB)
테스트 14 〉	통과 (12.79ms, 106MB)
테스트 15 〉	통과 (46.22ms, 154MB)
테스트 16 〉	통과 (44.89ms, 148MB)
테스트 17 〉	통과 (66.97ms, 148MB)
테스트 18 〉	통과 (22.74ms, 94.8MB)
테스트 19 〉	통과 (46.20ms, 113MB)
테스트 20 〉	통과 (244.67ms, 189MB)
테스트 21 〉	통과 (329.53ms, 161MB)
테스트 22 〉	통과 (257.67ms, 381MB)
테스트 23 〉	통과 (312.63ms, 397MB)
테스트 24 〉	통과 (952.95ms, 394MB)
테스트 25 〉	통과 (804.54ms, 543MB)
 */