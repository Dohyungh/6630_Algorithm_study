import java.util.*;

class Solution {
    
    static long target;
    static int answer;
    
    public int solution(int[] queue1, int[] queue2) {
        // 정답 큰 수로 초기화
        answer = 987654321;
        
        // 두 큐를 좌우로 합친 큐를 생성하기 위한 초기화
        // 인덱스 0부터 (queue1 길이 - 1)까지 queue1을 의미 / 인덱스 queue1의 길이부터 queue1+queue2까지 queue2를 저장할 것
        // 참고. queue1.length == queue2.length
        int[] q = new int[queue1.length*2];
        long sum1 = 0;
        long sum2 = 0;
         
        for (int i=0; i<q.length; i++){
            if (i < queue1.length){
                q[i] = queue1[i];
                sum1 += queue1[i];
            }
            else {
                q[i] = queue2[i-queue2.length];
                sum2 += queue2[i-queue2.length];
            }
        }
        
        // 만약 전체의 합이 홀수라면 바로 리턴하기
        if ((sum1 + sum2)%2 == 1){
            return -1;
        }
        
        // 내가 맞추고자 하는 큐의 합을 target으로 선언
        target = (sum1 + sum2) / 2;
        
        
        // 투포인터 사용
        int left = 0;
        int right = queue1.length;
        long currSum = sum1;    // 현재 합을 기준으로 left를 증가시킬지 right을 증가시킬지를 판단
        answer = 0;

        while (left < q.length && right < q.length) {
            if (currSum == target) {
                return answer;
            } else if (currSum > target) {
                currSum -= q[left];
                left++;
            } else {
                currSum += q[right];
                right++;
            }
            answer++;
        }
        return -1;
    }
    
    
    // 아래는 실패한 메소드 방식
    // O(n^2)인데 else if로 백트래킹되지 않나 싶어서 실행했는데
    // 9번, 14번 테케 틀리고, 시간 초과 4개쯤 떴었음
    static boolean findTarget(int[] arr, int arrLen) {
        long[] pSum = new long[arr.length + 1];
        
        for (int i = 1; i <= arr.length; i++) {
            pSum[i] = pSum[i - 1] + arr[i - 1];
        }
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j <= arr.length; j++) {
                if (pSum[j] - pSum[i] == target) {
                    int idx1 = i;
                    int idx2 = j;
                    int result = idx1 + Math.abs(idx2 - arrLen);
                    answer = Math.min(result, answer);
                    return true;
                } else if (pSum[j] - pSum[i] > target) {
                    break;
                }
            }
        }
        return false;
    }
    
    
    
    
    
}