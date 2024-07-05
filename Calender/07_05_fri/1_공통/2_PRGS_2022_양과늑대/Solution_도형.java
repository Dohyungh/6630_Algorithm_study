package PRGS._2022Kakao_양과늑대;

import java.util.*;
class Solution {

    // 자식에 빠르게 접근하기 위해
    // 각 node 별로 2개의 자식 node 번호를 저장하는 child 배열 생성
    static int[][] child;

    // 함수 안에서 최종 정답을 업데이트 해주고 싶어서 스태틱 선언
    // 전체 노드의 개수 고정이고, 함수 안에서 계속 쓰고 싶어서 스태틱 선언
    static int answer, N;

    public int solution(int[] info, int[][] edges) {

        // 정답 초기화
        answer = 0;

        // 노드의 개수 (배열의 길이) 초기화
        N = info.length;

        // 자식 배열 초기화
        child = new int[N][2];

        // 자식 배열 입력된 거 O(1) 으로 접근할 수 있게 변환
        for (int[] edge : edges) {
            int pa = edge[0]; // 부모
            int ch = edge[1]; // 자식

            // 0에 먼저 채우고, 1에 채운다.
            if (child[pa][0] != 0) { // 이미 입력된 자식이 있으면, 1 번에
                child[pa][1] = ch;
                continue;
            }
            child[pa][0] = ch; // 없으면 0번에
        }

        int[] order = new int[N]; // 순서 배열 생성
        // 자동으로 첫번쨰 순서(0) 자리엔 0이 들어가 있겠지

        // 계속 복사해서 갖고 다닐 순서 배열
        // 양인지 늑대인지 적혀있는 info 배열
        // 몇번째 순서까지 탐색했는지 표시해줄 depth
        solve(order, info, 0);

        return answer;
    }

    // 재귀로 호출할 solve 함수
    // 일종의 순열인데,,
    // 자식으로 연결된 노드들만 다음 순서로 추가할 수 있는 순열
    void solve(int[] order, int[] info, int depth) {

        // 적혀있는 순서대로 방문했을 때

        // 양의 수
        int sheep = 0;
        // 늑대의 수
        int wolf = 0;

        for (int i = 0; i <= depth; i++) {
            // 이번 순서에
            int node = order[i];

            // 0 이면 양, 1이면 늑대
            if (info[node] == 0) sheep++;
            if (info[node] == 1) wolf++;
        }

        // 늑대가 양 이상이 되면 다 잡아 먹히니까 종료 (기저 조건)
        if (sheep <= wolf) return;
        // 아니면, static 정답 변수에 양의 수를 업데이트
        answer = Math.max(answer, sheep);
        
        // 여기부터 재귀 호출부

        // 다음 방문할 수 있는 노드 목록들을 다음 depth에 담아서
        // 다음 solve 함수 호출
        List<Integer> nextNodes = getNextNodes(order);

        for (int nextPossibleNode : nextNodes) {

            // 복사해서 넘겨주어야 함
            int[] newOrder = Arrays.copyOf(order, N);
            // 다음 노드를 써서 넘겨줌
            newOrder[depth+1] = nextPossibleNode;
            
            solve(newOrder, info, depth+1);
        }

    }

    // 다음으로 방문할 수 있는 자식노드들을 모아서
    // list로 반환해주는 함수
    // ((최적화가 덜 된 느낌))
    List<Integer> getNextNodes(int[] order) {

        // 방문 한 노드의 모든 자식 노드들 중에서
        // 방문 한 애들만 빼면
        // 다음에 방문할 수 있는 자식노드가 됨

        // 최종 반환해줄 자식 노드들 리스트
        List<Integer> nextNodes = new ArrayList<>();

        // 방문체크
        boolean[] visited = new boolean[N];

        for (int ord : order) {
            visited[ord] = true;
        }

        // 방문한 노드의
        for (int ord : order) {

            // 모든 자식노드들 중에서
            for (int i = 0; i <2; i++) {
                int ch = child[ord][i];

                // 방문 안했으면
                if (!visited[ch]) {

                    // 다음에 방문할 수 있는 자식노드
                    nextNodes.add(ch);
                }
            }

        }
        return nextNodes;

    }

}