package day_29;

import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] adj;
    static boolean[] visited;
    static int[] subTreeSize;    // 인덱스와 번호가 일치하는 노드의 서브트리의 크기를 저장하는 dp 테이블

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        subTreeSize = new int[1+N];
        Arrays.fill(subTreeSize, 1);    // 모든 서브트리의 크기를 1로 일단 초기화(자기자신은 반드시 포함)

        adj = new ArrayList[1+N];
        for(int i=1 ; i<=N ; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=0 ; i<N-1 ; i++){
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            adj[U].add(V);
            adj[V].add(U);
        }

        visited = new boolean[1+N];
        visited[R] = true;
        DFS(R);

        for(int i=0 ; i<Q ; i++){
            sb.append(subTreeSize[Integer.parseInt(br.readLine())]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    // 탑다운 방식의 dp로 node를 루트로 하는 서브트리의 크기는 node의 자식을 루트로 하는 서브트리의 크기합 + 1(이 1은 위에서 초기화때 해줌)
    private static int DFS(int node){

        for(int next : adj[node]){
            if(!visited[next]){
                visited[next] = true;
                subTreeSize[node] += DFS(next);
            }
        }

        return subTreeSize[node];
    }

}
