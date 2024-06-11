package BOJ._1976_여행가자;

import java.util.Scanner;

public class Main {

    // 갈 수 있냐 없냐만 판단한다는 것은
    // 서로소 집합 (서로 다른 집합)에 속하냐 아니냐를 묻는 것이므로
    // Union-find

    // 부모 배열
    static int[] p;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        // p(부모)배열 생성
        p = new int[N+1];

        // make-set
        for (int i = 1; i < N+1; i++) p[i] = i;

        // i, j가 p 배열의 index로 들어갈 것이기 때문에
        // 1부터 N 까지 (입력 마지막 줄에 도시가 0번부터가 아니니까)
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                int conn = sc.nextInt(); // 0이면 입력만 받고 넘겨야 한다.
                if (i > j) continue; // 대칭은 할 필요가 없다.

                if (conn == 1) { // 연결되어 있으면 union 해준다.
                    union(i,j);
                }

            }
        }
        // 여기까지 서로소 집합을 다 찾은 상태

        // 첫번째 도시의 부모를 찾은 다음
        int parent = find(sc.nextInt());

        // 그 뒤로 오는 도시들의 부모가 단 한개라도 다르면 "NO"를 출력
        for (int i = 0; i < M-1; i++) {
            if (parent != find(sc.nextInt())) {
                System.out.println("NO");
                return;
            }
        }
        // 여기 도달했다는 건 모두 부모가 같았다는 뜻
        // "YES" 출력
        System.out.println("YES");

        sc.close();
    }

    // union
    public static void union(int x, int y) {
        p[find(x)] = find(y);

    }

    // find
    public static int find(int x) {
        if (x == p[x]) return x;
        return p[x] = find(p[x]);
    }

}
