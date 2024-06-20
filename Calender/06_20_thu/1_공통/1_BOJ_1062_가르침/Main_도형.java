package BOJ._1062_가르침;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 정답을 함수 안에서 업데이트 할 것이기 때문에 스태틱으로 선언
    static int answer = 0;

    // 단어의 개수
    static int N;
    // 가르칠 알파벳의 개수
    static int K;

    // 단어들을 비트마스킹을 통해 숫자로 바꿔서 저장할 words 배열
    static int[] words;

    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 무조건 ancti 는 배워야 한다.
        // 애초에 준 K가 5개 이상이 아니면 아무것도 못 배운다. 0을 출력하고 리턴.
        if( K < 5 ) {
            System.out.println(0);
            return;
        }

        // 알파벳이 26개니까 int(2^32) 범위 내에서의 비트마스킹이 가능하다.
        // 단어들을 모두 숫자로 바꿔서 저장할 것이다.
        // 예를 들어 "a" 라는 단어는 1 (== 1 << 'a' - 'a' 로 생각한다.)
        // "ac" 라는 단어는 101 (오른쪽 끝 1이 a 이고, 왼쪽 끝이 c이다.)
        // "ade" 라는 단어는 11001
        // 이런식으로 2진수로 바꿔서 저장한다.

        words = new int[N];

        // 전체 단어들에서 출현한 알파벳이 K개 이하이면 모두 배울 수 있는 단어들이므로
        // N을 출력하고 리턴할 것이다.

        // 전체 단어들에서 출현한 모든 알파벳을 기억할 int변수를 선언
        int totalBit = 0;

        // toBitMask 함수를 이용해 모든 단어들을 숫자로 바꿔 words 배열에 저장해 놓을 것이다.
        for (int i = 0; i < N; i++) {
            words[i] = toBitMask(br.readLine().toCharArray());

            // 그 과정에서 totalBit을 계산한다.
            // 사용한 알파벳이 누적되어 갈 것이기 때문에,
            // 비트 OR 연산을 이용한다.
            totalBit = totalBit | words[i];
        }

        // 전체 단어들에서 사용된 알파벳의 총 개수를 구하는 부분
        // 한칸씩 오른쪽 시프트 연산 하면서 (오른쪽 끝 숫자를 날리면서)
        // 만약 1과 & 연산 결과가 0보다 컸다면, (즉, 오른쪽 끝 숫자가 1이었다면) 켜진 비트의 개수(cnt)를 +1 해준다
        int cnt = 0;
        while (totalBit > 0) {
            if ((totalBit & 1) > 0) cnt++;
            totalBit = totalBit>>1;
        }

        // 만약 켜진 비트의 개수 (= 총 사용된 알파벳의 총 개수) 가 K 보다 작으면 그냥 모든 단어를 배울 수 있다 하고 리턴
        if (cnt < K) {
            System.out.println(N);
            return;
        }

        // 26 Combination 13 = 1040 0600 (알파벳을 기준으로 조합을 쓸 경우 최대 경우의 수)
        // 1040 0600 * 50 (words배열 순회)

        // ancti 5개 알파벳을 배웠다 생각하고 조합을 구해보자
        getComb(5, toBitMask("ancti".toCharArray()) ,0);

        System.out.println(answer);
    }

    // char[] 형태로 단어가 들어오면 'a'를 0으로 생각한
    // 비트마스킹을 통해
    // int형으로 변환된다.
    static int toBitMask(char[] word) {
        int result = 0;

        for (char c : word) {
            result = result | (1 << (c - 97));
        }

        return result;
    }

    static void getComb(int depth, int chosen, int index) {
        if (depth == K) {
            // 한가지 경우의 수를 구하면 바로 정답을 업데이트 할 수 있는 기회를 준다.
            // countLearnable 함수로 읽을 수 있는 단어의 개수를 count한다.
            countLearnable(chosen);
            return;
        }

        // 알파벳의 범위를 벗어나면 강제로 끝낸다.
        if (index >= 26) {
            return;
        }

        // 현재 어떤 알파벳 차례인지
        int now = 'a' + index;

        // ancti 차례라면 그냥 넘어간다
        if (now == 'a' || now == 'n' || now == 'c' || now == 't' || now == 'i') {
            getComb(depth, chosen, index+1);
            return;
        }

        // 그 이외의 알파벳이라면 배울지 안배울지를 분기를 나누어 조합을 구현한다.
        getComb(depth+1, chosen | (1<<index), index+1);
        getComb(depth, chosen, index+1);

    }

    // words 배열을 순회하면서 각 단어가 읽을 수 있는 단어인지 체크해서
    // 개수를 세어 answer보다 클 때만 업데이트한다.
    // 각 단어가 읽을 수 있는 단어인지 판단할 때는
    // 배운 알파벳 (chosen) 과 word 를 비트 and 연산한 결과가 word 자체와 같은지(==)를 판단한다. (본 코드에서는 >= 부등호를 썼다.)
    static void countLearnable(int chosen) {
        int cnt = 0;
        for (int word : words) {
            if ((chosen & word) >= word) {
                cnt++;
            }
        }
        answer = Math.max(answer, cnt);
    }
}
