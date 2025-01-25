package BOJ._2723_눌러서잠금해제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * 완벽하게 이해하진 못했지만 적는다.
 * 
 * 숫자가 3개 있다고 했을 때
 * (1-2) 는 0 0 0 에서 곧바로 1 1 0 의 "상태"로 가는 것과 동일하다.
 * (1-2) 3 는 0 0 0 에서 1 1 0 의 상태로 갔다가 1 1 1의 상태로 가는 것과 동일하다.
 * 3 (1-2) 는 0 0 0 에서 0 0 1 의 상태로 갔다가 1 1 1의 상태로 가는 것과 동일하다.
 * 
 * 이 예시들은 모두 한가지의 경우의 수로 세어지기 때문에
 * 
 * for 문 안에서 ++을 해주는 것이다.
 * 
 */

public class Main {
    private static int nbuttons = 0;
    private static int maxMove = 0;
    private static int[] saveMove = new int[16384];

    private static long count(int used) {
        if (saveMove[used] > 0) {
            return saveMove[used];
        }
        long nMove = 0;
        for (int move = 1; move < maxMove; move++) {
            if ((move & used) != 0) {
                continue;
            }
            
            // 1을 더하는 이유는 이것이 곧 한가지 경우이기 때문이다.
            nMove++;
            nMove += count(used | move);
        }
        saveMove[used] = (int) nMove;
        return nMove;
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        int n = Integer.parseInt(line.trim());

        for (int i = 1; i <= n; i++) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            nbuttons = Integer.parseInt(line.trim());
            maxMove = 1 << nbuttons;

            Arrays.fill(saveMove, 0);
            long ncomb = count(0);
            System.out.printf("%d%n", ncomb);
                
            }

        
    }
}
