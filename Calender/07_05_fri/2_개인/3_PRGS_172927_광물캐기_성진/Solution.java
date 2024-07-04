
/**
 * 풀이 순서
 * 1. 곡괭이 하나당 캘 수 있는 미네랄 개수인 5개로 partition을 구분
 * 2. 각 partition을 다중 조건으로(dia -> iron -> stone 순) 정렬하기
 * 3. 정렬된 순서대로 피로도의 최솟값을 계산
 */


import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int picksNum = 0;   // 전체 곡괭이의 수
        for (int i=0; i<picks.length; i++){
            picksNum += picks[i];
        }

        // 한 곡괭이당 캘 수 있는 미네랄(5개)를 partition으로 쪼개서
        // 정수형 배열 partition = {'다이아 개수', '철 개수', '돌 개수'}인 배열을
        // 리스트인 mineralList에 순서대로 저장
        List<int[]> mineralList = new ArrayList<>();
        
        for (int i=0; i<picksNum && i*5 < minerals.length; i++){
            int[] partition = new int[3];
            
            for (int j=0; j<5 && i*5 + j < minerals.length; j++){
                
                if (minerals[i*5 + j].equals("diamond")){
                    partition[0] += 1;
                }
                else if (minerals[i*5 + j].equals("iron")){
                    partition[1] += 1;
                }
                else {
                    partition[2] += 1;
                }
            }
            
            if (partition[0] != 0 || partition[1] != 0 || partition[2] != 0){
                mineralList.add(partition);
            }
        }
        
        // 람다 방식으로 다중 조건 정렬!
        // 아직 IDE 없이 클래스 선언하고, comparable interface 불러오는게 어려워서...
        // 물론 이것도 한 번에 쓰진 못했습니다...
        mineralList.sort((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o2[0] - o1[0]; // 다이아몬드 개수 기준 내림차순
            } else if (o1[1] != o2[1]) {
                return o2[1] - o1[1]; // 철 개수 기준 내림차순
            } else {
                return o2[2] - o1[2]; // 돌 개수 기준 내림차순
            }
        });
        

        int idx = 0;
        
        // 각 곡괭이(다이아 -> 철 -> 돌 순서)를 순회하면서
        out:
        for (int i=0; i<3; i++){
            // 각 곡괭이 당 보유 개수(cnt) 만큼
            // 미네랄 5개씩 캐기
            int cnt = picks[i];
            while (cnt > 0 && idx < mineralList.size()){
                int[] temp = mineralList.get(idx);
                if (i == 0){    // 다이아 곡괭이
                    answer += (temp[0] + temp[1] + temp[2]);
                }
                else if (i == 1){   // 철 곡괭이
                    answer += (temp[0] * 5 + temp[1] + temp[2]);
                }
                else{   // 돌 곡괭이
                    answer += (temp[0] * 25 + temp[1] * 5 + temp[2]);
                }
                idx++;
                cnt--;
            }
            // 곡괭이랑 광물 개수 잘 맞춰줘야 함(곡괭이로 캘 수 있는 전체 광물이 현재 광물 개수보다 많은 경우)
            if (idx >= mineralList.size()) {
                break out;
            }
        }
        
        return answer;
    }
}
