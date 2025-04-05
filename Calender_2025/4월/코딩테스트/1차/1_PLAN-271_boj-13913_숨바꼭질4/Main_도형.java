package BOJ._13913_숨바꼭질4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int answer = 0;
    static List<Integer> answerList = null;
    static int K;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        K = sc.nextInt();
        int cN = N;
        if (cN > K) {

            System.out.println(N - K);
            for (int i = N; i>=K; i--) {
                sb.append(i);
                sb.append(" ");
            }
            System.out.println(sb.toString());
            return;

        }

        while(cN * 2 <= K) {
            cN *= 2;
            answer++;
        }

        answer += K - cN;
        List<Integer> list = new ArrayList<Integer>();
        recur(0, N, list);
        answerList.add(K);
        System.out.println(answer);
        for (int i = 0; i < answerList.size(); i++) {
            System.out.print(answerList.get(i));
            System.out.print(" ");
        }
    }

    static void recur(int depth, int N, List<Integer> list) {
        if (depth > answer) return;
        List<Integer> copiedList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            copiedList.add(list.get(i));
        }
        copiedList.add(N);
        if (N == K) {
            if (depth < answer) {
                answer = depth;
                answerList = list;
            }
            return;
        }

        if (N > K) {
            recur(depth+1, N-1, copiedList);
            return;
        }

        if (N < K) {
            recur(depth+1, N*2, copiedList);
            recur(depth+1, N+1, copiedList);
            recur(depth+1, N-1, copiedList);
        }


    }
}
