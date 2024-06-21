package PRGS._2022Kakao_등산코스정하기;

import java.util.*;
class Solution_Union_find {
    int[] p;
    boolean[] isGate;
    boolean[] isSummit;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {-1,-1};

        // 봉우리들을 정렬해서 제일 작은 봉우리를 우선 탐색하고 갈 수 있으면 Break 할 것임.
        Arrays.sort(summits);

        // 이분탐색을 위한 두 개 포인터 설정
        int left = 1;
        int right = 10000001;

        // 출입구인지, 봉우리인지 노드의 타입 구분이 중요한데,
        // node 클래스 만들어서 필드로 타입 (봉우리, 출입구, 쉼터)을 넣어줘도 되고,
        // 이렇게 O(1) 로 바로 알 수 있게 배열을 선언해도 됨.
        // 배열을 선언한다는 게 메모리를 희생한다는 건데
        // 풀이 도중에 이 배열이 바뀔 일도 없고, 크기도 최대 50000 밖에 안된다.
        isGate = new boolean[1+n];
        isSummit = new boolean[1+n];

        // 이렇게 굳이 Gates 배열과 Summits 배열을 순회하면서 체크하지 말고,
        // 바로 인덱스로 이 노드가 출입구인지, 봉우리인지 확인할 수 있도록 하자.
        for (int g : gates) isGate[g] = true;
        for (int s : summits) isSummit[s] = true;

        // 이분탐색 (매개변수 탐색)
        // 해당 mid 값 이하인 edge 들만 존재한다고 생각하고 union-find 실행할 것임
        // 결국 가장 작은 mid를 찾아 나가는 과정에서는
        // 단순히 출입구랑 연결된 봉우리가 있는지 없는지만 중요하지, 어떻게 어떤 가중치로 연결됐는지는 중요하지 않음.
        while(left < right) {

            int mid = ( left + right ) /2;

            // p 배열은 매개변수가 바뀔 때마다 새로 만들어 줌.
            p = new int[1+n];

            //make-set
            for (int i = 1; i < 1+n; i++) {
                p[i] = i;
            }

            // 모든 edge들을 돌면서
            for (int[] path : paths) {
                // 가중치가 mid 값 이하인 edge들만 골라서 union 해줌
                if (path[2] <= mid) {
                    union(path[0], path[1]);
                }
            }

            // 한 봉우리라도 경로를 찾았다면, 이분탐색 왼쪽 탐색
            // 못 찾았다면 오른쪽 탐색
            boolean flag = false;

            // 모든 봉우리에 대해서
            for (int s : summits) {
                // gate를 부모로 가지는 (연결된) 봉우리가 있다면 찾았다 표시하고 정답 업데이트 후 break
                if (isGate[p[s]]) {
                    flag = true;
                    answer[0] = s;
                    answer[1] = mid;
                    break;
                }
            }

            // 하나도 못 찾음
            if (!flag) {
                left = mid+1;
            } else {
                // 찾음
                right = mid;
            }

        }

        return answer;
    }

    // union-find를 변형해서 사용할 것임
    // 목표는 봉우리와 출입구가 연결되어 있으면 봉우리의 부모를 출입구로 바꿔주고
    // 마지막에 봉우리들의 P 배열만 순회하면서 그 중에 출입구랑 연결된 친구가 있으면 됨.
    // 과정이 좀 까다로운데,
    // 관찰할 Edge의 두 노드의 부모가 누구냐에 따라 달라짐
    // 1. 어느 한쪽이 Gate일 경우 무조건 Gate쪽으로 연결함 (둘다 Gate 일 경우를 생각할 필요 없음. 그냥 아무 쪽으로나 연결되면 됨)
    // 2. 어느 한쪽만 Summit일 경우 (나머지는 일반 Node) Summit 쪽으로 연결함
    // 3. 둘다 Summit일 경우 원본 노드가 Summit인지 아닌지를 봐야함
        // 일반 Node (부모가 Summit) vs 일반 Node (부모가 Summit) -> 더 번호가 작은 Summit 쪽으로 "원본"을 연결
        // 일반 Node vs Summit (원본이 그냥 Summit) -> Summit 쪽으로 연결
        // Summit vs Summit -> 연결안하고 그냥 넘김
    // 0. 둘다 일반 Node 인 경우는 그냥 연결하면 됨
    void union(int x, int y) {
        // 부모를 찾아본다.
        int px = find(x);
        int py = find(y);

        // 부모가 출입구
        if (isGate[px]) {
            p[py] = px;
            return;
        }
        if (isGate[py]) {
            p[px] = py;
            return;
        }
        // 부모가 둘다 봉우리
        if (isSummit[px] && isSummit[py]) {

            // 원본을 봐야함

            // 일반 Node vs Summit
            if (isSummit[x] && !isSummit[y]) {
                p[y] = x;
                return;

            }
            if (!isSummit[x] && isSummit[y]) {
                p[x] = y;
                return;
            }

            // Summit vs Summit
            if (isSummit[x] && isSummit[y]) {
                // 아무것도 안함. (연결되면 안됨)
                return;
            }

            // 일반 Node vs 일반 Node
            // 더 번호가 작은쪽으로 "원본"!!! 을 연결
            if (px < py) {
                p[y] = px;
            } else {
                p[x] = py;
            }
            return;
        }
        // 여기 도달했다는 것은
        // 일반 vs Summit
        // 일반 vs 일반

        // 한쪽만 Summit이면 Summit 쪽으로 연결
        if (isSummit[px]) {
            p[py] = px;
            return;
        }
        if (isSummit[py]) {
            p[px] = py;
            return;
        }

        // 둘다 일반 Node 면 그냥 연결
        p[px] = py;
    }

    // path-compression 을 포함하는 find
    // 부모를 거슬러 올라가다가 출입구 혹은 봉우리를 찾으면 그걸 반환해줄 거임
    // 결국 연결된 집단의 대표(최고부모)는 출입구거나 봉우리가 되게 할려고 그러는 것임.
    int find(int x) {
        // 출입구 혹은 봉우리인 조건이 우선 적용됨
        if (isGate[x] || isSummit[x]) return x;

        // 아직 출입구나 봉우리랑 연결이 안 된 평범한 노드들은 이쪽으로 빠지겠지
        if (p[x] == x) return x;

        return p[x] = find(p[x]);
    }

}