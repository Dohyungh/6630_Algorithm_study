package prgs_귤고르기;

import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        // tangerine의 원소 10,000,000까지이므로
        // 카운팅 배열 생성
        int[] cntArr = new int[10000001];
        for (int i=0; i<tangerine.length; i++){
            int t_size = tangerine[i];
            cntArr[t_size]++;
        }
        // 내림차순 정렬
        Arrays.sort(cntArr);
        
        // 임시 합 저장 변수
        int sum = 0;
        // 뒤에서부터 카운트배열 순회하며 0 이상의 tangerine에 대해서 임시 합을 계산
        // 담으려는 귤의 개수 k 이상이 되는 순간 전체 종류의 합을 반환
        for (int i=cntArr.length-1; i>=0; i--){
            if (cntArr[i] > 0){
                answer++;
                sum += cntArr[i];
            }
            if (sum >= k){
                break;
            }
        }
        
        
        return answer;
    }
}