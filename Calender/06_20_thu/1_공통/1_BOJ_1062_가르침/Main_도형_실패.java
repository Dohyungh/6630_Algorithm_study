package BOJ._1062_가르침;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2 ^ 50 = 1.1258999e+15 (단어를 기준으로 DP를 쓸 경우 최대 경우의 수)
// 갖가지 백트래킹으로 될 줄 알았으나 실패
// 생각해보면 보호필름도 깊이가 13층 밖에 안됐었다 (경우의 수가 2개가 아니라 3개긴 했지만)

public class Main_실패 {
    static int answer = 0;

    static int N;
    static int K;

    static int[] words;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if( K < 5 ) {
            System.out.println(0);
            return;
        }

        // 알파벳이 26개니까 int(2^32)로 가능하다.

        words = new int[N];

        int totalBit = 0;

        for (int i = 0; i < N; i++) {
            words[i] = toBitMask(br.readLine().toCharArray());
            totalBit = totalBit | words[i];
        }

        if (usedBit(totalBit) < K) {
            System.out.println(N);
            return;
        }

        DP(0, 0, toBitMask("ancti".toCharArray()));

        System.out.println(answer);
    }

    static int toBitMask(char[] word) {
//      System.out.println((int) 'a');
//      97 인거 확인

        int result = 0;
        int len = word.length;

        for (char c : word) {
            result = result | (1 << (c - 97));
        }
        return result;
    }

    static void DP(int chosenCount, int idx, int accumulatedBit) {
        // 가르친 알파벳이 K 를 넘으면 백트래킹
        if (usedBit(accumulatedBit) > K) return;

        // 남은 단어를 모두 배워도 현재까지 알고 있는 최대 answer 보다 작거나 같으면 백트래킹
        if ((chosenCount + words.length - idx) <= answer) return;

        // 기저조건
        // 단어 배열의 끝에 도달했으면 정답을 업데이트
        if (idx >= words.length) {
            answer = Math.max(answer, chosenCount);
            return;
        }

        // 이번 단어에 쓰인 알파벳이 5개 (ancti) 이하면 그냥 배울 수 있으니까 배우고 넘어간다.
        if (usedBit(words[idx]) <=5) {
            DP(chosenCount+1, idx+1, accumulatedBit);
            return;
        }

        // 여태까지 배운 알파벳으로 커버되는 단어면 그냥 배우고 넘어간다.
        if ( (words[idx] & accumulatedBit) >= words[idx])  {
            DP(chosenCount+1, idx+1, accumulatedBit);
            return;
        }

        // 이번 단어에 쓰인 알파벳의 개수가 K개 보다 많으면 절대로 못 배우는 단어이므로 안 배우고 넘어간다.
        if (usedBit(words[idx]) > K) {
            DP(chosenCount, idx+1, accumulatedBit);
            return;
        }

        // 이번 단어에 속한 알파벳을 모두 배운다
        DP(chosenCount+1, idx+1, accumulatedBit | words[idx]);
        // 이번 단어를 안배운다
        DP(chosenCount, idx+1, accumulatedBit);
    }

    // 제공된 숫자 (비트) 에 켜진 비트가 몇개인지 세는 함수
    // 여태까지 배운 알파벳의 개수가 몇개인지 세는 것에 쓰인다
    static int usedBit(int bit) {
        int cnt = 0;
        while (bit > 0) {
            if ((bit & 1) > 0) cnt++;
            bit = bit>>1;
        }
        return cnt;
    }


}
