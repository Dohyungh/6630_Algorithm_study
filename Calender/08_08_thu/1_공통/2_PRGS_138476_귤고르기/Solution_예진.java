/*
 * [풀이방법]
 * size별로 개수를 세고, 내림차순으로 정렬해서 가장 개수가 많은 사이즈부터 채운다.
 * 
 * 기본형 배열은 sort 메서드가 안되는 것 같다. 
 * 참조형이어야 sort메서드로 정렬이 가능하다. 
 * 기본형은 직접 값에 접근해서 swap해주는 정렬을 구현해야 한다. 
 * int 배열로 퀵정렬 구현해봤는데, 이 문제에서는 배열 크기도 너무 큰데 비해 
 * 사이즈 종류가 애초에 많이 없으면 0인 값이 대다수라서 오히려 정렬에 비효율이다.
 * 
 */
package prog_귤고르기;

import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        // 사이즈별 귤의 개수 찾기
        Map<Integer, Integer> cntForSize = new HashMap<>();
        for (int size : tangerine) {
            cntForSize.put(size, cntForSize.getOrDefault(size, 0) + 1);
        }
        
        // 구한 사이즈별 개수를 리스트로 만들어서 내림차순 정렬
        List<Integer> counts = new ArrayList<>(cntForSize.values());
        counts.sort(Collections.reverseOrder());
        
        // k에서 하나씩 빼면서 종류 수 구하기
        for (int cnt : counts) {
            k -= cnt;
            answer++;
            if (k <= 0) break;
        }
        
        return answer;
    }
}