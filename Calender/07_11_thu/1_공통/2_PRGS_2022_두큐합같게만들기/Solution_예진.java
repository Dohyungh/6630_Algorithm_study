/*
 * 모르겠어서 풀이 참고했슴미다 ㅎ
 * 
 * [풀이방법]
 * 1. 주어진 각 배열의 합을 구해둔다.
 * 2. 배열 원소들을 두개의 큐에 나눠 담는다.
 * 3. 구해뒀던 두 배열의 합을 계속 비교하면서 poll, add를 반복한다.
 * 		- 합이 큰 큐의 원소를 빼서 옮긴다. (큐 합 상태도 그에 맞게 갱신, 횟수도)
 * 		- 두 큐의 합이 같아지는 순간 종료한다.
 * 		- 최대 횟수는 (큐1길이 + 큐2길이) * 2 이다. -> 모든 원소가 본래 위치로 돌아오는 데 필요한 횟수
 * 		- 최대 횟수를 초과하면 answer를 -1로 설정하고 종료한다.
 * 
 * [궁금한 점]
 * 합 비교를 통해 합이 더 큰쪽의 원소를 옮기는 로직이 어떻게 통하는건지,,
 * 단순히 poll해서 반대쪽 큐로 add하는 규칙이라서 가능한건가
 * 
 */

package prog_kakao_두큐합같게만들기;

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long aSum = 0;
        long bSum = 0;
        
        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            aSum += queue1[i];
            bSum += queue2[i];
        }
        
        while (aSum != bSum) {
            
            if (answer > queue1.length * 4) {
                answer = -1;
                break;
            }
            
            if (aSum > bSum) {
                int tmp = q1.poll();
                q2.add(tmp);
                aSum -= tmp;
                bSum += tmp;
                answer++;
            } else if (aSum < bSum) {
                int tmp = q2.poll();
                q1.add(tmp);
                aSum += tmp;
                bSum -= tmp;
                answer++;
            }
        }
        
        return answer;
    }
}