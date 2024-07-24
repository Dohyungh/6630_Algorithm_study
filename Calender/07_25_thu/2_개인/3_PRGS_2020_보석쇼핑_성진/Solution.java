import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        // 전체의 보석 종류를 구하기 위해 set 사용
        Set<String> gemSet = new HashSet<>(Arrays.asList(gems));
        // 젬카운트라는 맵을 선언
        // 해당 맵에 보석을 넣었다 뺐다하면서 전체 종류의 보석을 담고 있는지 확인할 것임!
        Map<String, Integer> gemCnt = new HashMap<>();


        // 투포인터 느낌
        int start = 0;
        int end = 0;
        // 주의! "가장 짧은 구간"을 찾아야하므로 아래의 두 변수를 선언해 사용할 것
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        while (end < gems.length) {
            // end는 0부터 1씩 증가할 것
            // gemCnt에는 현재 endGem(내가 이번에 넣을 보석)과
            // 현재까지 넣은 해당 보석의 개수(getOrDefault(endGem, 0) + 1)를 정수형으로 계산해 put해준다.
            String endGem = gems[end];
            gemCnt.put(endGem, gemCnt.getOrDefault(endGem, 0) + 1);
            end++;
            
            // 모든 종류의 보석을 포함할 때까지 start를 이동하는 로직
            // ㄴgemSet 집합의 사이즈가 전체 보석 종류의 수이므로
            while (gemCnt.size() == gemSet.size()) {
                // 중요: 최소 구간 길이 업데이트
                // minLength를 갱신 가능하다면 갱신
                if (end - start < minLength) {
                    minLength = end - start;
                    minStart = start;
                }
                
                // 보석을 제거하는 로직
                // start를 1씩 증가하며 보석 개수를 계산해 마찬가지로 map을 갱신해본다
                String startGem = gems[start];
                gemCnt.put(startGem, gemCnt.get(startGem) - 1);
                if (gemCnt.get(startGem) == 0) {
                    gemCnt.remove(startGem);
                }
                start++;
            }
        }

        // 진열대가 1번부터라서 1을 더해주어야 함
        return new int[] {minStart + 1, minStart + minLength};
    }
}
